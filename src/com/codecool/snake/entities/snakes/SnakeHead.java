package com.codecool.snake.entities.snakes;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;

public class SnakeHead extends GameEntity implements Animatable {

    private static final float speed = 2;
    private static final float turnRate = 2;
    public boolean leftKeyDown = false;
    public boolean rightKeyDown = false;
    public boolean isInvincible = false;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;

    public SnakeHead(Pane pane, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        health = 100;
        tail = this;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);

        addPart(4);
    }

    public void step() {
        double dir = getRotate();
        if (leftKeyDown) {
            dir = dir - turnRate;
        }
        if (rightKeyDown) {
            dir = dir + turnRate;
        }
        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
            Globals.gameLoop.stop();
            Alert alert = showGameOverModal();

            Platform.runLater(alert::showAndWait);
        }
    }


    private Alert showGameOverModal() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        System.out.println("Game Over");
        alert.setTitle("Game Over");
        alert.setHeaderText("Game Over");
        alert.setContentText("Do you want try again?");

        ButtonType yesButton = new ButtonType("YES");
        ButtonType noButton = new ButtonType("NO");

        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(noButton, yesButton);

        alert.getDialogPane().lookupButton(yesButton).setOnMouseReleased(event -> {
            Globals.gameLoop.restart();
        });

        alert.getDialogPane().lookupButton(noButton).setOnMouseReleased(event -> {
            alert.close();
        });

        return alert;
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
            tail = newPart;
        }
    }

    public int getHealth() {
        return this.health;
    }

    public void beInvincible() {
        this.isInvincible = true;
        long expectedTime = System.currentTimeMillis() + 5000;
        if (System.currentTimeMillis() == expectedTime) {
            this.isInvincible = false;
        }
    }

    public void changeHealth(int diff) {
        health += diff;
    }
}

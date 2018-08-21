package com.codecool.snake.entities.snakes;

import com.codecool.snake.Globals;
import com.codecool.snake.Modals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;

public class SnakeHead extends GameEntity implements Animatable {

    private static final float speed = 2;
    private static final float turnRate = 2;
    private static final int startingHealth = 100;
    private static final int startingTailParts = 4;
    public int health;
    public int score = 0;
    public boolean leftKeyDown = false;
    public boolean rightKeyDown = false;
    private GameEntity tail = this; // the last element. Needed to know where to add the next part.
    private boolean player;
    private ArrayList<SnakeBody> body = new ArrayList<SnakeBody>();


    public SnakeHead(Pane pane, int xc, int yc, boolean player) {
        super(pane);
        setX(xc);
        setY(yc);
        if (player) setImage(Globals.snakeHead);
        else setImage(Globals.secSnakeHead);
        tail = this;
        health = startingHealth;
        pane.getChildren().add(this);
        this.player = player;

        addPart(startingTailParts);
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
            health = 0;
            score = body.size();
            for (GameEntity entity : Globals.getGameObjects()) {
                if (body.indexOf(entity) != -1) {
                    Globals.removeGameObject(entity);
                    entity.destroy();
                }
            }
            for (GameEntity entity : Globals.getGameObjects()) {
                if (entity.equals(this)) {
                    Globals.removeGameObject(entity);
                    entity.destroy();
                }
            }
        }
    }
//            Globals.gameLoop.stop();
//            Modals modal = new Modals();
//            Alert alert = modal.showGameOverModal();
//
//            Platform.runLater(alert::showAndWait);

//        Globals.menuHealth.setText("Health: " + Globals.health);


    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            Image image;
            if (player) image = Globals.snakeBody;
            else image = Globals.secSnakeBody;
            tail = new SnakeBody(pane, tail, image);
            body.add((SnakeBody) tail);
        }
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void changeHealth(int diff) {
        health += diff;
        setHealth(health);
        Globals.menuHealth.setText(Integer.toString(health));
    }
}

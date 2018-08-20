package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class SnakeHead extends GameEntity implements Animatable {

    private static final float speed = 2;
    private static final float turnRate = 2;
    public boolean leftKeyDown = false;
    public boolean rightKeyDown = false;
    private GameEntity tail = this; // the last element. Needed to know where to add the next part.
    private int health = 100;
    private boolean player;

    public SnakeHead(Pane pane, int xc, int yc, boolean player) {
        super(pane);
        setX(xc);
        setY(yc);
        if (player) setImage(Globals.snakeHead);
        else setImage(Globals.secSnakeHead);
        pane.getChildren().add(this);
        this.player = player;

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
            System.out.println("Game Over");
            Globals.gameLoop.stop();
        }
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            Image image;
            if (player) image = Globals.snakeBody;
            else image = Globals.secSnakeBody;

            tail = new SnakeBody(pane, tail, image);
        }
    }

    public void changeHealth(int diff) {
        health += diff;
    }
}

package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

public class SimpleEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int DAMAGE = 10;
    private static final int DISTANCE_FROM_SNAKE = 50;
    private static final int SPEED = 1;

    public SimpleEnemy(Pane pane) {
        super(pane);

        setImage(Globals.simpleEnemy);
        pane.getChildren().add(this);
        Random rnd = new Random();
        setPosition(rnd);
        setDirection(rnd);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
            new SimpleEnemy(pane);
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-DAMAGE);
        destroy();
    }

    @Override
    public String getMessage() {
        return DAMAGE + " damage";
    }

    private void setPosition(Random rnd) {
        double newX = 0, newY = 0;
        while (Math.abs(Math.round(newX) - Math.round(Globals.snake.getX())) < DISTANCE_FROM_SNAKE
                && Math.abs(Math.round(newY) - Math.round(Globals.snake.getY())) < DISTANCE_FROM_SNAKE
                || newX == 0 && newY == 0) {
            newX = (rnd.nextDouble() * Globals.WINDOW_WIDTH);
            newY = (rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        }
        setX(newX);
        setY(newY);
    }

    private void setDirection(Random rnd) {
        double direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, SPEED);
    }
}

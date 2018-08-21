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

// a simple enemy TODO make better ones.
public class SimpleEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;

    public SimpleEnemy(Pane pane) {
        super(pane);

        setImage(Globals.simpleEnemy);
        pane.getChildren().add(this);
        int speed = 1;
        Random rnd = new Random();
        double newX = 0, newY = 0;
        while (Math.round(newX) == Math.round(Globals.snake.getX())
                && Math.round(newY) == Math.round(Globals.snake.getY())
                || newX == 0 && newY == 0) {
            newX = (rnd.nextDouble() * Globals.WINDOW_WIDTH);
            newY = (rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        }
        setX(newX);
        setY(newY);
        double direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
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
        if (!player.isInvincible) {
            player.changeHealth(-damage);
        }
        player.isInvincible = false;
        destroy();
    }

    @Override
    public String getMessage() {
        if (!Globals.snake.isInvincible) {
            return "10 damage";
        } else {
            return "Invincible";
        }
    }
}

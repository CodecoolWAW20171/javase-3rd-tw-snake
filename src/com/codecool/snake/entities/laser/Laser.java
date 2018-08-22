package com.codecool.snake.entities.laser;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class Laser extends GameEntity implements Animatable, Interactable {

    private Point2D heading;

    public Laser(Pane pane) {
        super(pane);

        setImage(Globals.laser);
        pane.getChildren().add(this);
        int speed = 3;

        setX(Globals.snake.getX());
        setY(Globals.snake.getY());
        double direction = Globals.snake.getRotate();
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof SimpleEnemy) {
                    entity.destroy();
                    destroy();
                }
            }
        }
    }

    @Override
    public void apply(SnakeHead player) {

    }

    @Override
    public String getMessage() {
        return "PIUŁ PIUŁ";
    }
}

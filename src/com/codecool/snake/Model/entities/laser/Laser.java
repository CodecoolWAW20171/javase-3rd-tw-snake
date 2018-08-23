package com.codecool.snake.Model.entities.laser;

import com.codecool.snake.Model.Globals;
import com.codecool.snake.Controler.Utils;
import com.codecool.snake.Model.entities.Animatable;
import com.codecool.snake.Model.entities.GameEntity;
import com.codecool.snake.Model.entities.Interactable;
import com.codecool.snake.Model.entities.enemies.SimpleEnemy;
import com.codecool.snake.Model.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Laser extends GameEntity implements Animatable, Interactable {

    private Point2D heading;

    public Laser(Pane pane, SnakeHead snake) {
        super(pane);

        setImage(new Image("laser.png"));
        pane.getChildren().add(this);
        float speed = snake.speed + 1;

        setX(snake.getX());
        setY(snake.getY());
        double direction = snake.getRotate();
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
    public void apply(SnakeHead snakeHead) {

    }

    @Override
    public String getMessage() {
        return "PIUŁ PIUŁ";
    }
}

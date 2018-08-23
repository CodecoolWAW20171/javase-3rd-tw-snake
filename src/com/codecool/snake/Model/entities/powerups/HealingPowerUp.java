package com.codecool.snake.Model.entities.powerups;

import com.codecool.snake.Model.Globals;
import com.codecool.snake.Model.entities.GameEntity;
import com.codecool.snake.Model.entities.Interactable;
import com.codecool.snake.Model.entities.snakes.SnakeHead;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.Random;

// a healing powerUp that heals the snake
public class HealingPowerUp extends GameEntity implements Interactable {

    public HealingPowerUp(Pane pane) {
        super(pane);
        setImage(new Image("med_pack.png"));
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        int SNAKE_STARTING_HEALTH = 100;
        if (snakeHead.getHealth() != SNAKE_STARTING_HEALTH) {
            int HEALING_POINTS = 10;
            snakeHead.changeHealth(HEALING_POINTS);
        }
        destroy();
    }

    @Override
    public String getMessage() {
        return "Got healing power-up :)";
    }
}

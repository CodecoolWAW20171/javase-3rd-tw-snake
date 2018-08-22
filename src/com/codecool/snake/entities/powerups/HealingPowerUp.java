package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

// a healing powerUp that heals the snake
public class HealingPowerUp extends GameEntity implements Interactable {

    private static final int SNAKE_STARTING_HEALTH = 100;
    private static final int HEALING_POINTS = 10;

    public HealingPowerUp(Pane pane) {
        super(pane);
        setImage(Globals.medPack);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        if (snakeHead.getHealth() != SNAKE_STARTING_HEALTH) {
            snakeHead.changeHealth(HEALING_POINTS);
        }
        destroy();
    }

    @Override
    public String getMessage() {
        return "Got healing power-up :)";
    }
}

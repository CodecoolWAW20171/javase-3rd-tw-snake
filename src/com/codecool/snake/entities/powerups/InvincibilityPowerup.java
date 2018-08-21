package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

public class InvincibilityPowerup extends GameEntity implements Interactable {

    private static final int scoreBoost = 10;

    public InvincibilityPowerup(Pane pane) {
        super(pane);
        setImage(Globals.pill);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.score += scoreBoost;
        destroy();
    }

    @Override
    public String getMessage() {
        return "Got score power-up :)";
    }
}

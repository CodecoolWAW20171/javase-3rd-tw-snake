package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

// a healing powerup that heals the snake
public class HealingPowerup extends GameEntity implements Interactable {

    public HealingPowerup(Pane pane) {
        super(pane);
        setImage(Globals.medPack);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        if (snakeHead.getHealth() != 100) {
            snakeHead.changeHealth(10);
        }
        destroy();
        new HealingPowerup(this.pane);
    }

    @Override
    public String getMessage() {
        return "Got healing power-up :)";
    }
}

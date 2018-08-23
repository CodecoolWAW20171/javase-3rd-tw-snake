package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.Random;

public class SimplePowerUp extends GameEntity implements Interactable {

    public SimplePowerUp(Pane pane) {
        super(pane);
        setImage(new Image("power_up_berry.png"));
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.addPart(1);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Got berry power-up :)";
    }
}

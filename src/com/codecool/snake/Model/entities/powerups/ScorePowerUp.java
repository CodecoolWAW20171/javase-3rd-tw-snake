package com.codecool.snake.Model.entities.powerups;

import com.codecool.snake.Model.Globals;
import com.codecool.snake.Model.entities.GameEntity;
import com.codecool.snake.Model.entities.Interactable;
import com.codecool.snake.Model.entities.snakes.SnakeHead;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.Random;

public class ScorePowerUp extends GameEntity implements Interactable {

    public ScorePowerUp(Pane pane) {
        super(pane);
        setImage(new Image("pill.png"));
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        int SCORE_BOOST = 10;
        snakeHead.changeScore(SCORE_BOOST);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Got score power-up :)";
    }
}

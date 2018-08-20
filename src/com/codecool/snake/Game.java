package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Game extends Pane {

    public Game() {
        Globals.snake = new SnakeHead(this, 750, 500);
        Globals.secSnake = new SnakeHead(this, 250, 500);

        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);

        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
    }

    public void start() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.snake.leftKeyDown = true; break;
                case RIGHT: Globals.snake.rightKeyDown  = true; break;
                case A: Globals.secSnake.leftKeyDown = true; break;
                case D: Globals.secSnake.rightKeyDown = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.snake.leftKeyDown = false; break;
                case RIGHT: Globals.snake.rightKeyDown = false; break;
                case A: Globals.secSnake.leftKeyDown = false; break;
                case D: Globals.secSnake.rightKeyDown = false; break;
            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }
}

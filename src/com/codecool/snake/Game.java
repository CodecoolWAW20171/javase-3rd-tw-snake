package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

class Game extends Pane {

    private static final int NUM_OF_ENEMIES = 5;
    private static final int NUM_OF_BERRIES = 2;
    private static final int SNAKE_Y_POSITION = 500;
    private static final int PLAYER_1_X_POSITION_SINGLE = 500;
    private static final int PLAYER_1_X_POSITION_MULTI = 750;
    private static final int PLAYER_2_X_POSITION = 250;

    void start() {
        spawnSnakes();
        spawnEnemies();
        spawnBerries();

        Globals.scene = getScene();
        handleOnKeyPressed();
        handleOnKeyReleased();

        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }

    private void spawnSnakes() {
        int newPos;
        if (Globals.singlePlayer) {
            newPos = PLAYER_1_X_POSITION_SINGLE;
        } else {
            newPos = PLAYER_1_X_POSITION_MULTI;
            Globals.secSnake = new SnakeHead(this, PLAYER_2_X_POSITION, SNAKE_Y_POSITION, false);
            Globals.secSnake.setName("Player 2");
        }
        Globals.snake = new SnakeHead(this, newPos, SNAKE_Y_POSITION, true);
        Globals.snake.setName("Player 1");
    }

    private void spawnEnemies() {
        for (int i = 0; i < NUM_OF_ENEMIES; i++) {
            new SimpleEnemy(this);
        }
    }

    private void spawnBerries() {
        for (int i = 0; i < NUM_OF_BERRIES; i++) {
            new SimplePowerUp(this);
        }
    }

    private void handleOnKeyPressed() {
        Globals.scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.snake.leftKeyDown = true; break;
                case RIGHT: Globals.snake.rightKeyDown  = true; break;
                case A: if(!Globals.singlePlayer) Globals.secSnake.leftKeyDown = true; break;
                case D: if(!Globals.singlePlayer) Globals.secSnake.rightKeyDown = true; break;
                case P: Globals.gameLoop.pause(); break;
                case R: Globals.gameLoop.restart(); break;
            }
        });
    }

    private void handleOnKeyReleased() {
        Globals.scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.snake.leftKeyDown = false; break;
                case RIGHT: Globals.snake.rightKeyDown = false; break;
                case A: if(!Globals.singlePlayer) Globals.secSnake.leftKeyDown = false; break;
                case D: if(!Globals.singlePlayer) Globals.secSnake.rightKeyDown = false; break;
            }
        });
    }
}

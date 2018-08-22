package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

class Game extends Pane {

    private static final int NUM_OF_ENEMIES = 5;
    private static final int NUM_OF_BERRIES = 2;

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
        if(Globals.singleplayer) {
            Globals.snake = new SnakeHead(this, 500, 500, true);
            Globals.snake.setName("Player 1");
        }
        else {
            Globals.snake = new SnakeHead(this, 750, 500, true);
            Globals.snake.setName("Player 1");
            Globals.secSnake = new SnakeHead(this, 250, 500, false);
            Globals.secSnake.setName("Player 2");
        }
    }

    private void spawnEnemies() {
        for (int i = 0; i < NUM_OF_ENEMIES; i++) {
            new SimpleEnemy(this);
        }
    }

    private void spawnBerries() {
        for (int i = 0; i < NUM_OF_BERRIES; i++) {
            new SimplePowerup(this);
        }
    }

    private void handleOnKeyPressed() {
        Globals.scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.snake.leftKeyDown = true; break;
                case RIGHT: Globals.snake.rightKeyDown  = true; break;
                case A: if(!Globals.singleplayer) Globals.secSnake.leftKeyDown = true; break;
                case D: if(!Globals.singleplayer) Globals.secSnake.rightKeyDown = true; break;
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
                case A: if(!Globals.singleplayer) Globals.secSnake.leftKeyDown = false; break;
                case D: if(!Globals.singleplayer) Globals.secSnake.rightKeyDown = false; break;
            }
        });
    }
}

package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.laser.Laser;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

class Game extends Pane {

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
        int SNAKE_Y_POSITION = 500;
        int PLAYER_1_X_POSITION_SINGLE = 500;
        int PLAYER_1_X_POSITION_MULTI = 750;
        if (Globals.singlePlayer) {
            newPos = PLAYER_1_X_POSITION_SINGLE;
        } else {
            newPos = PLAYER_1_X_POSITION_MULTI;
            int PLAYER_2_X_POSITION = 250;
            Globals.secSnake = new SnakeHead(this, PLAYER_2_X_POSITION, SNAKE_Y_POSITION, false);
            Globals.secSnake.setName("Player 2");
        }
        Globals.snake = new SnakeHead(this, newPos, SNAKE_Y_POSITION, true);
        Globals.snake.setName("Player 1");
    }

    private void spawnEnemies() {
        int NUM_OF_ENEMIES = 5;
        for (int i = 0; i < NUM_OF_ENEMIES; i++) {
            new SimpleEnemy(this);
        }
    }

    private void spawnBerries() {
        int NUM_OF_BERRIES = 2;
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
                case UP: Globals.addGameObject(new Laser(Globals.gamePane, Globals.snake)); break;
                case W: if(!Globals.singlePlayer) {Globals.addGameObject(new Laser(Globals.gamePane, Globals.secSnake));} break;
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

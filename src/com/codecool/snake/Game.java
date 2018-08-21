package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Game extends Pane {

    public Game() {
        Globals.snake = new SnakeHead(this, 500, 500);

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
        Globals.scene = getScene();
        Globals.scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.snake.leftKeyDown = true; break;
                case RIGHT: Globals.snake.rightKeyDown  = true; break;
                case P: Globals.gameLoop.pause();
            }
        });

        Globals.scene.setOnKeyReleased(event -> {
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

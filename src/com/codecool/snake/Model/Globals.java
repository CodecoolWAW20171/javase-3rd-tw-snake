package com.codecool.snake.Model;

import com.codecool.snake.Controler.GameLoop;
import com.codecool.snake.Model.entities.GameEntity;
import com.codecool.snake.Model.entities.snakes.SnakeHead;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Globals {

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects;
    public static List<GameEntity> oldGameObjects;
    public static GameLoop gameLoop;
    public static SnakeHead snake;
    public static SnakeHead secSnake;
    public static Pane gamePane;
    public static boolean isGamePaused = false;
    public static boolean singlePlayer;

    public static Stage stage;
    public static VBox vBox;
    public static MenuBar menuBar;
    public static Menu menuHealth;

    static {
        gameObjects = new LinkedList<>();
        newGameObjects = new LinkedList<>();
        oldGameObjects = new LinkedList<>();
    }

    public static void addGameObject(GameEntity toAdd) {
        newGameObjects.add(toAdd);
    }

    public static void removeGameObject(GameEntity toRemove) {
        oldGameObjects.add(toRemove);
    }

    public static List<GameEntity> getGameObjects() {
        return Collections.unmodifiableList(gameObjects);
    }
}

package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// class for holding all static stuff
public class Globals {

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public static Image snakeHead = new Image("snake_head.png");
    public static Image secSnakeHead = new Image("sec_snake_head.png");
    public static Image snakeBody = new Image("snake_body.png");
    public static Image secSnakeBody = new Image("sec_snake_body.png");
    public static Image simpleEnemy = new Image("simple_enemy.png");
    public static Image powerupBerry = new Image("powerup_berry.png");
    public static Image medPack = new Image("med_pack.png");
    public static Image pill = new Image("pill.png");
    //.. put here the other images you want to use

    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    public static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static GameLoop gameLoop;
    public static SnakeHead snake;
    public static SnakeHead secSnake;
    public static Pane gamePane;
    public static boolean isGamePaused = false;

    public static Stage stage;
    public static Scene scene;
    public static VBox vBox;
    public static MenuBar menuBar;
    public static Menu menu;
    public static Menu menuInfo;
    public static Menu menuHealth;

    public static MenuItem menuRestartItem;
    public static MenuItem menuPauseItem;
    public static MenuItem menuCloseItem;

    public static int health;

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

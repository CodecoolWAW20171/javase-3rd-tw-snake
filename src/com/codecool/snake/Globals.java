package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.entities.laser.Laser;
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
import java.util.ListIterator;

public class Globals {

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public static Image snakeHead = new Image("snake_head.png");
    public static Image secSnakeHead = new Image("sec_snake_head.png");
    public static Image snakeBody = new Image("snake_body.png");
    public static Image secSnakeBody = new Image("sec_snake_body.png");
    public static Image simpleEnemy = new Image("simple_enemy.png");
    public static Image powerUpBerry = new Image("power_up_berry.png");
    public static Image medPack = new Image("med_pack.png");
    public static Image pill = new Image("pill.png");
    public static Image laser = new Image("laser.png");
    //.. put here the other images you want to use

    static List<GameEntity> gameObjects;
    static List<GameEntity> newGameObjects;
    static List<GameEntity> oldGameObjects;
    static GameLoop gameLoop;
    public static SnakeHead snake;
    static SnakeHead secSnake;
    static Pane gamePane;
    static boolean isGamePaused = false;
    static boolean singlePlayer;

    static Stage stage;
    public static Scene scene;
    static VBox vBox;
    static MenuBar menuBar;
    static Menu menu;
    static Menu menuInfo;
    static Menu menuHealth;

    static MenuItem menuRestartItem;
    static MenuItem menuModeItem;
    static MenuItem menuPauseItem;
    static MenuItem menuCloseItem;

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

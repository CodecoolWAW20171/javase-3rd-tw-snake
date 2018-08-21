package com.codecool.snake;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game();
        Globals.gamePane = game;
        Globals.stage = primaryStage;

        GameMenu gameMenu = new GameMenu();
        gameMenu.generateMenu(game);
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(new Scene(Globals.vBox, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
        primaryStage.show();

        Modals modal = new Modals();
        Alert alert = modal.selectGameMode(game);

        Platform.runLater(alert::showAndWait);
    }
}

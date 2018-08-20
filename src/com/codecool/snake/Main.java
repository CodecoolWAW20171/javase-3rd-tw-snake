package com.codecool.snake;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game();
        Globals.stage = primaryStage;
        Globals.vBox = new VBox();
        Globals.menuBar = new MenuBar();
        Globals.menuRestart = new Menu("Restart");
        Globals.menuPause = new Menu("Pause");
        Globals.menuClose = new Menu("Close");
        Globals.menuBar.getMenus().addAll(Globals.menuRestart, Globals.menuPause, Globals.menuClose);
        Globals.vBox.getChildren().addAll(Globals.menuBar, game);

        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(new Scene(Globals.vBox , Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
        primaryStage.show();
        game.start();
    }
}

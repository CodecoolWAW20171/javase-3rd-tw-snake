package com.codecool.snake;

import javafx.application.Application;
import javafx.scene.Scene;
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
        Globals.vBox = new VBox();
        Globals.menuBar = new MenuBar();
        Globals.menu = new Menu("Menu");

        Globals.menuRestartItem = new MenuItem("Restart");
        Globals.menuPauseItem = new MenuItem("Pause");
        Globals.menuCloseItem = new MenuItem("Close");
        String info = "<- Move Left, -> Move Right, P - pause, R - restart";
        Globals.menuInfo = new Menu(info);
        Globals.menuInfo.setDisable(true);

        Globals.menuCloseItem.setOnAction(event -> {
            System.exit(0);
        });

        Globals.menuPauseItem.setOnAction(event -> {
            Globals.gameLoop.pause();
            if ( Globals.menuPauseItem.getText().equals("Pause")) {
                Globals.menuPauseItem.setText("Resume");
            } else {
                Globals.menuPauseItem.setText("Pause");
            }
        });

        Globals.menuRestartItem.setOnAction(event -> {
            Globals.gameLoop.restart();
        });

        Globals.menu.getItems().setAll(Globals.menuRestartItem, Globals.menuPauseItem, Globals.menuCloseItem);


        Globals.menuBar.getMenus().addAll(Globals.menu, Globals.menuInfo);
        Globals.vBox.getChildren().addAll(Globals.menuBar, game);

        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(new Scene(Globals.vBox, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
        primaryStage.show();
        game.start();
    }
}

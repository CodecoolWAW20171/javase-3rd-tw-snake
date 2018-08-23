package com.codecool.snake.View;

import com.codecool.snake.Controler.Game;
import com.codecool.snake.Model.Globals;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class MainFrame {

    public MainFrame(Stage primaryStage) {
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

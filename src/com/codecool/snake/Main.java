package com.codecool.snake;

import com.codecool.snake.View.MainFrame;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        new MainFrame(primaryStage);
    }
}

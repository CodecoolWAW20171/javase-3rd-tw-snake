package com.codecool.snake;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

class Modals {

    private Alert createAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        return alert;
    }

    private String highScoreText() {
        return Globals.singlePlayer ?
                "Your score: " + Globals.snake.score :
                "Green snake: " + Globals.snake.score + "\nPink snake: " + Globals.secSnake.score;
    }

    Alert showGameOverModal() {
        Alert alert = createAlert("Game Over", highScoreText(), "Would you like to try again?");

        ButtonType yesButton = new ButtonType("YES");
        ButtonType noButton = new ButtonType("NO");

        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(noButton, yesButton);

        alert.getDialogPane().lookupButton(yesButton).setOnMouseReleased(event -> Globals.gameLoop.restart());

        alert.getDialogPane().lookupButton(noButton).setOnMouseReleased(event -> alert.close());

        return alert;
    }

    Alert selectGameMode(Game game) {
        Alert alert = createAlert("Game Mode", "Game Mode", "Select Game Mode");

        ButtonType singlePlayerModeButton = new ButtonType("Single Player");
        ButtonType twoPlayersModeButton = new ButtonType("Versus Mode");
        
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(singlePlayerModeButton, twoPlayersModeButton);

        alert.getDialogPane().lookupButton(twoPlayersModeButton).setOnMouseReleased(event -> {
            Globals.singlePlayer = false;
            start(game);
        });

        alert.getDialogPane().lookupButton(singlePlayerModeButton).setOnMouseReleased(event -> {
            Globals.singlePlayer = true;
            start(game);
        });

        return alert;
    }

    private void start(Game game) {
        try {
            Globals.gameLoop.stop();
            game.start();
        } catch (NullPointerException e) {
            game.start();
        }
    }
}

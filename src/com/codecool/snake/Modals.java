package com.codecool.snake;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Modals {

    public Alert showGameOverModal() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        System.out.println("Game Over");
        alert.setTitle("Game Over");
        alert.setHeaderText("Game Over");
        alert.setContentText("Your Health:\n" + Globals.health + "\nDo you want try again?");

        ButtonType yesButton = new ButtonType("YES");
        ButtonType noButton = new ButtonType("NO");

        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(noButton, yesButton);

        alert.getDialogPane().lookupButton(yesButton).setOnMouseReleased(event -> {
            Globals.gameLoop.restart();
        });

        alert.getDialogPane().lookupButton(noButton).setOnMouseReleased(event -> {
            alert.close();
        });

        return alert;
    }

}

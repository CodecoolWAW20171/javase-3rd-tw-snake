package com.codecool.snake;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

class GameMenu {
    void generateMenu(Game game) {
        Globals.vBox = new VBox();
        Globals.menuBar = new MenuBar();
        Globals.menu = new Menu("Menu");

        Globals.menuRestartItem = new MenuItem("Restart");
        Globals.menuPauseItem = new MenuItem("Pause");
        Globals.menuModeItem = new MenuItem("Mode");
        Globals.menuCloseItem = new MenuItem("Close");
        String info = "<- Move Left, -> Move Right, P - pause, R - restart";
        Globals.menuInfo = new Menu(info);
        Globals.menuInfo.setDisable(false);

        Globals.menuHealth = new Menu();
        Globals.menuHealth.setDisable(false);

        Globals.menuCloseItem.setOnAction(event -> System.exit(0));

        Globals.menuPauseItem.setOnAction(event -> {
            Globals.gameLoop.pause();
            if ( Globals.menuPauseItem.getText().equals("Pause")) {
                Globals.menuPauseItem.setText("Resume");
            } else {
                Globals.menuPauseItem.setText("Pause");
            }
        });

        Globals.menuRestartItem.setOnAction(event -> Globals.gameLoop.restart());

        Globals.menuModeItem.setOnAction(event -> {
            Modals modal = new Modals();
            try {
                Globals.gameLoop.stop();
            } catch (NullPointerException e) {
                System.out.println(e);
            }
            Alert alert = modal.selectGameMode(game);
            Platform.runLater(alert::showAndWait);
        });

        Globals.menu.getItems().setAll(Globals.menuRestartItem, Globals.menuModeItem, Globals.menuPauseItem, Globals.menuCloseItem);


        Globals.menuBar.getMenus().addAll(Globals.menu, Globals.menuInfo, Globals.menuHealth);
        Globals.vBox.getChildren().addAll(Globals.menuBar, game);
    }
}

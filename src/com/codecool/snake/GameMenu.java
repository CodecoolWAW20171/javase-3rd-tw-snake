package com.codecool.snake;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

class GameMenu {

    void generateMenu(Game game) {
        Globals.vBox = new VBox();
        Globals.menuBar = new MenuBar();
        Menu menu = new Menu("Menu");

        MenuItem menuRestartItem = new MenuItem("Restart");
        MenuItem menuPauseItem = new MenuItem("Pause");
        MenuItem menuModeItem = new MenuItem("Mode");
        MenuItem menuCloseItem = new MenuItem("Close");
        String info = "<- Move Left, -> Move Right, P - pause, R - restart";
        Menu menuInfo = new Menu(info);
        menuInfo.setDisable(false);

        Globals.menuHealth = new Menu();
        Globals.menuHealth.setDisable(false);

        menuCloseItem.setOnAction(event -> System.exit(0));

        menuPauseItem.setOnAction(event -> {
            Globals.gameLoop.pause();
            if (menuPauseItem.getText().equals("Pause")) {
                menuPauseItem.setText("Resume");
            } else {
                menuPauseItem.setText("Pause");
            }
        });

        menuRestartItem.setOnAction(event -> Globals.gameLoop.restart());

        menuModeItem.setOnAction(event -> {
            Modals modal = new Modals();
            try {
                Globals.gameLoop.stop();
            } catch (NullPointerException e) {
                System.out.println(e);
            }
            Alert alert = modal.selectGameMode(game);
            Platform.runLater(alert::showAndWait);
        });

        menu.getItems().setAll(menuRestartItem, menuModeItem, menuPauseItem, menuCloseItem);


        Globals.menuBar.getMenus().addAll(menu, menuInfo, Globals.menuHealth);
        Globals.vBox.getChildren().addAll(Globals.menuBar, game);
    }
}

package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;


public class GameLoop extends AnimationTimer {

    // This gets called every 1/60 seconds
    @Override
    public void handle(long now) {
        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject instanceof Animatable) {
                Animatable animObject = (Animatable)gameObject;
                animObject.step();
            }
        }
        Globals.gameObjects.addAll(Globals.newGameObjects);
        Globals.newGameObjects.clear();

        Globals.gameObjects.removeAll(Globals.oldGameObjects);
        Globals.oldGameObjects.clear();
    }

    @Override
    public void stop() {
        super.stop();
        Globals.snake.destroy();
    }

    void pause() {
        if(!Globals.isGamePaused) {
            super.stop();
            Globals.isGamePaused = !Globals.isGamePaused;
        } else {
            super.start();
            Globals.isGamePaused = !Globals.isGamePaused;
        }
    }

    public void restart() {
        Globals.gameLoop.stop();
        Game game = new Game();
        Globals.vBox = new VBox();
        Globals.vBox.getChildren().addAll(Globals.menuBar, game);
        Globals.stage.setScene(new Scene(Globals.vBox, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
        Globals.stage.show();
        game.start();
    }

}

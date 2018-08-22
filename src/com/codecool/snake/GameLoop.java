package com.codecool.snake;

import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.HealingPowerup;
import com.codecool.snake.entities.powerups.ScorePowerup;
import com.codecool.snake.entities.powerups.SimplePowerup;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import java.util.Random;

public class GameLoop extends AnimationTimer {

    private static final int chanceForSimpleEnemiesAndPowerups = 1000;
    private static final int chanceIn1000ForSimpleEnemiesAndPowerups = 2;
    private static final int chanceIn1000ForBetterPowerups = 1;
    private static final int DISTANCE_BETWEEN_SNAKE_HEADS = 15;

    // This gets called every 1/60 seconds
    @Override
    public void handle(long now) {
        runAnimations();
        checkSnakesCollisions();
        checkGameOverStatus();
        generateRandomPowerups();
        updateHealthBarStatus();
        updateGameObjectsStatus();
    }

    private void updateGameObjectsStatus() {
        Globals.gameObjects.addAll(Globals.newGameObjects);
        Globals.newGameObjects.clear();

        Globals.gameObjects.removeAll(Globals.oldGameObjects);
        Globals.oldGameObjects.clear();
    }

    private void updateHealthBarStatus() {
        if (!Globals.singleplayer) {
            Globals.menuHealth.setText("Health of " + Globals.snake.getName() + ": " + Globals.snake.getHealth() +
                    " Health of " + Globals.secSnake.getName() + ": " + Globals.secSnake.getHealth());
        } else {
            Globals.menuHealth.setText("Health of " + Globals.snake.getName() + ": " + Globals.snake.getHealth());
        }
    }

    private void runAnimations() {
        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject instanceof Animatable) {
                Animatable animObject = (Animatable)gameObject;
                animObject.step();
            }
        }
    }

    private void checkSnakesCollisions() {
        if (Globals.secSnake != null)
            if ((Math.abs(Globals.snake.getY() - Globals.secSnake.getY()) < DISTANCE_BETWEEN_SNAKE_HEADS) &&
                    (Math.abs(Globals.snake.getX() - Globals.secSnake.getX()) < DISTANCE_BETWEEN_SNAKE_HEADS)) {
                handleGameOver();
            }
    }

    private void checkGameOverStatus() {
        if (Globals.snake.getHealth() <= 0)
            if (Globals.secSnake != null) {
                if (Globals.secSnake.getHealth() <= 0)
                    handleGameOver();
            } else {
                handleGameOver();
            }
    }

    private void handleGameOver() {
        Globals.gameLoop.stop();
        Modals modals = new Modals();

        Platform.runLater(modals.showGameOverModal()::showAndWait);
        Globals.gameLoop.stop();
    }

    private void generateRandomPowerups() {
        Random rand = new Random();
        if (rand.nextInt(chanceForSimpleEnemiesAndPowerups) < chanceIn1000ForSimpleEnemiesAndPowerups) {
            Globals.addGameObject(new SimpleEnemy(Globals.gamePane));
        } if (rand.nextInt(chanceForSimpleEnemiesAndPowerups) < chanceIn1000ForSimpleEnemiesAndPowerups) {
            Globals.addGameObject((new SimplePowerup(Globals.gamePane)));
        } if (rand.nextInt(chanceForSimpleEnemiesAndPowerups) < chanceIn1000ForBetterPowerups) {
            Globals.addGameObject(new HealingPowerup(Globals.gamePane));
        } if (rand.nextInt(chanceForSimpleEnemiesAndPowerups) < chanceIn1000ForBetterPowerups) {
            Globals.addGameObject(new ScorePowerup(Globals.gamePane));
        }
    }

    public void stop() {
        super.stop();
        Globals.gamePane.getChildren().clear();
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

    void restart() {
        Globals.gameLoop.stop();
        Game game = new Game();
        Globals.vBox = new VBox();
        Globals.vBox.getChildren().addAll(Globals.menuBar, game);
        Globals.stage.setScene(new Scene(Globals.vBox, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
        Globals.stage.show();
        game.start();
    }

}

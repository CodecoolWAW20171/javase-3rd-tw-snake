package com.codecool.snake.Controler;

import com.codecool.snake.Model.Globals;
import com.codecool.snake.Model.entities.Animatable;
import com.codecool.snake.Model.entities.GameEntity;
import com.codecool.snake.Model.entities.enemies.SimpleEnemy;
import com.codecool.snake.Model.entities.powerups.HealingPowerUp;
import com.codecool.snake.Model.entities.powerups.ScorePowerUp;
import com.codecool.snake.Model.entities.powerups.SimplePowerUp;
import com.codecool.snake.View.Modals;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import java.util.Random;

public class GameLoop extends AnimationTimer {

    // This gets called every 1/60 seconds
    @Override
    public void handle(long now) {
        runAnimations();
        checkSnakesCollision();
        checkGameOverStatus();
        generateRandomPowerUps();
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
        if (!Globals.singlePlayer) {
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

    private void checkSnakesCollision() {
        int DISTANCE_BETWEEN_SNAKE_HEADS = 20;
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

    private void generateRandomPowerUps() {
        Random rand = new Random();
        int CHANCE_FOR_SIMPLE_ENEMIES_AND_POWER_UPS = 1000;
        int CHANCE_IN_1000_FOR_SIMPLE_ENEMIES_AND_POWER_UPS = 2;
        if (rand.nextInt(CHANCE_FOR_SIMPLE_ENEMIES_AND_POWER_UPS) < CHANCE_IN_1000_FOR_SIMPLE_ENEMIES_AND_POWER_UPS) {
            Globals.addGameObject(new SimpleEnemy(Globals.gamePane));
        } if (rand.nextInt(CHANCE_FOR_SIMPLE_ENEMIES_AND_POWER_UPS) < CHANCE_IN_1000_FOR_SIMPLE_ENEMIES_AND_POWER_UPS) {
            Globals.addGameObject((new SimplePowerUp(Globals.gamePane)));
        }
        int CHANCE_IN_1000_FOR_BETTER_POWER_UPS = 1;
        if (rand.nextInt(CHANCE_FOR_SIMPLE_ENEMIES_AND_POWER_UPS) < CHANCE_IN_1000_FOR_BETTER_POWER_UPS) {
            Globals.addGameObject(new HealingPowerUp(Globals.gamePane));
        } if (rand.nextInt(CHANCE_FOR_SIMPLE_ENEMIES_AND_POWER_UPS) < CHANCE_IN_1000_FOR_BETTER_POWER_UPS) {
            Globals.addGameObject(new ScorePowerUp(Globals.gamePane));
        }
    }

    public void stop() {
        super.stop();
        Globals.gamePane.getChildren().clear();
        Globals.snake.destroy();
    }

    public void pause() {
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

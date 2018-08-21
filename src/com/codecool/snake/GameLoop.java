package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.HealingPowerup;
import com.codecool.snake.entities.powerups.InvincibilityPowerup;
import com.codecool.snake.entities.powerups.SimplePowerup;
import javafx.animation.AnimationTimer;

import java.util.Random;

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

        Random rand = new Random();
        if (rand.nextInt(1000) < 2) {
            Globals.addGameObject(new SimpleEnemy(Globals.gamePane));
        } if (rand.nextInt(1000) < 2) {
            Globals.addGameObject((new SimplePowerup(Globals.gamePane)));
        } if (rand.nextInt(1000) < 1) {
            Globals.addGameObject(new HealingPowerup(Globals.gamePane));
        } if (rand.nextInt(1500) < 1) {
            Globals.addGameObject(new InvincibilityPowerup(Globals.gamePane));
        }
        Globals.gameObjects.addAll(Globals.newGameObjects);
        Globals.newGameObjects.clear();

        Globals.gameObjects.removeAll(Globals.oldGameObjects);
        Globals.oldGameObjects.clear();
    }
}

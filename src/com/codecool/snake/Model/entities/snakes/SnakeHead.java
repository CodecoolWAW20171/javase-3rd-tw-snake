package com.codecool.snake.Model.entities.snakes;

import com.codecool.snake.Model.Globals;
import com.codecool.snake.Controler.Utils;
import com.codecool.snake.Model.entities.Animatable;
import com.codecool.snake.Model.entities.GameEntity;
import com.codecool.snake.Model.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

public class SnakeHead extends GameEntity implements Animatable {

    private static float STARTING_SPEED = 1.5f;
    private static float STARTING_TURN_RATE = 1.5f;
    public static float speed;
    private static float turnRate;
    private final int STARTING_HEALTH = 100;
    private int health = STARTING_HEALTH;
    public int score = 0;
    public boolean leftKeyDown;
    public boolean rightKeyDown;
    private GameEntity tail = this;
    private boolean player;
    private ArrayList<GameEntity> body = new ArrayList<>();
    private String name;


    public SnakeHead(Pane pane, int xc, int yc, boolean player) {
        super(pane);
        setX(xc);
        setY(yc);
        if (player) setImage(new Image("snake_head.png"));
        else setImage(new Image("sec_snake_head.png"));
        pane.getChildren().add(this);
        this.player = player;
        speed = STARTING_SPEED;
        turnRate = STARTING_TURN_RATE;

        int NUMBER_OF_PARTS_TO_ADD_AT_START = 4;
        addPart(NUMBER_OF_PARTS_TO_ADD_AT_START);
    }

    public void step() {
        if (health != 0) {
            moveToNewPos();
            checkCollisionWithGameObjects();
            checkGameOverCondition();
        }
    }

    private double setDirection() {
        double dir = getRotate();
        if (leftKeyDown) return dir - turnRate;
        if (rightKeyDown) return dir + turnRate;
        return dir;
    }

    private void moveToNewPos() {
        double direction = setDirection();
        setRotate(direction);
        Point2D heading = Utils.directionToVector(direction, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    private void checkCollisionWithGameObjects() {
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }
    }

    private void checkGameOverCondition() {
        if (isOutOfBounds() || health <= 0) {
            health = 0;
            for (GameEntity entity : Globals.getGameObjects())
                if ((body.indexOf(entity) != -1) || entity.equals(this)) {
                    Globals.removeGameObject(entity);
                    entity.destroy();
                }
        }
    }


    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            score++;
            Image image;
            if (player) image = new Image("snake_body.png");
            else image = new Image("sec_snake_body.png");
            tail = new SnakeBody(pane, tail, image);
            body.add(tail);
        }
    }

    public int getHealth() {
        return this.health;
    }

    public void changeHealth(int diff) {
        health += diff;
    }

    public void changeSpeed(){
        float changeRatio = 0.1f;
        speed += changeRatio;
        turnRate += changeRatio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

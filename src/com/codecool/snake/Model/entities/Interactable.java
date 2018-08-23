package com.codecool.snake.Model.entities;

import com.codecool.snake.Model.entities.snakes.SnakeHead;

// interface that all game objects that can be interacted with must implement.
public interface Interactable {

    void apply(SnakeHead snakeHead);

    String getMessage();

}
package com.codecool.snake.Controler;

import javafx.geometry.Point2D;

public class Utils {

    /*
    Converts a direction in degrees (0...360) to x and y coordinates.
    The length of this vector is the second parameter
    */
    public static Point2D directionToVector(double directionInDegrees, double length) {
        double directionInRadians = directionInDegrees / 180 * Math.PI;
        return new Point2D(length * Math.sin(directionInRadians), - length * Math.cos(directionInRadians));
    }
}
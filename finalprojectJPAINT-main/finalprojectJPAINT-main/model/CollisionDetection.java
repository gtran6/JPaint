package model;

import java.awt.*;

/*
var rect1 = {x: 5, y: 5, width: 50, height: 50}
var rect2 = {x: 20, y: 10, width: 10, height: 10}

if (rect1.x < rect2.x + rect2.width &&
   rect1.x + rect1.width > rect2.x &&
   rect1.y < rect2.y + rect2.height &&
   rect1.y + rect1.height > rect2.y) {
    // collision detected!
}
 */
public class CollisionDetection {
    public static boolean detectCollision(Shape shape, Shape diffShape) {
        Rectangle rectangle1 = shape.getBounds();
        Rectangle rectangle2 = diffShape.getBounds();
        boolean collisionDetected = false;
        if (rectangle1.x < rectangle2.x + rectangle2.width
                && rectangle2.x < rectangle1.x + rectangle1.width
                && rectangle1.y < rectangle2.y + rectangle2.height
                && rectangle2.y < rectangle1.y + rectangle1.height) {
            collisionDetected = true;
        }
        return collisionDetected;
    }
}

package model.strategy;

import java.awt.*;
import java.util.ArrayList;

public interface IMasterShape {

    void paintOnCanvas();

    void setGraphics2D(Graphics2D graphics2D);

    void create();

    void deleteShape();

    //selected shape list
    void selectedShape();

    ArrayList<IMasterShape> getArrayList();

    /*
    translate this by an amount dx in the x direction and dy in the y direction
    dx translation in x direction
    dy translation in y direction
     */
    void moveShape(int dx, int dy);

    IMasterShape copyShape();

    IMasterShape pasteShape();

    boolean detectCollision(Shape diffShape);

    Shape getBoundingBox();
}


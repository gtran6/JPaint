package model.composite;

import java.awt.*;

public class DrawBoundingBox implements ISelectionBox {
    private static DrawBoundingBox instance;
    private Shape boundingBox;

    private DrawBoundingBox() {
    }

    public static synchronized DrawBoundingBox getInstance() {
        if (instance == null) {
            instance = new DrawBoundingBox();}
        return instance;
    }

    public Shape fromPoints(Point startPoint, Point endPoint) {
        ShapeBoundingBox shapeBoundingBox = new ShapeBoundingBox();
        this.boundingBox = shapeBoundingBox.fromPoints(startPoint,endPoint);
        return this.boundingBox;
    }

    public Shape getBoundingBox() {
        return boundingBox;
    }

    public void drawBoundingBox(Graphics2D graphics2D) {
    }
}

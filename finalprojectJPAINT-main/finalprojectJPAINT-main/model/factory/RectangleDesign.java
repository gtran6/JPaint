package model.factory;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class RectangleDesign implements IDesignShape {
    int x;
    int y;
    int width;
    int height;

    public RectangleDesign(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public Shape design() {
        return new Rectangle2D.Double(x, y, width, height);
    }
}

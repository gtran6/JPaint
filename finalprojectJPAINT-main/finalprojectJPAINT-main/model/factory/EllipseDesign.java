package model.factory;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class EllipseDesign implements IDesignShape {
    int x;
    int y;
    int width;
    int height;

    public EllipseDesign(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public Shape design() {
        return new Ellipse2D.Double( x, y, width, height );
    }
}

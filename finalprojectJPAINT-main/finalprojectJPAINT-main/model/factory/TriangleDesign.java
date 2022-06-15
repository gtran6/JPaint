package model.factory;

import java.awt.*;
import java.awt.geom.Path2D;
/*
equilateral triangle:
int height = 200 * Math.cos(Math.PI / 6);
triangle.moveTo(100, 300);
triangle.lineTo(300, 300);
triangle.lineTo(200, 300 - height);
triangle.closePath();
 */

public class TriangleDesign implements IDesignShape {
    private Point startPoint;
    private Point endPoint;

    public TriangleDesign(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    @Override
    public Shape design() {
        Path2D triangle = new Path2D.Double();
        triangle.moveTo( startPoint.x, endPoint.y );
        triangle.lineTo( endPoint.x, endPoint.y );
        triangle.lineTo((startPoint.x + endPoint.x)/2, endPoint.y - (((startPoint.y + endPoint.y)/2)*Math.cos( Math.PI / 6 )));
        triangle.closePath();
        return triangle;
    }
}

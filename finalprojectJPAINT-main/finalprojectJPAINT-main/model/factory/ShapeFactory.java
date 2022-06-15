package model.factory;

import model.ShapeType;
import model.builder.ShapeBuilder;
import model.factory.EllipseDesign;
import model.factory.IDesignShape;
import model.factory.RectangleDesign;
import model.factory.TriangleDesign;

import java.awt.*;

public class ShapeFactory {

    public static Shape getShape(ShapeBuilder shapeBuilder) {
        ShapeType shapeType = shapeBuilder.getShapeType();
        Integer adjustX = shapeBuilder.getAdjustStart();
        Integer adjustY = shapeBuilder.getAdjustEnd();
        Integer width = shapeBuilder.getWidth();
        Integer height = shapeBuilder.getHeight();
        Point startPoint = shapeBuilder.getStartPoint();
        Point endPoint = shapeBuilder.getEndPoint();
        Shape shape = null;
        IDesignShape designShape = null;

        if (shapeType.equals( ShapeType.RECTANGLE )) {
            designShape = new RectangleDesign(adjustX, adjustY, width, height);
        } else if (shapeType.equals( ShapeType.ELLIPSE )) {
            designShape = new EllipseDesign(adjustX, adjustY, width, height);
        } else if (shapeType.equals( ShapeType.TRIANGLE )) {
            designShape = new TriangleDesign(startPoint, endPoint);
        }
        return shape = designShape.design();
    }
}

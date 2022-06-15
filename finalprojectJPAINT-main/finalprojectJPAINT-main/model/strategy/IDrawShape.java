package model.strategy;

import model.builder.ShapeBuilder;

import java.awt.*;

public interface IDrawShape {
    void designShape();

    void draw();

    void setShape(Shape shape);

    void updateShapeBuilder(Shape newShape);

    ShapeBuilder getShapeBuilder();

    Shape getShape();
}

package model.builder;

import model.ShapeShadingType;
import model.ShapeType;
import model.factory.IShapeFactory;
import model.factory.NullShapeFactory;
import model.factory.ShadingShapeFactory;

import java.awt.*;

/*
The X and Y coordinates of the rectangle are the smallest of the initial point and the current point.
The width and height of the rectangle are the distance between the coordinates of the initial point
and the current point on each axis, usually called delta or Δ.
X = min(X1, X2)
Y = min(Y1, Y2)
Width = abs(X1 - X2)
Height = abs(Y1 - Y2)
drawRect(x, y, width, height)
https://observablehq.com/@danburzo/drawing-svg-rectangles
 */

public class ShapeBuilder {
    private Point startPoint;
    private Point endPoint;
    private ShapeType shapeType;
    private ShapeShadingType shadingType;
    private Color primaryColor;
    private Color secondaryColor;
    private int topLeftX;
    private int topLeftY;
    private int width;
    private int height;

    public ShapeBuilder(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.topLeftX = Math.min( startPoint.x, endPoint.x );
        this.topLeftY = Math.min( startPoint.y, endPoint.y );
        this.width = Math.abs( endPoint.x - startPoint.x );
        this.height = Math.abs( endPoint.y - startPoint.y );
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public ShapeBuilder setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
        return this;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public ShapeBuilder setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
        return this;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public ShapeBuilder setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
        return this;
    }

    public ShapeShadingType getShapeShadingType() {
        return shadingType;
    }

    public ShapeBuilder setShapeShadingType(ShapeShadingType shadingType) {
        this.shadingType = shadingType;
        return this;
    }

    public Color getPrimaryColor() {
        return primaryColor;
    }

    public ShapeBuilder setPrimaryColor(Color primaryColor) {
        this.primaryColor = primaryColor;
        return this;
    }

    public Color getSecondaryColor() {
        return secondaryColor;
    }

    public ShapeBuilder setSecondaryColor(Color secondaryColor) {
        this.secondaryColor = secondaryColor;
        return this;
    }

    public Integer getAdjustStart() {
        return topLeftX;
    }

    public ShapeBuilder setAdjustStart(Integer adjustX) {
        this.topLeftX = adjustX;
        return this;
    }

    public Integer getAdjustEnd() {
        return topLeftY;
    }

    public ShapeBuilder setAdjustEnd(Integer adjustY) {
        this.topLeftY = adjustY;
        return this;
    }

    public Integer getWidth() {
        return width;
    }

    public ShapeBuilder setWidth(Integer width) {
        this.width = width;
        return this;
    }

    public Integer getHeight() {
        return height;
    }

    public ShapeBuilder setHeight(Integer height) {
        this.height = height;
        return this;
    }

}

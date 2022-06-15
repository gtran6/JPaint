package model.composite;

import model.ShapeType;
import model.builder.ShapeBuilder;
import model.factory.ShapeFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

//https://doc.qt.io/qt-5/qrect.html

public class ShapeBoundingBox implements ISelectionBox {
    ArrayList<Shape> list;
    Shape boundingBox;

    public ShapeBoundingBox(ArrayList<Shape> list) {
        this.list = list;
        updateBoundingBox();
    }

    public ShapeBoundingBox() {}

    private void updateBoundingBox() {
        this.boundingBox = fromList(list);
    }

    public Shape fromList(ArrayList<Shape> list) {
        ArrayList<Integer> topX = new ArrayList<>();
        ArrayList<Integer> topY = new ArrayList<>();
        ArrayList<Integer> bottomX = new ArrayList<>();
        ArrayList<Integer> bottomY = new ArrayList<>();

        for (Shape shape : list) {
            Rectangle rectBound = shape.getBounds();
            topX.add(rectBound.x);//left-top
            topY.add(rectBound.y);//right-top
            bottomX.add(rectBound.x + rectBound.width);//left-bottom
            bottomY.add(rectBound.y + rectBound.height);//right-bottom
        }
        Point startPoint = new Point(Collections.min(topX), Collections.min(topY));
        Point endPoint = new Point(Collections.max(bottomX), Collections.max(bottomY));
        return fromPoints(startPoint, endPoint);
    }

    public Shape fromPoints(Point startPoint, Point endPoint) {
        ShapeBuilder shapeBuilder = new ShapeBuilder( startPoint, endPoint );
        shapeBuilder.setShapeType( ShapeType.RECTANGLE );
        Shape shape = ShapeFactory.getShape( shapeBuilder );
        return shape;
    }

    public Shape getBoundingBox() {
        return this.boundingBox;
    }
}

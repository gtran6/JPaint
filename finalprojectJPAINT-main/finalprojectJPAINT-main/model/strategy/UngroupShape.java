package model.strategy;

import model.ShapeSubjects;
import model.command.IUndoable;
import model.composite.DrawBoundingBox;
import model.strategy.GroupShape;
import model.strategy.IMasterShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
immutable list collector
List shapes = ...
List boxes = shapes.stream().
						 filter(s -> s.getColor() == BLUE).
						 map(s -> s.getContainingBox()).
						 collect(Collectors.toList());
 */

public class UngroupShape implements IUndoable {
    PaintCanvasBase paintCanvas;
    List<GroupShape> list;

    public UngroupShape(PaintCanvasBase paintCanvas) {
        this.paintCanvas = paintCanvas;
        ArrayList<IMasterShape> list1 = ShapeSubjects.getShapeList.getArrayList();
        DrawBoundingBox boundingBox = DrawBoundingBox.getInstance();
        Shape boundingBoxShape = boundingBox.getBoundingBox();
        list = list1.stream()
                .filter(GroupShape.class::isInstance)
                .map(GroupShape.class::cast)
                .filter( shape->shape.detectCollision( boundingBoxShape ) )
                .collect( Collectors.toList() );
    }

    public void ungroup() {
        for (GroupShape groupShape: list) {
            groupShape.unGroup();
        }
        paintCanvas.repaint();
    }

    @Override
    public void undo() {
        for (GroupShape groupShape: list) {
            groupShape.create();
        }
        paintCanvas.repaint();
    }

    @Override
    public void redo() {
        ungroup();
    }
}


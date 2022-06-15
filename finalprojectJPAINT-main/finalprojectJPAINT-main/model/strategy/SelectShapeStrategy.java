package model.strategy;

import model.ShapeSubjects;
import model.composite.DrawBoundingBox;
import model.interfaces.IApplicationState;
import model.command.IUndoable;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class SelectShapeStrategy implements IUndoable {
    private Point startPoint;
    private Point endPoint;
    private Graphics2D graphics2D;
    private IApplicationState applicationState;
    private PaintCanvasBase paintCanvas;

    public SelectShapeStrategy(Point startPoint, Point endPoint, PaintCanvasBase paintCanvas, IApplicationState applicationState) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.paintCanvas = paintCanvas;
        this.applicationState = applicationState;
        this.graphics2D = paintCanvas.getGraphics2D();
    }

    public void operate() {
        //clear the selected shapes in the shape list
        ShapeSubjects.getSelectedShapeList.clear();
        //ArrayList<IMasterShape> list = ShapeSubjects.getShapeList.getArrayList();
        DrawBoundingBox drawBoundingBox = DrawBoundingBox.getInstance();
        drawBoundingBox.fromPoints( startPoint, endPoint );
        Shape x = drawBoundingBox.getBoundingBox();
        for (IMasterShape shape: ShapeSubjects.getShapeList.getArrayList()) {
            if (shape.detectCollision( x )) {
                ShapeSubjects.getSelectedShapeList.add( shape );
            }
        }
        paintCanvas.repaint();
    }

    @Override
    public void undo() {
        ShapeSubjects.getSelectedShapeList.clear();
        paintCanvas.repaint();
    }

    @Override
    public void redo() {
        operate();
        paintCanvas.repaint();
    }
}

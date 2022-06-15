package model.strategy;

import model.ShapeSubjects;
import model.interfaces.IApplicationState;
import model.command.IUndoable;
import model.observer.Observer;
import model.observer.PaintCanvasObserver;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class MoveShapeStrategy implements IUndoable {
    private Point startPoint;
    private Point endPoint = new Point();
    private PaintCanvasBase paintCanvas;
    private IApplicationState applicationState;
    private Graphics2D graphics2D;
    private Point movePosition;
    PaintCanvasObserver paintCanvasObserver;

    public MoveShapeStrategy(Point startPoint, Point movePosition,
                             PaintCanvasBase paintCanvas,
                             IApplicationState applicationState,
                             PaintCanvasObserver paintCanvasObserver) {
        this.startPoint = startPoint;
        this.movePosition = movePosition;
        this.paintCanvas = paintCanvas;
        this.graphics2D = paintCanvas.getGraphics2D();
        this.applicationState = applicationState;
        this.paintCanvasObserver = paintCanvasObserver;
        paintCanvasObserver.attach( (Observer) paintCanvas );
    }

    public void move() {
        translate(movePosition.x, movePosition.y);
    }

    public void translate(int x, int y) {
        AffineTransform.getTranslateInstance( x, y );
        for (IMasterShape shape: ShapeSubjects.getSelectedShapeList.getArrayList()) {
            shape.moveShape( x,y );
        }
        paintCanvasObserver.notifyAllObserver();
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public void undo() { translate( startPoint.x - endPoint.x, startPoint.y - endPoint.y);
    }

    @Override
    public void redo() {
        translate( endPoint.x - startPoint.x, endPoint.y - startPoint.y);
    }
}

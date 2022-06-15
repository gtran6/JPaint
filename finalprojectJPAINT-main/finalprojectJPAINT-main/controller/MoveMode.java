package controller;

import model.command.MoveShapeCommand;
import model.interfaces.IApplicationState;
import model.observer.PaintCanvasObserver;
import model.strategy.MoveShapeStrategy;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class MoveMode implements Mode {
    private Point startPoint;
    private Point movePosition;
    private PaintCanvasBase paintCanvas;
    private IApplicationState applicationState;
    PaintCanvasObserver observer = new PaintCanvasObserver();
    MoveShapeStrategy moveShape;


    public MoveMode(Point startPoint, Point movePosition, PaintCanvasBase paintCanvas, IApplicationState applicationState) {
        this.movePosition = movePosition;
        this.startPoint = startPoint;
        this.paintCanvas = paintCanvas;
        this.applicationState = applicationState;
    }

    @Override
    public void operate() {
        moveShape = new MoveShapeStrategy( startPoint, movePosition, paintCanvas, applicationState, observer);
        moveShape.move();
    }

    public void move(Point endPoint) {
        moveShape.setEndPoint( endPoint );
        MoveShapeCommand moveShapeCommand = new MoveShapeCommand(moveShape);
        moveShapeCommand.run();
    }
}

package controller;

import model.command.SelectShapeCommand;
import model.interfaces.IApplicationState;
import model.strategy.SelectShapeStrategy;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class SelectMode implements Mode {
    private Point startPoint;
    private Point endPoint;
    private PaintCanvasBase paintCanvas;
    private IApplicationState applicationState;

    public SelectMode(Point startPoint, Point endPoint,
                      PaintCanvasBase paintCanvas,
                      IApplicationState applicationState) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.paintCanvas = paintCanvas;
        this.applicationState = applicationState;
    }

    @Override
    public void operate() {
        SelectShapeStrategy selectShape = new SelectShapeStrategy( startPoint, endPoint, paintCanvas, applicationState );
        SelectShapeCommand selectShapeCommand = new SelectShapeCommand( selectShape );
        selectShapeCommand.run();
    }
}

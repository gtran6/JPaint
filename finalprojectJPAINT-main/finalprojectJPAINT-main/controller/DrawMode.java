package controller;

import model.ShapeShadingType;
import model.ShapeType;
import model.builder.ShapeBuilder;
import model.command.CreateShapeCommand;
import model.interfaces.IApplicationState;
import model.persistence.ColorSingleton;
import model.strategy.DrawShapeStrategy;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class DrawMode implements Mode {
    private Point startPoint;
    private Point endPoint;
    private PaintCanvasBase paintCanvas;
    private IApplicationState applicationState;
    ShapeBuilder shapeBuilder;

    public DrawMode(Point startPoint, Point endPoint, PaintCanvasBase paintCanvas, IApplicationState applicationState) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.paintCanvas = paintCanvas;
        this.applicationState = applicationState;

        ShapeType shapeType = applicationState.getActiveShapeType();
        ShapeShadingType shapeShadingType = applicationState.getActiveShapeShadingType();
        Color primaryColor = ColorSingleton.getColor(applicationState.getActivePrimaryColor());
        Color secondaryColor = ColorSingleton.getColor(applicationState.getActiveSecondaryColor());

        shapeBuilder = new ShapeBuilder( startPoint, endPoint );
        shapeBuilder
                .setShapeType( shapeType )
                .setShapeShadingType( shapeShadingType )
                .setPrimaryColor( primaryColor )
                .setSecondaryColor( secondaryColor );
    }
    @Override
    public void operate() {
        if (!startPoint.equals( endPoint )) {
            DrawShapeStrategy drawShape = new DrawShapeStrategy( paintCanvas, shapeBuilder );
            CreateShapeCommand createShapeCommand = new CreateShapeCommand( drawShape );
            createShapeCommand.run();
        }
    }
}

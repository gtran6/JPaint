package view.gui;

import model.ShapeSubjects;
import model.observer.Observer;
import model.strategy.IMasterShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class PaintCanvas extends PaintCanvasBase implements Observer{
    Shape shape = null;

    public Graphics2D getGraphics2D() {
        return (Graphics2D) getGraphics();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent( g ); //paint background
        Graphics2D graphics2D = (Graphics2D) g;
        for (IMasterShape shape : ShapeSubjects.getShapeList.getArrayList()) {
            shape.setGraphics2D( graphics2D );
            shape.paintOnCanvas();
            if(ShapeSubjects.getSelectedShapeList.getArrayList().contains( shape )) {
                shape.selectedShape();
            }
        }
    }

    @Override
    public void update() {
        this.repaint();
    }
}

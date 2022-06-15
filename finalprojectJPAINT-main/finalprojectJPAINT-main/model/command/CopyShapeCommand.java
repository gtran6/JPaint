package model.command;

import model.ShapeSubjects;
import model.strategy.IMasterShape;
import view.interfaces.PaintCanvasBase;

import java.util.ArrayList;

public class CopyShapeCommand implements ICommand {
    private PaintCanvasBase paintCanvas;
    ArrayList<IMasterShape> shapes = ShapeSubjects.getClipboardShapeList.getArrayList();

    public CopyShapeCommand(PaintCanvasBase paintCanvas) {
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void run() {
        shapes.clear();
        for (IMasterShape shape: ShapeSubjects.getSelectedShapeList.getArrayList()) {
            IMasterShape copyShape = shape.copyShape();
            shapes.add( copyShape );
        }
    }
}

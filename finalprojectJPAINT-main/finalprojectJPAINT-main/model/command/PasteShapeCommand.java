package model.command;

import model.ShapeSubjects;
import model.command.IUndoable;
import model.strategy.IMasterShape;
import view.interfaces.PaintCanvasBase;

import java.util.ArrayList;

public class PasteShapeCommand implements ICommand, IUndoable {
    ArrayList<IMasterShape> shapes = ShapeSubjects.getShapeList.getArrayList();
    ArrayList<IMasterShape> pastedShapes = new ArrayList<>();
    private PaintCanvasBase paintCanvas;

    public PasteShapeCommand(PaintCanvasBase paintCanvas) {
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void run() {
        pastedShapes.clear();
        for (IMasterShape shape: ShapeSubjects.getClipboardShapeList.getArrayList()) {
            IMasterShape pastedShape = shape.pasteShape();
            shapes.add(pastedShape);
            pastedShapes.add( pastedShape );
        }
        paintCanvas.repaint();
        CommandHistory.add( this );
    }

    @Override
    public void undo() {
        for(IMasterShape shape: pastedShapes) {
            shapes.removeAll( shape.getArrayList() );
        }
        paintCanvas.repaint();
    }

    @Override
    public void redo() {
        for(IMasterShape shape: pastedShapes) {
            shapes.add( shape );
        }
        paintCanvas.repaint();
    }
}

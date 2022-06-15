package model.command;

import model.ShapeSubjects;
import model.command.IUndoable;
import model.strategy.IMasterShape;
import view.interfaces.PaintCanvasBase;

import java.util.ArrayList;

public class DeleteShapeCommand implements ICommand, IUndoable {
    private PaintCanvasBase paintCanvas;
    ArrayList<IMasterShape> shapes = new ArrayList<IMasterShape>();

    public DeleteShapeCommand(PaintCanvasBase paintCanvas) {
        this.paintCanvas = paintCanvas;
        shapes.addAll( ShapeSubjects.getSelectedShapeList.getArrayList() );
    }

    @Override
    public void run() {
        for (IMasterShape shape: shapes) {
            shape.deleteShape();
        }
        paintCanvas.repaint();
        CommandHistory.add( this );
    }

    @Override
    public void undo() {
        for (IMasterShape shape: shapes) {
            shape.create();
        }
        paintCanvas.repaint();
    }

    @Override
    public void redo() {
        run();
    }
}

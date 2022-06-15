package model.command;

import model.ShapeSubjects;
import model.command.IUndoable;
import model.strategy.MoveShapeStrategy;

public class MoveShapeCommand implements ICommand, IUndoable {
    MoveShapeStrategy moveShape;

    public MoveShapeCommand(MoveShapeStrategy moveShape) {
        this.moveShape = moveShape;
    }

    @Override
    public void run() {
        boolean res = !ShapeSubjects.getSelectedShapeList.getArrayList().isEmpty();
        if (res) {
            moveShape.move();
            CommandHistory.add( this );
        }
    }

    @Override
    public void undo() {
        moveShape.undo();
    }

    @Override
    public void redo() {
        moveShape.redo();
    }
}

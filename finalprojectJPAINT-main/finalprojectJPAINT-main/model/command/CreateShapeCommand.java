package model.command;

import model.command.IUndoable;
import model.strategy.IDrawShape;

public class CreateShapeCommand implements ICommand, IUndoable {
    IDrawShape drawShape;

    public CreateShapeCommand(IDrawShape shape) {
        this.drawShape = shape;
    }

    @Override
    public void run() {
        drawShape.draw();
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        ((IUndoable) drawShape).undo();
    }

    @Override
    public void redo() {
        ((IUndoable) drawShape).redo();
    }
}

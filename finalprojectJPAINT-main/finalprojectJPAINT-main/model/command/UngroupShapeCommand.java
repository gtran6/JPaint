package model.command;

import model.strategy.UngroupShape;

public class UngroupShapeCommand implements ICommand, IUndoable {
    UngroupShape ungroupShape;

    public UngroupShapeCommand(UngroupShape ungroupShape) {
        this.ungroupShape = ungroupShape;
    }

    @Override
    public void run() {
        ungroupShape.ungroup();
        CommandHistory.add( this );
    }

    @Override
    public void undo() {
        ungroupShape.undo();
    }

    @Override
    public void redo() {
        ungroupShape.redo();
    }
}

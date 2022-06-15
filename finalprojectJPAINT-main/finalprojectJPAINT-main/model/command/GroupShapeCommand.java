package model.command;

import model.ShapeSubjects;
import model.strategy.GroupShape;

public class GroupShapeCommand implements ICommand, IUndoable {
    GroupShape groupShape;

    public GroupShapeCommand(GroupShape groupShape) {
        this.groupShape = groupShape;
    }

    @Override
    public void run() {
        boolean res = !ShapeSubjects.getSelectedShapeList.getArrayList().isEmpty();
        if (res) {
            groupShape.group();
            CommandHistory.add( this );
        }
    }

    @Override
    public void undo() {
        groupShape.undo();
    }

    @Override
    public void redo() {
        groupShape.redo();
    }
}

package model.command;

import model.strategy.SelectShapeStrategy;

public class SelectShapeCommand implements ICommand {
    SelectShapeStrategy selectShape;

    public SelectShapeCommand(SelectShapeStrategy selectShape) {
        this.selectShape = selectShape;
    }

    @Override
    public void run() {
        selectShape.operate();
    }
}

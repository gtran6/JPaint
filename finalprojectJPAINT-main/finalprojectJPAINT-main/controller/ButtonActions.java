package controller;

import model.command.*;
import model.strategy.GroupShape;
import model.strategy.UngroupShape;
import view.EventName;
import view.interfaces.IUiModule;
import view.interfaces.PaintCanvasBase;

public class ButtonActions implements IJPaintController{
    private IUiModule uiModule;
    private PaintCanvasBase paintCanvas;
    //PaintCanvasObserver observer;

    public ButtonActions(IUiModule uiModule, PaintCanvasBase paintCanvas) {
        this.uiModule = uiModule;
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent( EventName.GROUP, ()->groupAction() );
        uiModule.addEvent( EventName.UNGROUP, ()->ungroupAction() );
    }

    private void ungroupAction() {
        UngroupShape ungroupShape = new UngroupShape( paintCanvas );
        UngroupShapeCommand command = new UngroupShapeCommand(ungroupShape);
        command.run();
    }

    private void groupAction() {
        GroupShape groupShape = new GroupShape( paintCanvas );
        GroupShapeCommand command = new GroupShapeCommand(groupShape);
        command.run();
    }
}

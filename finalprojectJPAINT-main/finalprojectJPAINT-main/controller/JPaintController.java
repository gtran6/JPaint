package controller;

import model.command.*;
import model.interfaces.IApplicationState;
import model.observer.PaintCanvasObserver;
import view.EventName;
import view.interfaces.IUiModule;
import view.interfaces.PaintCanvasBase;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    PaintCanvasObserver observer;
    PaintCanvasBase paintCanvas;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, PaintCanvasObserver observer, PaintCanvasBase paintCanvas) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.observer = observer;
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_MOUSE_MODE, () -> applicationState.setActiveStartAndEndPointMode());
        uiModule.addEvent( EventName.UNDO, () -> new UndoCommand( observer).run() );
        uiModule.addEvent( EventName.REDO, () -> new RedoCommand( observer ).run() );
        uiModule.addEvent( EventName.COPY, () -> new CopyShapeCommand( paintCanvas ).run() );
        uiModule.addEvent( EventName.PASTE, () -> new PasteShapeCommand(paintCanvas).run() );
        uiModule.addEvent( EventName.DELETE, () -> new DeleteShapeCommand( paintCanvas ).run() );
    }
}

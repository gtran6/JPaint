package model.command;

import model.observer.PaintCanvasObserver;

public class RedoCommand implements ICommand {
    PaintCanvasObserver observer;

    public RedoCommand(PaintCanvasObserver observer) {
        this.observer = observer;
    }
    @Override
    public void run() {
        CommandHistory.redo();
        observer.notifyAllObserver();
    }
}

package model.command;

import model.observer.PaintCanvasObserver;

public class UndoCommand implements ICommand {
    PaintCanvasObserver observer;

    public UndoCommand(PaintCanvasObserver observer) {
        this.observer = observer;
    }

    @Override
    public void run(){
        CommandHistory.undo();
        observer.notifyAllObserver();
    }
}

package model.observer;

import java.util.ArrayList;

public class PaintCanvasObserver implements Subject{
    ArrayList<Observer> observers = new ArrayList<Observer>();

    public PaintCanvasObserver() {
    }

    @Override
    public void attach(Observer observer) {
        if(!observers.contains( observer )) {
            observers.add( observer );
        }
    }

    @Override
    public void detach(Observer observer) {
        observers.remove( observer );
    }

    @Override
    public void notifyAllObserver() {
        for (Observer observer: observers) {
            observer.update();
        }
    }

    @Override
    public void clear() {
        observers.clear();
    }
}

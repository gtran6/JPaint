package model;

import model.strategy.IMasterShape;
import model.strategy.IMasterShape;

import java.util.ArrayList;

public class ShapeList {
    private final ArrayList<IMasterShape> shapeList = new ArrayList<>();
    public ArrayList<IMasterShape> getArrayList() {
        return shapeList;
    }

    public void remove(IMasterShape shape) {
        shapeList.remove( shape );
    }

    public void clear() {
        shapeList.clear();
    }

    public void add(IMasterShape shape) {
        if(!shapeList.contains( shape )) {
            shapeList.add( shape );
        }
    }

    public void removeAll(ArrayList<IMasterShape> list) {
        shapeList.removeAll( list );
    }
}

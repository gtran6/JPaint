package model.composite;

import java.awt.*;

public interface ISelectionBox {
    public Shape fromPoints(Point startPoint, Point endPoint);
    public Shape getBoundingBox();
}

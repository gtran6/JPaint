package model.factory;

import java.awt.*;

public class OutlineShapeFactory implements IShapeFactory {
    Color primaryColor;
    Shape shape;
    Stroke stroke = new BasicStroke(4);
    private Graphics2D graphics2D;

    public OutlineShapeFactory(Color primaryColor, Shape shape, Graphics2D graphics2D) {
        this.primaryColor = primaryColor;
        this.shape = shape;
        this.graphics2D = graphics2D;
    }

    @Override
    public void create() {
        graphics2D.setPaint(primaryColor);
        graphics2D.setStroke( stroke );
        graphics2D.draw( shape );
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }
}

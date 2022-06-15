package model.factory;

import model.factory.IShapeFactory;

import java.awt.*;

public class OutlineAndFilledInShapeFactory implements IShapeFactory {
    Color primaryColor;
    Color secondaryColor;
    Shape shape;
    BasicStroke stroke = new BasicStroke(4);
    private Graphics2D graphics2D;

    public OutlineAndFilledInShapeFactory(Color primaryColor, Color secondaryColor, Shape shape, Graphics2D graphics2D) {
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.shape = shape;
        this.graphics2D = graphics2D;
    }

    @Override
    public void create() {
        graphics2D.setStroke( stroke );
        graphics2D.setPaint( secondaryColor );
        graphics2D.draw( shape );
        graphics2D.setPaint( primaryColor );
        graphics2D.fill( shape );
    }
}

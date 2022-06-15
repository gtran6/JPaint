package model.factory;

import java.awt.*;

public class FilledInShapeFactory implements IShapeFactory {
    Color primaryColor;
    Shape shape;
    private Graphics2D graphics2D;

    public FilledInShapeFactory(Color primaryColor, Shape shape, Graphics2D graphics2D) {
        this.primaryColor = primaryColor;
        this.shape = shape;
        this.graphics2D = graphics2D;
    }

    @Override
    public void create() {
        graphics2D.setPaint( primaryColor );
        graphics2D.fill( shape );
    }
}

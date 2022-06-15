package model.factory;

import java.awt.*;

public class ShadingShapeFactory {
    public static IShapeFactory filledInShape(Color primaryColor, Shape shape, Graphics2D graphics2D) {
        return new FilledInShapeFactory( primaryColor, shape, graphics2D );
    }

    public static IShapeFactory outlineShape(Color primaryColor, Shape shape, Graphics2D graphics2D) {
        return new OutlineShapeFactory( primaryColor, shape, graphics2D );
    }

    public static IShapeFactory outlineAndFilledInShape(Color primaryColor, Color secondaryColor, Shape shape, Graphics2D graphics2D) {
        return new OutlineAndFilledInShapeFactory( primaryColor, secondaryColor, shape, graphics2D );
    }
}

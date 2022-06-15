package model.persistence;

import model.ShapeColor;
import java.awt.*;
import java.util.EnumMap;

public class ColorSingleton {
    private static EnumMap<ShapeColor, java.awt.Color> colorEnumMap = new EnumMap<ShapeColor, Color>(ShapeColor.class);

    static {
        colorEnumMap.put( ShapeColor.BLACK, Color.BLACK );
        colorEnumMap.put( ShapeColor.BLUE, Color.BLUE );
        colorEnumMap.put( ShapeColor.CYAN, Color.CYAN );
        colorEnumMap.put( ShapeColor.DARK_GRAY, Color.DARK_GRAY );
        colorEnumMap.put( ShapeColor.GRAY, Color.GRAY );
        colorEnumMap.put( ShapeColor.GREEN, Color.GREEN );
        colorEnumMap.put( ShapeColor.LIGHT_GRAY, Color.LIGHT_GRAY );
        colorEnumMap.put( ShapeColor.MAGENTA, Color.MAGENTA );
        colorEnumMap.put( ShapeColor.ORANGE, Color.ORANGE );
        colorEnumMap.put( ShapeColor.PINK, Color.PINK);
        colorEnumMap.put( ShapeColor.RED, Color.RED );
        colorEnumMap.put( ShapeColor.WHITE, Color.WHITE );
        colorEnumMap.put( ShapeColor.YELLOW, Color.YELLOW );
    }
    public static Color getColor(ShapeColor shapeColor) {
        return colorEnumMap.get( shapeColor );
    }
}

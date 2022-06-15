package model.strategy;

import model.*;
import model.builder.ShapeBuilder;
import model.factory.*;
import model.command.IUndoable;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collections;

/* source:
https://www.codejava.net/java-se/graphics/drawing-rectangles-examples-with-graphics2d
http://geom-java.sourceforge.net/api/math/geom2d/polygon/Rectangle2D.html
*/

public class DrawShapeStrategy implements IDrawShape, IMasterShape, IUndoable {
    private PaintCanvasBase paintCanvas;
    private ShapeBuilder shapeBuilder;
    Shape shape = null;
    private Graphics2D graphics2D;
    ArrayList<Shape> list;


    public DrawShapeStrategy(IDrawShape iDrawShape, PaintCanvasBase paintCanvas) {
        ShapeBuilder sb= iDrawShape.getShapeBuilder();
        shapeBuilder = new ShapeBuilder(sb.getStartPoint(), sb.getEndPoint());
        shapeBuilder.setShapeType( sb.getShapeType());
        shapeBuilder.setShapeShadingType(sb.getShapeShadingType());
        shapeBuilder.setPrimaryColor(sb.getPrimaryColor());
        shapeBuilder.setSecondaryColor(sb.getSecondaryColor());

        this.paintCanvas = paintCanvas;
        this.graphics2D = paintCanvas.getGraphics2D();
        designShape();
    }

    public DrawShapeStrategy(PaintCanvasBase paintCanvas, ShapeBuilder shapeBuilder) {
        this.paintCanvas = paintCanvas;
        this.graphics2D = paintCanvas.getGraphics2D();
        this.shapeBuilder = shapeBuilder;
        designShape();
    }
    @Override
    public void undo() {
        deleteShape();
    }

    @Override
    public void redo() {
        create();
    }

    @Override
    public void designShape() {
        shape = ShapeFactory.getShape( shapeBuilder );
    }

    @Override
    public void draw() {
        create();
        paintCanvas.repaint();
    }

    @Override
    public void setShape(Shape shape) {
        this.shape = shape;
        updateShapeBuilder( shape );
    }

    @Override
    public void updateShapeBuilder(Shape newShape) {
        Rectangle rectangle = newShape.getBounds();
        Point newStartPoint = new Point(rectangle.x, rectangle.y);
        Point newEndPoint = new Point((rectangle.width + rectangle.x), (rectangle.height + rectangle.y));
        this.shapeBuilder.setStartPoint( newStartPoint );
        this.shapeBuilder.setEndPoint( newEndPoint );
    }

    @Override
    public ShapeBuilder getShapeBuilder() {
        return shapeBuilder;
    }

    @Override
    public void paintOnCanvas() {
        ShapeShadingType shadingType = shapeBuilder.getShapeShadingType();
        IShapeFactory shapeFactory = new NullShapeFactory();
        if (shadingType.equals( ShapeShadingType.FILLED_IN )) {
            shapeFactory = ShadingShapeFactory.filledInShape( shapeBuilder.getPrimaryColor(), shape, graphics2D);
        }
        else if (shadingType.equals( ShapeShadingType.OUTLINE )) {
            shapeFactory = ShadingShapeFactory.outlineShape( shapeBuilder.getPrimaryColor(), shape, graphics2D );
        }
        else if (shadingType.equals( ShapeShadingType.OUTLINE_AND_FILLED_IN )) {
            shapeFactory = ShadingShapeFactory.outlineAndFilledInShape( shapeBuilder.getPrimaryColor(), shapeBuilder.getSecondaryColor(), shape, graphics2D );
        }
        shapeFactory.create();
    }

    @Override
    public void setGraphics2D(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
    }

    @Override
    public void deleteShape() {
        ShapeSubjects.getShapeList.remove(this);
    }

    @Override
    public void selectedShape() {
        //when selecting,
        //assume any shape (e.g. ellipse or triangle) has an invisible
        //bounding box that surrounds the shape
        OutlineShapeFactory outlineShapeFactory = new OutlineShapeFactory( Color.BLACK, shape, graphics2D );
        outlineShapeFactory.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0));
        //float[] dashingPattern = {10, 10, 1, 10};
        //outlineShapeFactory.setStroke(new BasicStroke(3, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1, dashingPattern, 0));
        outlineShapeFactory.create();
    }

    @Override
    public void moveShape(int dx, int dy) {
        //AffineTransform.createTransformedShape(Shape pSrc)
        //Returns a new Shape object defined by the geometry of the specified Shape after it has been transformed by this transform.
        //AffineTransform.getTranslateInstance( x, y );
        AffineTransform transform = new AffineTransform();
        transform.translate( dx, dy );
        Shape transformShape = transform.createTransformedShape( this.getShape() );
        this.setShape(transformShape);
    }

    public Shape getShape() {
        return shape;
    }

    @Override
    public void create() {
        ShapeSubjects.getSelectedShapeList.clear();
        paintOnCanvas();
        ShapeSubjects.getShapeList.add(this);
    }

    @Override
    public ArrayList<IMasterShape> getArrayList() {
        ArrayList<IMasterShape> newList = new ArrayList<>();
        newList.add(this);
        return newList;
    }

    @Override
    public Shape getBoundingBox() {
        return shape.getBounds();
    }

    @Override
    public IMasterShape copyShape() {
        IMasterShape copyShape = new DrawShapeStrategy(this, paintCanvas);
        return copyShape;
    }

    @Override
    public IMasterShape pasteShape() {
        DrawShapeStrategy shape = new DrawShapeStrategy(this, paintCanvas);
        AffineTransform transform = new AffineTransform();
        transform.translate( 100,100 );
        Shape copiedShape = transform.createTransformedShape( this.getShape() );
        shape.setShape( copiedShape );
        return shape;
    }

    @Override
    public boolean detectCollision(Shape diffShape) {
        return CollisionDetection.detectCollision( shape, diffShape );
    }
}

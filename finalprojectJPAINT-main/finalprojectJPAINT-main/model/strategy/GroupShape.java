package model.strategy;

import model.CollisionDetection;
import model.ShapeSubjects;
import model.command.IUndoable;
import model.composite.DrawBoundingBox;
import model.composite.ShapeBoundingBox;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class GroupShape implements IMasterShape, IUndoable {
    ArrayList<IMasterShape> list1 = new ArrayList<>();
    private PaintCanvasBase paintCanvas;
    private  Graphics2D graphics2D;
    private Shape boundingBox;

    public GroupShape(GroupShape groupShape) {
        this.paintCanvas = groupShape.paintCanvas;
        this.graphics2D = groupShape.paintCanvas.getGraphics2D();
        for (IMasterShape shape: groupShape.getList()) {
            this.add(shape.copyShape());
        }
        this.createBoundingBox();
    }

    public GroupShape(PaintCanvasBase paintCanvas) {
        this.paintCanvas = paintCanvas;
        this.graphics2D = paintCanvas.getGraphics2D();
    }

    private void createBoundingBox() {
        ArrayList<Shape> list = new ArrayList<>();
        for (IMasterShape shape: list1) {
            Shape boundingBox = shape.getBoundingBox();
            if (!list.contains( boundingBox )) {
                list.add( shape.getBoundingBox() );
            }
        }
        ShapeBoundingBox shapeBoundingBox = new ShapeBoundingBox(list);
        this.boundingBox = shapeBoundingBox.getBoundingBox();
    }

    private void add(IMasterShape copyShape) {
        if(!list1.contains( copyShape )) {
            list1.add(copyShape);
            this.createBoundingBox();
        }
    }
    //Shapes grouped together should be operated on as if they were
    //one shape
    public void group() {
        DrawBoundingBox boundingBox = DrawBoundingBox.getInstance();
        ArrayList<IMasterShape> list = ShapeSubjects.getSelectedShapeList.getArrayList();
        if(list != null) {
            for (IMasterShape shape: list) {
                this.add( shape );
            }
            ShapeSubjects.getSelectedShapeList.removeAll( this.list1 );
            ShapeSubjects.getSelectedShapeList.add( this );
            create();
            boundingBox.drawBoundingBox( graphics2D );
            drawBoundingBox();
        }
    }

    private void drawBoundingBox() {
        graphics2D.setStroke( new BasicStroke( 3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0 ) );
        graphics2D.setPaint( Color.BLACK );
        graphics2D.draw( this.getBoundingBox() );
    }

    public void unGroup() {
        for (IMasterShape shape: list1) {
            ShapeSubjects.getShapeList.add( shape );
        }
        ShapeSubjects.getShapeList.remove( this );
        ShapeSubjects.getSelectedShapeList.remove( this );
    }

    @Override
    public void undo() {
        unGroup();
    }

    @Override
    public void redo() {
        create();
    }

    @Override
    public void paintOnCanvas() {
        for (IMasterShape shape: list1) {
            shape.setGraphics2D( graphics2D );
            shape.paintOnCanvas();
        }
    }

    @Override
    public void setGraphics2D(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
    }

    @Override
    public void deleteShape() {
        unGroup();
        for (IMasterShape shape: list1) {
            shape.deleteShape();
        }
    }

    @Override
    public void selectedShape() {
    }
    @Override
    public void moveShape(int dx, int dy) {
        AffineTransform transform = new AffineTransform();
        transform.translate( dx, dy);
        boundingBox = transform.createTransformedShape( boundingBox );
        for (IMasterShape shape: list1) {
            shape.moveShape( dx, dy);
        }
    }

    @Override
    public void create() {
        ShapeSubjects.getShapeList.add( this );
        for (IMasterShape shape: this.list1) {
            ShapeSubjects.getShapeList.remove( shape );
        }
        this.createBoundingBox();
    }

    @Override
    public ArrayList<IMasterShape> getArrayList() {
        ArrayList<IMasterShape> list = new ArrayList<>();
        for (IMasterShape shape: list1) {
            list.addAll( shape.getArrayList() );
        }
        return list;
    }

    @Override
    public Shape getBoundingBox() {
        return boundingBox;
    }

    @Override
    public IMasterShape copyShape() {
        GroupShape gS = new GroupShape( this );
        return gS;
    }

    @Override
    public IMasterShape pasteShape() {
        GroupShape gS = new GroupShape( paintCanvas );
        for (IMasterShape shape: list1) {
            gS.add( shape.pasteShape() );
        }
        gS.create();
        return gS;
    }

    @Override
    public boolean detectCollision(Shape diffShape) {
        createBoundingBox();
        boolean detectCollision = CollisionDetection.detectCollision( boundingBox, diffShape );
        return detectCollision;
    }

    public ArrayList<IMasterShape> getList() {return list1;
    }
}


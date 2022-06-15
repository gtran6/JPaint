package controller;

import model.MouseMode;
import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseEvents extends MouseAdapter {
    private Point startPoint;
    private Point endPoint;
    private PaintCanvasBase paintCanvas;
    private IApplicationState applicationState;
    private int prevX = 0; //the previous location of the mouse
    private int prevY = 0;
    private boolean dragging = false; //this is set to true while user is drawing
    private MoveMode moveMode = null;

    public MouseEvents(PaintCanvasBase paintCanvas, IApplicationState applicationState) {
        this.paintCanvas = paintCanvas;
        this.applicationState = applicationState;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
        endPoint = e.getPoint();
        prevX = e.getX();
        prevY = e.getY();
        dragging = false;
    }
    public void mouseDragged(MouseEvent e) {
        MouseMode mouseMode = applicationState.getActiveMouseMode();
        int currX = e.getX(); //x-coordinate of mouse
        int currY = e.getY(); //y-coordinate of mouse
        dragging = true;

        if (mouseMode.equals( MouseMode.MOVE )) {
            int moveX = currX - prevX;
            int moveY = currY - prevY;
            //new position of the point
            Point newPosition = new Point(moveX, moveY);
            prevX = currX; //get ready for the next shape
            prevY = currY;
            moveMode = new MoveMode(startPoint, newPosition, paintCanvas, applicationState);
            moveMode.operate();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MouseMode mouseMode = applicationState.getActiveMouseMode();
        endPoint = e.getPoint();
        Mode mode = null;
        if (mouseMode.equals( MouseMode.DRAW ) ) {
            mode = new DrawMode(startPoint, endPoint, paintCanvas, applicationState);
        } else if (mouseMode.equals( MouseMode.SELECT )) {
            mode = new SelectMode(startPoint, endPoint, paintCanvas, applicationState);
        } else if (mouseMode.equals( MouseMode.MOVE )) {
            if (dragging && moveMode != null) {
                moveMode.move(endPoint);
            }
        }
        if (mode != null) {
            mode.operate();
        }
    }
}

import canvas.Direction;
import canvas.Fruit;
import canvas.Point;
import canvas.util.Util;

import java.awt.*;
import java.util.ArrayList;

public class AbstractSnake {
    public ArrayList<CanvasPoint> snakePoints = new ArrayList<>();
    Color color = Util.newColor();

    CanvasPoint createNewHead(Direction d){
        CanvasPoint head = new CanvasPoint(getHead().r + d.dr, getHead().c + d.dc, this.color);
        return head;
    }

    public void moveHead(Direction d) {
        this.snakePoints.add(createNewHead(d));
    }

    public boolean didCollideWithFruit(Fruit f){
        return this.getHead().r == f.r && this.getHead().c == f.c;
    }

    public void removeTail() {
        this.snakePoints.remove(0);
    }

    public CanvasPoint getHead() {
        return snakePoints.get(snakePoints.size() - 1);
    }
}

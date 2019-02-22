import canvas.Point;

import java.awt.Color;

public class CanvasPoint extends Point {
    static int canvasRow =  CanvasWrapper.canvasRow();
    static int canvasCol = CanvasWrapper.canvasCol();

    CanvasPoint(int r, int c, Color color){
        this.r = (r + canvasRow) % canvasRow ;
        this.c = (c + canvasCol) % canvasCol;
        this.color = color;
    }
}

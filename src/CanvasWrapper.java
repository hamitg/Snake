import canvas.Canvas;
import canvas.MyCanvas;

public class CanvasWrapper {
    private final static Canvas canvas = MyCanvas.newCanvas();


    public static Canvas getCanvas() {
        return canvas;
    }

    public static int canvasRow(){
        return canvas.row();
    }

    public static int canvasCol(){
        return canvas.col();
    }

}

import canvas.Fruit;

public class MovingFruit extends Fruit {
    private int canvasRow = CanvasWrapper.canvasRow();
    private int canvasCol = CanvasWrapper.canvasCol();
    public void moveIt () {
        this.r = (this.r + 1 + canvasRow) % canvasRow;
        this.c = (this.c + canvasCol) % canvasCol;
    }
}

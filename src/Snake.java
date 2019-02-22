import canvas.Point;

public class Snake extends AbstractSnake{
    public Snake() {
        snakePoints.add(new CanvasPoint(5,5,color));

    }

    boolean didCollideWithItself() {
        for (int i= 0; i <this.snakePoints.size() - 1; i++) {
            if (this.snakePoints.get(i).c == this.getHead().c &&
                    this.snakePoints.get(i).r == this.getHead().r &&
                    this.snakePoints.size() > 1)
                return true;
        }
        return false;
    }
}

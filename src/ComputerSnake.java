import canvas.Direction;
import canvas.Point;

import java.util.ArrayList;

public class ComputerSnake extends AbstractSnake {
    Direction currentDirection = Direction.RIGHT;
    ComputerSnake() {
        snakePoints.add(new CanvasPoint(8, 8, color));
    }

    ComputerSnake(ArrayList<CanvasPoint> points){
        this.snakePoints.addAll(points);
    }


    public void moveHead() {
        super.moveHead(currentDirection);
    }


    public void updateDirection(int r, int c) {
        if (c < this.getHead().c && this.currentDirection != Direction.RIGHT) {
            this.currentDirection = Direction.LEFT;
        } else if (c > this.getHead().c && this.currentDirection != Direction.LEFT) {
            this.currentDirection = Direction.RIGHT;
        } else if (r < this.getHead().r && this.currentDirection != Direction.DOWN) {
            this.currentDirection = Direction.UP;
        } else if (r > this.getHead().r && this.currentDirection != Direction.UP) {
            this.currentDirection = Direction.DOWN;
        }

    }

}

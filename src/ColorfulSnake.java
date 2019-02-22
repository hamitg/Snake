import canvas.Direction;
import canvas.util.Util;

public class ColorfulSnake extends Snake {

    @Override
    public void moveHead(Direction d) {
        CanvasPoint newHead = createNewHead(d);
        newHead.color = Util.newColor();
        this.snakePoints.add(newHead);
    }

}

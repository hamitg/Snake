import canvas.*;
import canvas.Canvas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class SnakeApp implements KeyListener {
    final int FRuIT_AMOUNT =  1;
    Direction currentDirection = Direction.RIGHT;
    final static Canvas canvas = CanvasWrapper.getCanvas();
    Random random = new Random();
    ArrayList<Fruit> fruits = new ArrayList<>();
    List<AbstractSnake> snakes = Collections.synchronizedList(new ArrayList());
    int delay = 300;
    javax.swing.Timer timer = new Timer(delay, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            updateGame();
        }
    });



    SnakeApp() {
        canvas.addKeyListener(this);
        snakes.add(new Snake());
        snakes.add(new ComputerSnake());
        for(int i =0; i< FRuIT_AMOUNT; i++){
            fruits.add(new Fruit());
            fruits.add(new MovingFruit());

        }
        for(Fruit fruit: fruits){
            fruit.updatePoint(random);
        }
        drawCells();
        timer.start();
    }


    private void updateGame(){
        for (int i = 0; i< snakes.size();i++) {
            updateSnake(snakes.get(i));
        }
        moveFruits();
        drawCells();
    }

    private void updateSnake(AbstractSnake snake) {
        if (snake instanceof Snake){
            snake.moveHead(currentDirection);
            if (((Snake)snake).didCollideWithItself()){
                snakes.remove(snake);
            }
        }
        if (snake instanceof ComputerSnake){
            ((ComputerSnake)snake).moveHead();
            ((ComputerSnake)snake).updateDirection(fruits.get(0).r, fruits.get(0).c);
        }

        removeTailIfAteFood(snake);
    }

    private void removeTailIfAteFood(AbstractSnake snake) {
        if (!didEatFruit(snake)){
            snake.removeTail();
        }
    }

    private void moveFruits() {
        for (Fruit f : fruits) {
            if (f instanceof MovingFruit) {
                ((MovingFruit) f).moveIt();
            }
        }
    }

    private boolean didEatFruit(AbstractSnake snake) {
        boolean eaten = false;
        for (Fruit f : fruits) {
            if (snake.didCollideWithFruit(f)) {
                f.updatePoint(random);
                eaten = true;
            }
        }
        return eaten;
    }


    private void drawCells () {
        ArrayList<Point> allPoints = new ArrayList();
        for (AbstractSnake snake : snakes) {
            allPoints.addAll(snake.snakePoints);
        }
        allPoints.addAll(fruits);
        canvas.fillCells(allPoints);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void automaticPilot () {
        ComputerSnake comS = new ComputerSnake(snakes.get(findPlayerSnake()).snakePoints);
        snakes.remove(findPlayerSnake());
        snakes.add(comS);
    }

    public void manuelPilot () {
        Snake snake = new Snake();
        int computerSnakeIndex = findComputerSnake();
        snake.snakePoints.addAll(snakes.get(computerSnakeIndex).snakePoints);
        snakes.remove(computerSnakeIndex);
        snakes.add(snake);
    }

    private int findComputerSnake() {
        for(int i= 0; i< snakes.size();i++){
            if (snakes.get(i) instanceof ComputerSnake) return i;
        }
        return 0;
    }

    private int findPlayerSnake() {
        for(int i= 0; i< snakes.size();i++){
            if (snakes.get(i) instanceof Snake) return i;
        }
        return 0;
    }





    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            currentDirection = Direction.UP;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            currentDirection = Direction.LEFT;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            currentDirection = Direction.DOWN;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            currentDirection = Direction.RIGHT;
        }
        if (e.getKeyCode() == KeyEvent.VK_E) {
            snakes.add(new ColorfulSnake());
        }
//        if (e.getKeyCode() == KeyEvent.VK_UP) {
//            ComputerSnake. = Direction.UP;
//        }
//        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//            ComputerSnake.currentDirection = Direction.DOWN;
//        }
//        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//            ComputerSnake.currentDirection = Direction.LEFT;
//        }
//        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//            ComputerSnake.currentDirection = Direction.RIGHT;
//        }

        if (e.getKeyCode() == KeyEvent.VK_O) {
            automaticPilot();
        }
        if (e.getKeyCode() == KeyEvent.VK_M) {
            manuelPilot();

        }
        if (e.getKeyCode() == KeyEvent.VK_T) {
            fruits.add(new Fruit());
            timer.setDelay((int)(timer.getDelay()*0.8));
        }
        if (e.getKeyCode() == KeyEvent.VK_Y) {
            timer.setDelay((int)(timer.getDelay()*1.2));
        }

        updateGame();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    public static int canvasRow() {
        return canvas.row();
    }

    public static int canvasCol() {
        return canvas.col();
    }
}

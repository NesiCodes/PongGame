import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class GamePanel extends JPanel implements Runnable{
    private int GAME_WIDTH = 1000;
    private int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
    private Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    private int BALL_DIAMETER = 20;
    private Thread gameThread;
    private Image image;
    private Graphics graphics;
    private Random random;
    private Ball ball;
    private Score score;
    private Paddle paddle1;
    private Paddle paddle2;
    private ArrayList<Sprite> abilities = new ArrayList<Sprite>();
    private Sprite randomAbility;
    private boolean paddle2LastTouchedtheBall = false;
    private Timer timer;
    private boolean isGameOver = false;


    //The GamePanel constructor initializes the game with all the required components like (paddles and ball)
    //and starts it by using the java inbuilt Thread class which is similiar to the Timer class we've been using
    //but it allows for more features such as not having to have all code inside the timers anonymous class
    //It gets all users key pressings and calls the paddles method to move them accordingly
    //The timer spawns a random ability every 8 seconds in the middle of the screen
    GamePanel(){
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                paddle1.keyPressed(e);
                paddle2.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                paddle1.keyReleased(e);
                paddle2.keyReleased(e);
            }
        });
        timer = new Timer(8000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random1 = new Random();
                int randomNumForAbility = random1.nextInt(4 - 1 + 1) + 1;
                if(randomNumForAbility == 1){
                    randomAbility = new Sprite("./images/sizeincrease.png", 1);
                    abilities.add(randomAbility);
                }else if(randomNumForAbility == 2){
                    randomAbility = new Sprite("./images/p3.png", 2);
                    abilities.add(randomAbility);
                }else if(randomNumForAbility == 3){
                    randomAbility = new Sprite("./images/ballsizeincrease.png",3);
                    abilities.add(randomAbility);
                }else if(randomNumForAbility == 4){
                    randomAbility = new Sprite("./images/removescore.png",4);
                    abilities.add(randomAbility);
                }
            }
        });
        timer.setRepeats(true);
        timer.start();
    }

    public void newBall() {
        ball = new Ball("./images/ball.png");
    }

    // creates new Paddle objects with its corresponding ids and colors
    public void newPaddles() {
        paddle1 = new Paddle("./images/bluepaddle.png",1);
        paddle2 = new Paddle("./images/redpaddle.png", 2);
    }

    //paints the field and displays it
    public void paint(Graphics g) {
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }

    //displays every object into the panel(such as paddles, ball, abilities, score)
    public void draw(Graphics g) {
        Graphics2D brush = (Graphics2D)g;
        paddle1.paint(brush);
        paddle2.paint(brush);
        ball.paint(brush);
        try {
            randomAbility.paint(brush);
        }catch(java.lang.NullPointerException ex){
        }

        try{
            for(int i=1; i<abilities.size(); i++){
                abilities.get(i).paint(brush);
            }
        }catch (Exception e){

        }

        score.draw(g);
        Toolkit.getDefaultToolkit().sync(); // helps with the animation(smoother)
    }

    //calls paddles and the ball move() methods from their classes to make the correct movements
    public void move() {
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    //checks collision for every object in the panel and runs the correct code/function to be made accordingly
    // such as(ball with paddles, ball with abilities(applies the specific ability) etc)
    public void checkCollision() {
        if(ball.getY() <=0) {
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.getY() >= GAME_HEIGHT-BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.intersects(paddle1)) {
            paddle2LastTouchedtheBall = false;
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            if(ball.yVelocity>0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if(ball.intersects(paddle2)) {
            paddle2LastTouchedtheBall = true;
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            if(ball.yVelocity>0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        try{
            if(ball.intersects(randomAbility)){
                if(randomAbility.getAbilityType() == 1){
                    if(paddle2LastTouchedtheBall){
                        paddle2.setPaddle_height(paddle2.getPaddle_height() + 40);
                    }else{
                        paddle1.setPaddle_height(paddle1.getPaddle_height() + 40);
                    }
                }else if(randomAbility.getAbilityType() == 2){
                    if(paddle2LastTouchedtheBall){
                        paddle2.setSpeed(paddle2.getSpeed() + 8);
                    }else{
                        paddle1.setSpeed(paddle1.getSpeed() + 8);
                    }
                }else if(randomAbility.getAbilityType() == 3){
                    ball.setBall_height(35);
                    ball.setBall_width(35);
                }else if(randomAbility.getAbilityType() == 4){
                    if(paddle2LastTouchedtheBall){
                        if(score.player1 == 0){

                        }else{
                            score.player1--;
                        }
                    }else{
                        if(score.player2==0){

                        }else{
                            score.player2--;
                        }
                    }
                }
                randomAbility.setLocation(-100,-100);
            }
        }catch (java.lang.NullPointerException e){
        }

        if(paddle1.getY()<=0)
            paddle1.setY(0);
        if(paddle1.getY() >= (GAME_HEIGHT-paddle1.getPaddle_height()))
            paddle1.setY(GAME_HEIGHT-paddle1.getPaddle_height());
        if(paddle2.getY()<=0)
            paddle2.setY(0);
        if(paddle2.getY() >= (GAME_HEIGHT-paddle2.getPaddle_height()))
            paddle2.setY(GAME_HEIGHT-paddle2.getPaddle_height());
        if(ball.getX() <=0) {
            timer.restart();
            try {
                randomAbility.setLocation(-100,-100);
            }catch (NullPointerException err){

            }
            score.player2++;
            if(score.player2 == 5){
                isGameOver = true;
                gameOver();
            }
            newPaddles();
            newBall();
        }
        if(ball.getX() >= GAME_WIDTH-BALL_DIAMETER) {
            timer.restart();
            try {
                randomAbility.setLocation(-100,-100);
            }catch (NullPointerException err){

            }
            score.player1++;
            if(score.player1 == 5){
                isGameOver = true;
                gameOver();
            }
            newPaddles();
            newBall();
        }
    }

    public void pauseGame(){
        gameThread.suspend();
    }

    public void unpauseGame(){
        gameThread.resume();
    }

    //resets both players score and restarts the game
    public void resetGame(){
        timer.restart();
        try {
            randomAbility.setLocation(-100,-100);
        }catch (NullPointerException err){
        }
        score.player2 = 0;
        score.player1 = 0;
        newPaddles();
        newBall();
    }

    public void gameOver(){
        if(isGameOver){
            System.out.println("GAME OVER");
            gameThread.stop();
        }
    }

    //this method is run automaticially even though its not called in the constructor or anywhere
    //which is made possible by implementing the Runnable interface
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks =60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now -lastTime)/ns;
            lastTime = now;
            if(delta >=1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }
}

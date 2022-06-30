
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Ball{
    private BufferedImage image;
    Random random;
    int xVelocity;
    int yVelocity;
    int initialSpeed = 2;
    private int x;
    private int y;
    private int ball_width;
    private int ball_height;


    //The constructor takes the FilePath of the image and initializes its fields correspondingly
    //By using the java inbuild Random class it generates a random direction from where to start(left or right)
    //and moves the ball diagonally
    Ball(String imageFilePath){
        try {
            this.image = ImageIO.read(new File(imageFilePath));
        } catch(IOException e) {
            System.out.println("Unable to load image at " + imageFilePath);
            e.printStackTrace();
            System.exit(1);
        }
        ball_height = image.getHeight();
        ball_width = image.getWidth();
        this.x = (1000/2)-(20/2);
        random = new Random();
        this.y = random.nextInt((int)(1000 * (0.5555))-20);
        this.ball_width = 20;
        this.ball_height = 20;
        int randomXDirection = random.nextInt(2);
        if(randomXDirection == 0)
            randomXDirection--;
        setXDirection(randomXDirection*initialSpeed);

        int randomYDirection = random.nextInt(2);
        if(randomYDirection == 0)
            randomYDirection--;
        setYDirection(randomYDirection*initialSpeed);
    }

    //checks if the ball intersecs with the paddles(same code we used in the crab game)
    public boolean intersects(Paddle other) {
        if(getX() < (other.getX() + other.getPaddle_width()) && (getX() + getBall_width()) > other.getX() && getY() < (other.getY() + other.getPaddle_height()) && (getY()+getBall_height()) > other.getY()){
            return true;
        }else{
            return false;
        }
    }

    //checks if the ball intersecs with the paddles(same code we used in the crab game)
    public boolean intersects(Sprite other) {
        if(getX() < (other.getX() + other.getWidth()) && (getX() + getBall_width()) > other.getX() && getY() < (other.getY() + other.getHeight()) && (getY()+getBall_height()) > other.getY()){
            return true;
        }else{
            return false;
        }
    }


    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public int getInitialSpeed() {
        return initialSpeed;
    }

    public void setInitialSpeed(int initialSpeed) {
        this.initialSpeed = initialSpeed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getBall_width() {
        return ball_width;
    }

    public void setBall_width(int ball_width) {
        this.ball_width = ball_width;
    }

    public int getBall_height() {
        return ball_height;
    }

    public void setBall_height(int ball_height) {
        this.ball_height = ball_height;
    }

    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;
    }
    public void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;
    }
    public void move() {
        x += xVelocity;
        y += yVelocity;
    }
    public void paint(Graphics2D brush){
        brush.drawImage(image,x,y,ball_width,ball_height,null);
    }
}

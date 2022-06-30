import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Paddle {
    private int id;
    private BufferedImage image;
    private int paddle_width;
    private int paddle_height;
    private int x;
    private int y;
    int yVelocity;
    int speed = 10;

    //The constructor requires the FilePath of the image and its id and initializes the fields correspondingly
    //The height and width fields are initialized by calling the BufferedImage's getWidth and getHeight methods
    public Paddle(String imageFilePath, int id){
        try {
            this.image = ImageIO.read(new File(imageFilePath));
        } catch(IOException e) {
            System.out.println("Unable to load image at " + imageFilePath);
            e.printStackTrace();
            System.exit(1);
        }
        this.id=id;
        paddle_height = image.getHeight();
        paddle_width = image.getWidth();
        if(id == 1){
            this.x = 0;
            this.y=(1000/2)-(paddle_height/2);
        }else if(id == 2){
            this.x = 1000-paddle_width;
            this.y = ((int)(1000 * (0.5555))/2)-(paddle_height/2);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getPaddle_width() {
        return paddle_width;
    }

    public void setPaddle_width(int paddle_width) {
        this.paddle_width = paddle_width;
    }

    public int getPaddle_height() {
        return paddle_height;
    }

    public void setPaddle_height(int paddle_height) {
        this.paddle_height = paddle_height;
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

    public int getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Gets the keyevent pressed from the gamepanel each time a key is pressed and sets the paddles speed and direction accordingly
    public void keyPressed(KeyEvent e) {
        switch(id) {
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W) {
                    setYDirection(-speed);
                }
                if(e.getKeyCode()==KeyEvent.VK_S) {
                    setYDirection(speed);
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_I) {
                    setYDirection(-speed);
                }
                if(e.getKeyCode()==KeyEvent.VK_K) {
                    setYDirection(speed);
                }
                break;
        }
    }

    //stops paddle movement when key is released
    public void keyReleased(KeyEvent e) {
        switch(id) {
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W) {
                    setYDirection(0);
                }
                if(e.getKeyCode()==KeyEvent.VK_S) {
                    setYDirection(0);
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_I) {
                    setYDirection(0);
                }
                if(e.getKeyCode()==KeyEvent.VK_K) {
                    setYDirection(0);
                }
                break;
        }
    }
    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }
    public void move() {
        y= y + yVelocity;
    }
    //paints the image with its corresponding attributes into the gamepanel
    public void paint(Graphics2D brush){
        brush.drawImage(image,x,y,paddle_width,paddle_height,null);
    }
}

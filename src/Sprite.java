
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Sprite implements BaseSprite{

    private BufferedImage image;
    private int width;
    private int height;
    private int x;
    private int y;
    private int abilityType;

    //The constructor requires the FilePath of the image and its abilityType and initializes the fields correspondingly
    //The height and width fields are initialized by calling the BufferedImage's getWidth and getHeight methods
    public Sprite(String imageFilePath, int abilityType) {
        try {
            this.image = ImageIO.read(new File(imageFilePath));
        } catch(IOException e) {
            System.out.println("Unable to load image at " + imageFilePath);
            e.printStackTrace();
            System.exit(1);
        }
        this.abilityType = abilityType;
        width = image.getWidth();
        height = image.getHeight();
        x = 500;
        Random random = new Random();
        y = random.nextInt((int)(1000 * (0.5555))-20);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getAbilityType() {
        return abilityType;
    }

    public void setAbilityType(int abilityType) {
        this.abilityType = abilityType;
    }

    public void setHeight(int height) {
        this.height = height;
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

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public void paint(Graphics2D brush) {
        brush.drawImage(image, x, y, width, height, null);
    }
}

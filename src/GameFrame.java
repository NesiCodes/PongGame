
import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame{
    private GamePanel panel;

    //The constructor adds both the control panel which holds the buttons and the game panel which holds the game into one screen
    //andby giving the panel instance to the control panel object we can have the buttons on the control panel affect the gamePanel.
    GameFrame(){
        super("Final Project");
        this.setLayout(new BorderLayout());

        panel = new GamePanel();
        this.add(panel, BorderLayout.CENTER);
        ControlPanel controlPanel = new ControlPanel(panel);
        this.add(controlPanel, BorderLayout.SOUTH);

        this.setTitle("Final Project");
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}

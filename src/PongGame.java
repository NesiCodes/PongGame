import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PongGame {
//    private static JFrame window;
//    private Container con;
//    private JPanel titleNamePanel, startButtonPanel;
//    private JLabel titleNameLabel;
//    private Font titleFont = new Font("Times New Roman", Font.PLAIN, 48);
//    private Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
//    private static JButton startButton;
//    private static JButton quitButton;

    //Pong game constructor creates a welcome screen with 2 buttons(start game and exit) and displays them
    public PongGame(){
//        window = new JFrame();
//        window.setSize(800,600);
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.getContentPane().setBackground(Color.black);
//        window.setLayout(null);
//        window.setVisible(true);
        GameFrame frame = new GameFrame();
//        con = window.getContentPane();
//
//        titleNamePanel = new JPanel();
//        titleNamePanel.setBounds(100,100,600,150);
//        titleNamePanel.setBackground(Color.black);
//        titleNameLabel = new JLabel("Pong Game");
//        titleNameLabel.setForeground(Color.white);
//        titleNameLabel.setFont(titleFont);
//
//        startButtonPanel = new JPanel();
//        startButtonPanel.setBounds(300,400,200,100);
//        startButtonPanel.setBackground(Color.black);
//
//        startButton = new JButton("Start");
//        startButton.setBackground(Color.black);
//        startButton.setForeground(Color.white);
//        startButton.setFont(normalFont);
//
//        quitButton = new JButton("Quit");
//        quitButton.setBackground(Color.black);
//        quitButton.setForeground(Color.white);
//        quitButton.setFont(normalFont);
//
//        titleNamePanel.add(titleNameLabel);
//        startButtonPanel.add(startButton);
//        startButtonPanel.add(quitButton);
//        con.add(titleNamePanel);
//        con.add(startButtonPanel);
    }

    //In the main method we call the welcome screen and we set Action Listeners with anonymous classes for both buttons
    //to call the corresponding functions when clicked
    public static void main(String[] args) {
        PongGame pongGame = new PongGame();
//        startButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                window.dispose();
//                System.out.println("printed asdadsadsads");
//                GameFrame frame = new GameFrame();
//            }
//        });
//
//        quitButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                window.dispose();
//            }
//        });
    }


}


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel{
    private JButton startGameButton;
    private JButton pauseGameButton;

    //Creates a control panel with 2 buttons(start and pause/unpause) and adds the corresponding functions to which button using actionListeners
    //Also changes the text of the pause/unpause button each time its clicked
    public ControlPanel(GamePanel drawingPanel) {
        setLayout(new FlowLayout());
        setBackground(Color.orange);

        startGameButton = new JButton();
        startGameButton.setText("Reset");
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                drawingPanel.startGame();
                drawingPanel.resetGame();

            }
        });
        this.add(startGameButton);

        pauseGameButton = new JButton();
        pauseGameButton.setText("Pause");
        pauseGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pauseGameButton.getText().equals("Pause")) {
                    drawingPanel.pauseGame();
                    pauseGameButton.setText("Unpause");
                }
                else {
                    drawingPanel.unpauseGame();
                    pauseGameButton.setText("Pause");
                }

            }
        });
        this.add(pauseGameButton);
    }


}


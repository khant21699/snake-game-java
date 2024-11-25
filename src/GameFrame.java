import javax.swing.*;

public class GameFrame extends JFrame {
    private GamePanel gamePanel;
    private String title;
    public GameFrame(){
         gamePanel = new GamePanel();
         this.add(gamePanel);
         this.title = "Snake Game";
         this.setTitle(title);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setResizable(false);
         this.pack();
         this.setLocationRelativeTo(null);
         this.setVisible(true);

    }
}

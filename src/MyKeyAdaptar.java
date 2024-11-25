import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyAdaptar implements KeyListener {
    private GamePanel gamePanel;

    public MyKeyAdaptar(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // We don't need this for the snake game
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            // WASD Controls
            case KeyEvent.VK_W:
                if(gamePanel.getDirection() != 'D') {
                    gamePanel.setDirection('U');
                }
                break;
            case KeyEvent.VK_S:
                if(gamePanel.getDirection() != 'U') {
                    gamePanel.setDirection('D');
                }
                break;
            case KeyEvent.VK_A:
                if(gamePanel.getDirection() != 'R') {
                    gamePanel.setDirection('L');
                }
                break;
            case KeyEvent.VK_D:
                if(gamePanel.getDirection() != 'L') {
                    gamePanel.setDirection('R');
                }
                break;

            // Arrow Key Controls
            case KeyEvent.VK_UP:
                if(gamePanel.getDirection() != 'D') {
                    gamePanel.setDirection('U');
                }
                break;
            case KeyEvent.VK_DOWN:
                if(gamePanel.getDirection() != 'U') {
                    gamePanel.setDirection('D');
                }
                break;
            case KeyEvent.VK_LEFT:
                if(gamePanel.getDirection() != 'R') {
                    gamePanel.setDirection('L');
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(gamePanel.getDirection() != 'L') {
                    gamePanel.setDirection('R');
                }
                break;
            case KeyEvent.VK_SPACE:
                if( !gamePanel.getGameStart()) {
                    gamePanel.startGame();
                }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // We don't need this for the snake game
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    private final int SCREEN_WIDTH = 1000, SCREEN_HEIGHT = 600, UNIT_SIZE = 25;
    private final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE, DELAY = 75;
    private final int x[] = new int[GAME_UNITS];
    private final int y[] = new int[GAME_UNITS];
    private int bodyParts = 6;
    private int applesEaten = 0;
    private int appleX;
    private int appleY;
    private char direction = 'R';
    private boolean running = true;
    private boolean gameStart = false;
    private Timer timer;
    private Random random;
    private MyKeyAdaptar myKeyAdaptar;

    public GamePanel(){
        random = new Random();
        myKeyAdaptar = new MyKeyAdaptar(this);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(myKeyAdaptar);
        if(gameStart){
            startGame();
        }
    }

    public boolean getGameStart(){
        return gameStart;
    }

    public void startGame(){

        applesEaten = 0;
        bodyParts = 6;
        newApple();
        x[0] = SCREEN_WIDTH/2;
        y[0] = SCREEN_HEIGHT/2;
        running = true;
        gameStart = true;
        timer = new Timer(DELAY,this);
        timer.restart();
        repaint();

    }

    public void move(){
        for(int i = bodyParts; i > 0; i--){
            x[i] = x[ i - 1 ];
            y[i] = y[ i - 1 ];
        }

        // Move head based on direction
        switch(direction) {
            case 'U': y[0] = y[0] - UNIT_SIZE; break; // Move up
            case 'D': y[0] = y[0] + UNIT_SIZE; break; // Move down
            case 'L': x[0] = x[0] - UNIT_SIZE; break; // Move left
            case 'R': x[0] = x[0] + UNIT_SIZE; break; // Move right
        }

    }

    public void newApple(){
        appleY = random.nextInt((int)SCREEN_HEIGHT/UNIT_SIZE) * UNIT_SIZE;
        appleX = random.nextInt((int)SCREEN_WIDTH/UNIT_SIZE) * UNIT_SIZE;

    }

    public void checkCollision(){
        if((x[0] == SCREEN_WIDTH || x[0]==0) || (y[0] == SCREEN_HEIGHT || y[0]==0)){
            running = false;
        }

        for(int i = bodyParts; i > 0; i--) {
            if((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        g.setColor(Color.gray);
        for(int i = 0; i < SCREEN_WIDTH/UNIT_SIZE; i++){
            g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
            // Add horizontal lines too for a complete grid
        }
        for(int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; i++){
            g.drawLine(0, i* UNIT_SIZE,  SCREEN_WIDTH, i*UNIT_SIZE);
        }

        g.setColor(Color.cyan);
        g.fillOval(appleX,appleY,UNIT_SIZE,UNIT_SIZE);


        for(int i = 0 ; i < bodyParts; i++){
            if(i == 0){
                g.setColor(Color.green);
            }else{
                g.setColor(Color.red);
            }
            g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
        }

        g.setColor(Color.blue);
        Font scoreFont = new Font("Ink Free", Font.BOLD, 20);
        g.setFont(scoreFont);
        String score = "Score" + applesEaten;
        FontMetrics socreMetric = getFontMetrics(scoreFont);
        g.drawString(score,(SCREEN_WIDTH - socreMetric.stringWidth(score))/2, 70);
        if(!running){
            gameOver(g);
        }

        if(!gameStart){
            g.setColor(Color.red);
            Font startGame = new Font("Ink Free", Font.BOLD, 50);
            g.setFont(startGame);
            String gameOverMessage = "Press Space to Start the Game";
            FontMetrics metric = getFontMetrics(startGame);
            g.drawString(gameOverMessage,(SCREEN_WIDTH - metric.stringWidth(gameOverMessage))/2, SCREEN_HEIGHT - 100);
        }

    }

    public void gameOver(Graphics g){
            Font gameoverFont = new Font("Ink Free", Font.BOLD, 70);
            g.setFont(gameoverFont);
            String gameOverMessage = "Game Over";
            FontMetrics metric = getFontMetrics(gameoverFont);
            g.drawString(gameOverMessage,(SCREEN_WIDTH - metric.stringWidth(gameOverMessage))/2, SCREEN_HEIGHT/2);
            gameStart = false;
            direction = 'R';
            timer.stop();
    }

    public void checkApple(){

        if(x[0] == appleX && y[0] == appleY){
            applesEaten++;
            bodyParts++;
            System.out.println("apple eaten");
            newApple();
        }
    }

    public char getDirection(){
        return direction;
    }

    public void setDirection(char val){
        direction = val;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running) {
            move();          // Move the snake
            checkApple();    // Check if apple is eaten
            checkCollision(); // Check for collisions
        }
        repaint();          // Redraw the screen
    }
}
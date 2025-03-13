package Main.GameFolder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import Main.GameFolder.GameComp.Ball;
import Main.GameFolder.GameComp.CollisionHandler;
import Main.GameFolder.GameComp.Controler;

public class Game extends JPanel implements Runnable {

    public Ball ball = new Ball();
    public Controler paddle1;
    public Controler paddle2;
    public CollisionHandler collisionHandler;
    private int FPSLimit = 60;
    static Thread GameThread;


    public int player1Score = 0;
    public int player2Score = 0;

    public Game() {
        this.setPreferredSize(new java.awt.Dimension(800, 600));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
    }

    /**
     * Creates a new game for single player or local multiplayer
     * 
     * @param gameMode
     */
    public Game(int gameMode) {
        this.setPreferredSize(new java.awt.Dimension(800, 600));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        if (gameMode == 1) {
            paddle1 = new Controler();
            paddle2 = new Controler(false);
        } else if (gameMode == 2) {
            paddle1 = new Controler();
            paddle2 = new Controler(true);
        }
        this.addKeyListener(paddle1);
        this.addKeyListener(paddle2);
        collisionHandler = new CollisionHandler(paddle1, paddle2, ball);
        start();
    }

    /**
     * Check if the ball is out of bounds and update the score
     * also resets position and speed of the ball after a point is scored
     */
    public void points() {
        if (ball.x < 0) {
            player2Score++;
            ball.x = 400;
            ball.y = 300;
            ball.angle = 180;
            ball.speed = 5;
        }
        if (ball.x > 800) {
            player1Score++;
            ball.x = 400;
            ball.y = 300;
            ball.angle = 0;
            ball.speed = 5;
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.setFont(new java.awt.Font("DialogInput", java.awt.Font.BOLD, 30));
        g2d.drawString("" + player1Score, 200, 100);
        g2d.drawString("" + player2Score, 600, 100);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        draw(g2d);
        paddle1.draw(g2d);
        paddle2.draw(g2d);
        ball.draw(g2d);
    }

    public void start() {
        GameThread = new Thread(this);
        GameThread.start();
    }

    public void run() {
        while (GameThread != null) {
            update();
            repaint();
            try {
                Thread.sleep(1000 / FPSLimit);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        points();
        collisionHandler.checkCollision();
        paddle1.move(ball);
        paddle2.move(ball);
        ball.move();
    }
}
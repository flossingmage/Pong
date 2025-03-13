package Main.GameFolder.GameComp;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import Main.Client;
import Main.Server;
import Main.GameFolder.MultiplayerGameInfo;

public class Controler extends Paddle implements KeyListener {

    private boolean up = false, down = false;
    public boolean isPlayer1 = false;
    public boolean isPlayer2 = false;

    /**
     * Creates controler for player 1
     */
    public Controler() {
        super(1);
        this.isPlayer1 = true;
    }

    /**
     * Creates controler for player 2 or computer
     * depending on the boolean value
     * 
     * @param player
     */
    public Controler(boolean player) {
        super(2);
        if (player) {
            this.isPlayer2 = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Get the key pressed and set the direction of the paddle for player 1 and
     * player 2
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (isPlayer1) {
            if (keyCode == KeyEvent.VK_W)
                up = true;
            if (keyCode == KeyEvent.VK_S)
                down = true;
        } else if (isPlayer2) {
            if (keyCode == KeyEvent.VK_UP)
                up = true;
            if (keyCode == KeyEvent.VK_DOWN)
                down = true;
        }
        if (keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
            try {
                Client.socket.close();
                Server.server.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (isPlayer1) {
            if (keyCode == KeyEvent.VK_W)
                up = false;
            if (keyCode == KeyEvent.VK_S)
                down = false;
        } else if (isPlayer2) {
            if (keyCode == KeyEvent.VK_UP)
                up = false;
            if (keyCode == KeyEvent.VK_DOWN)
                down = false;
        }
    }

    /**
     * Move the paddle up or down depending on the key pressed
     */

    public void move(Ball ball) {
        if (isPlayer1 || isPlayer2) {
            if (up && y > 0)
                y -= speed;
            if (down && y < 600 - height)
                y += speed;
        } else {
            if (ball.y > y + height / 2 && y < 600 - height)
                y += speed;
            if (ball.y < y + height / 2 && y > 0)
                y -= speed;
        }
    }

    public void multiplayerMove(MultiplayerGameInfo multiplayerGameInfo) {
        if (up && y > 0)
            y -= speed;
        if (down && y < 600 - height)
            y += speed;

        if (isPlayer1) {
            multiplayerGameInfo.paddle1Y = y;
        } else {
            multiplayerGameInfo.paddle2Y = y;
        }
    }

    public void multiplayerMove(int y) {
        this.y = y;
    }

}

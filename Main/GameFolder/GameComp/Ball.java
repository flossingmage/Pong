package Main.GameFolder.GameComp;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.GameFolder.MultiplayerGameInfo;

public class Ball {
    public int x = 400;
    public int y = 300;
    public int size = 20;
    public double speed = 5;
    public int angle = 180;

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.fillRect(x, y, size, size);

    }

    public void move() {
        x += (int) (speed * Math.cos(Math.toRadians(angle)));
        y += (int) (speed * Math.sin(Math.toRadians(angle)));
    }

    public void multiplayerMove(MultiplayerGameInfo multiplayerGameInfo) {
        move();
        multiplayerGameInfo.ballX = x;
        multiplayerGameInfo.ballY = y;
    }

    public void multiplayerMove(int x, int y) {
        this.x = x;
        this.y = y;
    }

}

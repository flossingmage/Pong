package Main.GameFolder.GameComp;

import java.awt.Color;
import java.awt.Graphics2D;

public class Paddle {
    public int y = 300;
    public int x;
    public int width = 20;
    public int height = 100;
    public int speed = 5;

    public Paddle(int player) {
        if (player == 1) {
            this.x = 0;
        } else {
            this.x = 780;
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.fillRect(x, y, width, height);
    }
}

package Main.GameFolder;

import java.io.Serializable;

public class MultiplayerGameInfo implements Serializable {
    private static final long serialVersionUID = 1L;
     public int paddle1Y = 300;
     public int paddle2Y = 300;
     public int ballX;
     public int ballY;

    public MultiplayerGameInfo() {}

    public void updateInfoForPlayer2(MultiplayerGameInfo info) {
        paddle1Y = info.paddle1Y;
        ballX = info.ballX;
        ballY = info.ballY;
    }

    public void updateInfoForPlayer1(int paddle2Y) {
        this.paddle2Y = paddle2Y;
    }

    public String toString() {
        return "Paddle1Y: " + paddle1Y + " Paddle2Y: " + paddle2Y + " BallX: " + ballX + " BallY: " + ballY;
    }

}

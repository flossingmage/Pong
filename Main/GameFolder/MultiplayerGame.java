
package Main.GameFolder;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Main.GameFolder.GameComp.Ball;
import Main.GameFolder.GameComp.CollisionHandler;
import Main.GameFolder.GameComp.Controler;

public class MultiplayerGame extends Game {

    private boolean isServer;
    MultiplayerHandler multiplayerHandler;
    int recivedPaddle;
    Ball recivedBall;

    public MultiplayerGame(boolean isServer, ObjectOutputStream out, ObjectInputStream in) {
        super();

        this.isServer = isServer;

        paddle1 = new Controler();
        paddle2 = new Controler(true);

        this.addKeyListener(paddle1);
        this.addKeyListener(paddle2);

        collisionHandler = new CollisionHandler(paddle1, paddle2, ball);
        new Thread(multiplayerHandler = new MultiplayerHandler((Game) this, out, in, isServer));
        start();
    }

    @Override
    public void update() {
        points();
        collisionHandler.checkCollision();

        if (isServer) {
            try {
                paddle1.multiplayerMove(multiplayerHandler.multiplayerGameInfo);
                ball.multiplayerMove(multiplayerHandler.multiplayerGameInfo);
                paddle2.multiplayerMove(multiplayerHandler.multiplayerGameInfo.paddle2Y);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                paddle2.multiplayerMove(multiplayerHandler.multiplayerGameInfo);
                paddle1.multiplayerMove(multiplayerHandler.multiplayerGameInfo.paddle1Y);
                ball.multiplayerMove(multiplayerHandler.multiplayerGameInfo.ballX, multiplayerHandler.multiplayerGameInfo.ballY);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}

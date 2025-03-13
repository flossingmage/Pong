package Main.GameFolder;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MultiplayerHandler implements Runnable {
    public MultiplayerGameInfo multiplayerGameInfo;
    Game game;
    ObjectOutputStream out;
    ObjectInputStream in;
    boolean isServer;

    public MultiplayerHandler(Game game, ObjectOutputStream out, ObjectInputStream in, boolean isServer) {
        this.game = game;
        this.out = out;
        this.in = in;
        this.isServer = isServer;
        multiplayerGameInfo = new MultiplayerGameInfo();
        new Thread(this).start();

    }

   @Override
    public void run() {
        System.out.println("MultiplayerHandler is running...");
        while (true) {
            try {
                if (isServer) {
                    out.writeObject(multiplayerGameInfo);
                    out.reset();
                    multiplayerGameInfo.updateInfoForPlayer1(((MultiplayerGameInfo)in.readObject()).paddle2Y);
                } else {
                  multiplayerGameInfo.updateInfoForPlayer2((MultiplayerGameInfo) in.readObject());
                   out.writeObject(multiplayerGameInfo);
                    out.reset();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
   
    // @Override
    // public void run() {
    //     while (true) {
    //         try {
    //             if (isServer) {
    //                 out.writeObject(multiplayerGameInfo);
    //                 out.reset();
    //                 game.paddle2.multiplayerMove(in.readInt());
    //             } else {
    //                 multiplayerGameInfo = (MultiplayerGameInfo) in.readObject();
    //                 out.writeInt(multiplayerGameInfo.paddle2Y);
    //                 out.reset();
    //                 game.paddle1.multiplayerMove(multiplayerGameInfo.paddle1Y);
    //                 game.ball.multiplayerMove(multiplayerGameInfo.ballX, multiplayerGameInfo.ballY);

    //             }
    //         } catch (Exception e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }

}

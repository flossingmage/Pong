package Main;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Main.GameFolder.MultiplayerGame;

public class Server extends Window {
    public static Socket client;
    public static ServerSocket server;
    ObjectOutputStream out;
    ObjectInputStream in;

    public Server() {
        try {
            server = new ServerSocket(1234);
            System.out.println("Server is running...");
            client = server.accept();
            System.out.println("Client connected");
            out = new ObjectOutputStream((client.getOutputStream()));
            System.out.println("1");
            in = new ObjectInputStream((client.getInputStream()));
            System.out.println("2");

            game = new MultiplayerGame(true, out, in);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

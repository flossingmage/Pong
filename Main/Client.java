package Main;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import Main.GameFolder.MultiplayerGame;

public class Client extends Window {

    public static Socket socket;
    ObjectOutputStream out;
    ObjectInputStream in;

    public Client() {
        
        try {
            socket = new Socket("localhost", 1234);
            System.out.println("Connected to server");
            out = new ObjectOutputStream((socket.getOutputStream()));
            System.out.println("1");
            in = new ObjectInputStream((socket.getInputStream()));
            System.out.println("2");


            game = new MultiplayerGame(false, out, in);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

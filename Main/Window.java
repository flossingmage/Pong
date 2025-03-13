package Main;

import java.util.Scanner;

import javax.swing.JFrame;

import Main.GameFolder.Game;

public class Window {
    
    static Game game;
    static Scanner scanner = new Scanner(System.in);
    static JFrame window = new JFrame("Pong");


    public static void main(String[] args) {

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        pickGameMode();

        window.add(game);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static void pickGameMode() {
        try {
            System.out.println("Single player, Local multiplayer, Multiplayer (1/2/3)");
            int gameMode = scanner.nextInt();
            if (gameMode == 1) {
                game = new Game(1);
            } else if (gameMode == 2) {
                game = new Game(2);
            } else if (gameMode == 3) {
                multiplayerOptions();
            } else {
                System.out.println("Invalid input");
                pickGameMode();
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Invalid input");
            pickGameMode();
        }
    }

    public static void multiplayerOptions() {
        System.out.println("Host or join? (1/2)");
        int hostOrJoin = scanner.nextInt();
        if (hostOrJoin == 1) {
            new Server();
        } else if (hostOrJoin == 2) {
            new Client();
        } else {
            System.out.println("Invalid input");
            multiplayerOptions();
        }

    } 
}

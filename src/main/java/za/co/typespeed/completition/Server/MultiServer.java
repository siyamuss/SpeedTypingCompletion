package za.co.typespeed.completition.Server;

import za.co.typespeed.completition.world.ActivateTypeSpace;
import za.co.typespeed.completition.world.Port;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MultiServer {
    private static String[] commandlineArgs;

    public static void main(String[] args) throws IOException {
        commandlineArgs = args;
        allServerSettings();
    }

    private static void allServerSettings() throws IOException {
        ActivateTypeSpace activite = new ActivateTypeSpace( commandlineArgs );
        activite.activate_Space();

        RunChosenServer run = new RunChosenServer();

        MultiServer listen = new MultiServer();
        listen.listenServer( commandlineArgs );

        run.startChosenServer();
    }

    public static void socketServer(){
        System.out.println("Server running & waiting for client connections.");
        ServerSocket s = null;
        try {
            s = Port.getPORT();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while(true) {
            try {
                Socket socket = s.accept();
                System.out.println("Connection: " + socket);

                Runnable r = new Server(socket);
                Thread task = new Thread(r);
                task.start();

            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void listenServer(String[] args)throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                String worldCommand;
                do {
                    System.out.println("> What would you like to do next?");
                    worldCommand = scanner.nextLine().trim();
//                    new executeCommand(worldCommand);
                } while (true);

            }
        }).start();
    }



}
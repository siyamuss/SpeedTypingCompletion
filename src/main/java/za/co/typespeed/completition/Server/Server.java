package za.co.typespeed.completition.Server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import za.co.typespeed.completition.Json;
import za.co.typespeed.completition.world.JoinedUser;
import za.co.typespeed.completition.Commands.Command;
import za.co.typespeed.completition.Commands.createCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

public class Server implements Runnable {
    
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private BufferedReader din;
    private PrintStream dout ;
    static JoinedUser robot;
    private String in;
    private static Socket socket;
    private String clientMachine;

    static String getInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        String input = scanner.nextLine();
        while (input.isBlank()) {
            System.out.println(prompt);
            input = scanner.nextLine();
        }
        return input;
    }

    public Server(Socket socket){

        this.socket = socket;

        // added a try and catch when creating new buffered reader and writer
        try {
            din = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            dout = new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        clientMachine = socket.getInetAddress().getHostName();
        System.out.println("Connection from " + clientMachine);
        System.out.println("Waiting for client...");
    }
    
    public void run() {
            try {

                // changed the while true loop to while request is not null
                while ((in = din.readLine()) != null) {

                    // called the reciverRequest passing the request from the client
                    JsonNode cont = receiverRequest(in);

                    dout.println(cont);
                    dout.flush();
                }
//                robot.getWorld().setPosition(AbstractWorld.CENTRE);
//                MultiServer.listOfplayers.clear();

            } catch (IOException ex) {
                System.out.println("Shutting down single client server");
            }
        }

    // Added this method to the server for all the request from the client
    public static JsonNode receiverRequest(String request){

        System.out.println(request);
        JSONObject out = new JSONObject(request);

        robot = new JoinedUser(out.getString("name"), out.getString("password"));

        try {
            String argName = ((createCommand)OBJECT_MAPPER.readValue(request, createCommand.class)).returnName();

            Command command = Command.create(argName, createCommand.returnCommand(), createCommand.returnPassword());
            Json cont = robot.handleCommand(command);

            return OBJECT_MAPPER.readTree(String.valueOf(cont.getJsonData()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
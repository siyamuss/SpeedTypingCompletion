package za.co.wethinkcode.robotworlds.Client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import za.co.wethinkcode.robotworlds.Json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import static za.co.wethinkcode.robotworlds.Server.Server.OBJECT_MAPPER;


public class Play {

    // added new fields and changed them to public and same to private
    static Scanner scanner;
    public static BufferedReader din;
    private static PrintStream dout ;
    private static Socket socket;


    public static void main(String[] args) throws Exception {

        scanner = new Scanner(System.in);
        // create socket and connecting to the server through connect method
        Play play = new Play();
        play.connect("localhost", 5000);

        //set robot name
        String name = getInput("Register your name for the completion?");
        System.out.println("Welcome To SpeedTyping Completion! ");

        //creating and sending json with starting info for robot
        Json jData = new Json();
        jData.setMap("robot", name);

        // re-aranged the code to handly to send new required commands and arguments
        do {
            String instruction = getInput(name + "> What must I do next?").strip().toLowerCase();
            Json jDataArguments = setClientRequst(jData, instruction);

            try {

                JsonNode worlds = play.sendRequest(String.valueOf(jDataArguments.getJsonData()));

            } catch (IllegalArgumentException e) {
                play.disconnect();
            }
//            System.out.println(robot);
        } while (socket.isConnected());

    }

    public static Json setClientRequst(Json jData, String instruction){
        String[] cmd = instruction.split(" ");
        System.out.println(cmd.length);
        jData.setMap("command", cmd[0]);

        ArrayList<String> arguments = new ArrayList<>();
        if (cmd.length == 3) {
            arguments.add(cmd[0]);
            arguments.add(cmd[1]);
            arguments.add(cmd[2]);
        } else if (cmd.length > 1 ) {
            arguments.add(cmd[1]);
        } else {
            arguments.add("");
        }
        jData.setMap("arguments", arguments);
        return jData;
    }

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

    // added new method that helps create new socket, buffered reader and print stream.
    public boolean connect(String defaultIp, int defaultPort) {

        try {
            socket = new Socket(defaultIp, defaultPort);
            dout = new PrintStream(socket.getOutputStream());
            din = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // added new method that helps disconnect socket, buffered reader and print stream.
    public void disconnect() {

        try {
            dout.close();
            din.close();
            socket.close();
        } catch (IOException e) {
            //error connecting should just throw Runtime error and fail test
            throw new RuntimeException("Error disconnecting from Robot Worlds server.", e);
        }
    }
    // added new method that helps discoonect socket, buffered reader and print stream.
    public boolean isConnected() {
        return this.socket.isConnected();
    }

    // added new method that helps send all the request from the client to the server.
    // read the responce from the server.
    public JsonNode sendRequest(String request) {
        try {
            dout.println(request);
            dout.flush();
            return OBJECT_MAPPER.readTree(din.readLine());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing server response as JSON.", e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading server response.", e);
        }

    }
}

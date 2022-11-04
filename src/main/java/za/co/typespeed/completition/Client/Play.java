package za.co.typespeed.completition.Client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import za.co.typespeed.completition.Json;
import za.co.typespeed.completition.Server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


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
        play.connect("localhost", 7000);

        //set robot name
//        String name = getInput("Register your name for the completion?");
//        String password;
//        do{
//            password = getInput("Password");
//        }while (password.length() < 4 );

        //creating and sending json with starting info for robot
        Json jData = new Json();
        jData.setMap("name", "siya");
        jData.setMap("password", "12345");

        System.out.println("Type submit once you done with name and password");
        // re-aranged the code to handly to send new required commands and arguments
        do {

            String instruction = getInput("siya" + "> What would you like to do next?").strip().toLowerCase();
            if(instruction.equals("1")){
                instruction = "submit";
            }
            Json jDataArguments = setClientRequst(jData, instruction);

            try {

                JsonNode worlds = play.sendRequest(String.valueOf(jDataArguments.getJsonData()));
                System.out.println(worlds.toString());

            } catch (IllegalArgumentException e) {
                play.disconnect();
            }
//            System.out.println(robot);
        } while (socket.isConnected());

    }

    public static Json setClientRequst(Json jData, String instruction){
        jData.setMap("command", instruction);
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
            return Server.OBJECT_MAPPER.readTree(din.readLine());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing server response as JSON.", e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading server response.", e);
        }

    }
}

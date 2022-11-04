package za.co.typespeed.completition.world;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Properties;

public class Port {

    private static ServerSocket PORT;
    private static int PortNumber;
    private final HashMap<String, String> commandLineArgs;
    private final Properties configFile;

    public Port(HashMap<String, String> commandLineArgs, Properties config) {
        this.commandLineArgs = commandLineArgs;
        this.configFile = config;
    }

    public static int getPortNumber() {
        return PortNumber;
    }

    public void setPort() throws IOException {

        if(commandLineArgs.containsKey("-p")) {
            PortNumber = Integer.parseInt(commandLineArgs.get("-p"));
        } else{
            PortNumber = Integer.parseInt(configFile.getProperty("port"));
        }
        initServerPort();
    }

    private void initServerPort() throws IOException {
        PORT = new ServerSocket(PortNumber);
    }
    public static ServerSocket getPORT() throws IOException {
        return PORT;
    }

}

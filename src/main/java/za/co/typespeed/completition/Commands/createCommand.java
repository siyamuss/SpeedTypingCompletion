package za.co.typespeed.completition.Commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class createCommand {

    static String name;
    static String commands;
    static String password;

    @JsonCreator
    public createCommand(@JsonProperty("name") String robot, @JsonProperty("command") String command, @JsonProperty("password") String password) {
        this.name = robot;
        this.commands = command;
        this.password = password;
    }

    public static String returnPassword(){
        return password;
    }

    public static String returnName() {
        return name;
    }

    public static String returnCommand() {
        return commands;
    }
}

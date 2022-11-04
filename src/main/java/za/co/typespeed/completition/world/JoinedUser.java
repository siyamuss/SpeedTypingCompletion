package za.co.typespeed.completition.world;

import za.co.typespeed.completition.Json;
import za.co.typespeed.completition.Commands.Command;


public class JoinedUser {

    private String password;
    private final String name;
    
    public JoinedUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Json handleCommand(Command command) {
        return command.execute(this);
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

}
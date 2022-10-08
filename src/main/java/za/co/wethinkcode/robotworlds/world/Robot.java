package za.co.wethinkcode.robotworlds.world;

import za.co.wethinkcode.robotworlds.Commands.Command;
import za.co.wethinkcode.robotworlds.Json;
import za.co.wethinkcode.robotworlds.Server.Server;

import java.util.ArrayList;
import java.util.Properties;


public class Robot {

    private int visibility;
    private String status;
    private String name;
    private int shots;
    private int shield;
    private ArrayList<String> History;

    public Robot(String name) {
        this.name = name;
        this.status = "Ready";
        this.visibility = 5;

    }

    public String getHasLaunched() {return Server.hasLaunched;}

    public int getVisibility() {return visibility;}

    public void setHasLaunched(String hasLaunched) {Server.hasLaunched = hasLaunched;}


    public String getStatus() {
        return this.status;
    }

    public Json handleCommand(Command command) {
        return command.execute(this);
    }

    @Override
    public String toString() {
       return "[" + this.getWorld().getPosition().getX() + "," + this.getWorld().getPosition().getY() + "] "
               + this.name + "> " + this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public AbstractWorld getWorld() {
        return World.getWorld();
    }

}
package za.co.typespeed.completition.Commands;

import za.co.typespeed.completition.Json;
import za.co.typespeed.completition.world.JoinedUser;

import java.sql.SQLException;

public class HelpCommand extends Command {

    public HelpCommand(String command) throws SQLException {
        super("help", command);
    }

    @Override
    public Json execute(JoinedUser target) {

        Json jData = new Json();


        String ds = "I can understand these commands:\n" +
                "OFF  - Shut down robot\n" +
                "HELP - provide information about commands\n" +
                "FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'\n" +
                "BACK - move backward by specified number of steps, e.g. 'BACKWARD 10'\n" +
                "LEFT - turns left, e.g. 'TURNED LEFT'\n" +
                "FIRE - Shoot the opponent, e.g. 'Target shot\n" +
                "RIGHT - turns right, e.g. 'TURNED RIGHT'";

        jData.setMap("Start typing ->", ds);
        return jData;

    }
}

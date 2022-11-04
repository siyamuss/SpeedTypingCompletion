package za.co.typespeed.completition.Server;

import za.co.typespeed.completition.Json;
import za.co.typespeed.completition.world.JoinedUser;
import za.co.typespeed.completition.Commands.Command;

import java.sql.SQLException;


public class ErrorHandling extends Command {

    public ErrorHandling(String name,String command) throws SQLException {
        super(name,command);
    }

    @Override
    public Json execute(JoinedUser target) {



            Json jData = new Json();
            jData.setMap("message",getName());

            Json jDataMsg = new Json();
            jDataMsg.setMap("result", "ERROR");
            jDataMsg.setMap("data",jData.getJsonData());

//            return jDataMsg;

            Json data = new Json();
            data.setMap("Launch Command", "To Start The Game use eg:{launch 5 5}");

            Json jData1 = new Json();
            jData.setMap("result", "ERROR");
            jData.setMap("message", data.getJsonData());
            return jData;
        }
    }

package za.co.typespeed.completition.Commands;

import za.co.typespeed.completition.Json;
import za.co.typespeed.completition.world.JoinedUser;

import java.sql.SQLException;
import java.util.List;


public class ErrorHandling extends Command {

    public ErrorHandling(String name,String command, List<String> argument) throws SQLException {
        super(name,command);
    }
    //starting the game json collecting data
    @Override
    public Json execute(JoinedUser target) {

//        if(!target.getHasLaunched().equals(getName())){

            Json jData = new Json();
            jData.setMap("message",getName());

            Json jDataMsg = new Json();
            jDataMsg.setMap("result", "ERROR");
            jDataMsg.setMap("data",jData.getJsonData());

//            return jDataMsg;

//        }else {
            Json data = new Json();
            data.setMap("Launch Command", "Start The Game.");
            data.setMap("Look Command", "Check For Objects {PIT, MINI, ROBOT, OBSTACLE, EDGE}.");
            data.setMap("State Command", "Get information of your Robot.");

            Json jData1 = new Json();
            jData.setMap("result", "ERROR");
            jData.setMap("message", data.getJsonData());
            return jData;
        }
    }


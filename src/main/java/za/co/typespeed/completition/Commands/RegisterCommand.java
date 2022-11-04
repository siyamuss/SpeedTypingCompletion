package za.co.typespeed.completition.Commands;

import za.co.typespeed.completition.Json;
import za.co.typespeed.completition.persistence.DataLoader;
import za.co.typespeed.completition.persistence.Tables;
import za.co.typespeed.completition.rdbms.DatabaseManager;
import za.co.typespeed.completition.world.JoinedUser;


import java.io.IOException;
import java.sql.SQLException;

public class RegisterCommand extends Command {

    @Override
    public Json execute(JoinedUser target) {

        Tables tables = new Tables(databaseManager);
        boolean canIPlay = tables.createPlayers();

        if(canIPlay){
            return okResponse(target, databaseManager);
        }else{
            return error_Response_UnableToAdd();
        }
    }

    private Json error_Response_UnableToAdd(){
        Json data = new Json();
        data.setMap("message", "Could not add the player");
        data.setMap("result", "ERROR");
        return data;
    }

    private Json error_Response_Too_Many(){
        Json data = new Json();
        data.setMap("message", "Sign in using your password, eg:{password = 12345}");
        data.setMap("result", "ERROR");
        return data;
    }
    private Json okResponse(JoinedUser target, DatabaseManager databaseManager){

        Json state = new Json();

        try {
            DataLoader dataLoader = new DataLoader(databaseManager);
            boolean v = dataLoader.insertPlayers(target);

            if(v){
                state.setMap("name",target.getName());
                state.setMap("position", "-");
                databaseManager.shutdown();
                return state;
            }else{
                databaseManager.shutdown();
                if(!isIsPlayerRegistered()){
                    setIsPlayerRegistered(true);
                    return error_Response_Too_Many();
                }return error_Response_Already_LoggedIn();


            }
        } catch (SQLException e) {
        throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private Json error_Response_Already_LoggedIn() {
        Json data = new Json();
        data.setMap("message", "Already Logged In, run! {START} TO PLAY");
        data.setMap("result", "ERROR");
        return data;
    }

    public RegisterCommand(String command) throws SQLException {
        super("launch",command);
    }

}

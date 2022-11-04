package za.co.typespeed.completition.Commands;

import za.co.typespeed.completition.persistence.Finder;
import za.co.typespeed.completition.Json;
import za.co.typespeed.completition.rdbms.DatabaseManager;
import za.co.typespeed.completition.world.JoinedUser;


import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordCommand extends Command{

    public PasswordCommand( String command) throws SQLException {
        super("password", command);
    }

    @Override
    public Json execute(JoinedUser target) {
        String[] password = getCommand().split(" ");
        target.setPassword(password[1].trim());
        Json state = new Json();

        try {

            DatabaseManager databaseManager = new DatabaseManager("jdbc:sqlite:src/main/resources/MyTables/Players.db");
            Finder dataFinder = new Finder(databaseManager);
            ResultSet v = dataFinder.replacePlayers(target);
            System.out.println(v+" im here in re");

            if(v.next()){
                state.setMap("name",v.getString("name"));
                state.setMap("password",v.getString("password"));
                state.setMap("position", v.getString("number"));
                return state;
            }else{
                databaseManager.shutdown();
                return error_Response_WrongPassword();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private Json error_Response_WrongPassword(){
        Json data = new Json();
        data.setMap("message", "Wrong password!!!");
        data.setMap("result", "ERROR");
        return data;
    }
}

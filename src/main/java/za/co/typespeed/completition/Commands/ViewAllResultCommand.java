package za.co.typespeed.completition.Commands;

import za.co.typespeed.completition.Json;
import za.co.typespeed.completition.rdbms.DatabaseManager;
import za.co.typespeed.completition.world.JoinedUser;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

// added state command
public class ViewAllResultCommand extends Command{


    public ViewAllResultCommand(String command) throws SQLException {
        super("state",command);
    }

    @Override
    public Json execute(JoinedUser target) {

        Json jData = new Json();
        jData.setMap("result", "OK");


        DatabaseManager databaseManager;
        try {
            databaseManager = new DatabaseManager("jdbc:sqlite:src/main/resources/MyTables/Players.db");
            String sql = databaseManager.readFileInSourceResources("find_all_players.sql");
            System.out.println(sql);
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(sql);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getResultSet();
            HashMap<String, String> allResult = new HashMap<>();
            while (rs.next()){
                allResult.put(rs.getString("number"),rs.getString("name"));
            }
            databaseManager.shutdown();
            jData.setMap("current position", allResult);
            return jData;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}



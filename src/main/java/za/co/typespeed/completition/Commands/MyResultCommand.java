package za.co.typespeed.completition.Commands;

import za.co.typespeed.completition.persistence.Finder;
import za.co.typespeed.completition.persistence.Results;
import za.co.typespeed.completition.Json;
import za.co.typespeed.completition.rdbms.DatabaseManager;
import za.co.typespeed.completition.world.JoinedUser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


public class MyResultCommand extends Command{

    public MyResultCommand(String command) throws SQLException {
        super("result", command );
    }

    @Override
    public Json execute( JoinedUser target ) {

        Json jData = new Json();
        jData.setMap("result", "OK");
        HashMap<String, String> allResult = new HashMap<>();
        try {
            DatabaseManager databaseManager = new DatabaseManager("jdbc:sqlite:src/main/resources/MyTables/SpeedResults.db");
            Finder finder = new Finder( databaseManager );
            List<Results> results = finder.findResultsLike(target.getName());
            databaseManager.shutdown();
            for(Results results1 : results){
                allResult.put("all",results.toString());
            }
            jData.setMap("your results", allResult);
            return jData;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

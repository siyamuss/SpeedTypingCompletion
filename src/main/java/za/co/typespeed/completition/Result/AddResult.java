package za.co.typespeed.completition.Result;

import za.co.typespeed.completition.Json;
import za.co.typespeed.completition.persistence.DataLoader;
import za.co.typespeed.completition.persistence.Tables;
import za.co.typespeed.completition.rdbms.DatabaseManager;

import java.io.IOException;
import java.sql.SQLException;

public class AddResult {

    private static DatabaseManager DBMS;
    private final String username;
    private double timeTaken;
    private String speechline;

    public AddResult(String speech, double time, String user) {
        this.speechline = speech;
        this.timeTaken = time;
        this.username = user;
        initDatabaseManager();

    }

    private void excuteAdd() throws SQLException, IOException {

        Json state = new Json();

        Tables tables = new Tables( DBMS );
        boolean canIPlay = tables.createResults();

        if(canIPlay){
            DataLoader dataLoader = new DataLoader( DBMS );
            boolean v = dataLoader.insertResults( username , speechline , String.valueOf( timeTaken ) );
            state.setMap("name","target.getName()");
            state.setMap("position", "-");
            DBMS.shutdown();
        }

    }
    private void initDatabaseManager(){
        try {
            DBMS = new DatabaseManager("jdbc:sqlite:src/main/resources/MyTables/SpeedResults.db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

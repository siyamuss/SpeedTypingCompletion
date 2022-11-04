/**
 * It takes the genres HashMap and inserts each key-value pair into the Genres table
 */
package za.co.typespeed.completition.persistence;

import za.co.typespeed.completition.rdbms.DatabaseManager;
import za.co.typespeed.completition.world.JoinedUser;

import java.io.IOException;
import java.sql.*;

public class DataLoader {
    private final Connection connection;
    private final DatabaseManager databaseManager;

    public DataLoader(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        this.connection = databaseManager.getConnection();
    }

    public boolean insertPlayers(JoinedUser target) throws SQLException, IOException {

        boolean excute = false;
        if(!check( target, "find_all_players.sql")) {
           excute = databaseManager.excuteInsertPlayer("insert_players.sql", target);
        }
        return excute;
    }

    public boolean insertResults( String user, String speechline, String timeTaken ) throws SQLException, IOException {
        return databaseManager.excuteInsertResult( "insert_results.sql" , user , speechline, timeTaken);
    }


    private boolean check(JoinedUser target, String fileName) throws SQLException, IOException {
        return databaseManager.excuteCheckPlayerSql(fileName, target);
    }

}



/**
 * It creates the tables in the database
 */
package za.co.typespeed.completition.persistence;

import za.co.typespeed.completition.rdbms.DatabaseManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Tables {
    private final Connection connection;
    private final DatabaseManager databaseManager;

    public Tables(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        this.connection = databaseManager.getConnection();
    }

    public boolean createPlayers() {

        boolean create;
        try {
            String sql = databaseManager.readFileInSourceResources("create_players.sql");
            System.out.println(sql+" IM HERE");
            create = createTable(sql);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return create;
    }

    public boolean createResults() {

        String sql = null;
        try {
            sql = databaseManager.readFileInSourceResources("create_results.sql");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        boolean create = createTable(sql);

        return create;
    }

    protected boolean createTable(String sql) {
    
        if(sql.contains("CREATE")){
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.execute();
                return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}

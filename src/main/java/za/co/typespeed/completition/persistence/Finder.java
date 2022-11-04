/**
 * It's a class that uses the database connection to find things in the database
 */
package za.co.typespeed.completition.persistence;

import za.co.typespeed.completition.rdbms.DatabaseManager;
import za.co.typespeed.completition.world.JoinedUser;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class Finder {

    private final Connection connection;
    private final DatabaseManager databaseManager;

    public Finder(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        this.connection = databaseManager.getConnection();
    }
    public List<Player> findAllPlayers() throws SQLException, IOException {
        return databaseManager.excuteSelectPlayerSql("find_all_players.sql" );
    }

    public List<Results> findAllResults() throws SQLException, IOException {
        return databaseManager.excuteSelectResultsSql("find_all_results.sql");
    }

    public List<Results> findResultsLike(String pattern) throws SQLException, IOException {
        String sql  = "SELECT * FROM SpeedResults WHERE name LIKE ?";
        return databaseManager.executefindResultLikeSql(sql, pattern);

    }
    public ResultSet replacePlayers(JoinedUser target) {

        ResultSet rs = null;
        try {
            // replace with password
            String sql  = "SELECT * FROM Players WHERE name LIKE ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, target.getName());
            if(preparedStatement.execute()){
                 rs = preparedStatement.executeQuery();

                if(rs.getString("name").equals(target.getName())
                        && rs.getString("password").equals(target.getPassword())){
                    return rs;
                }
            };

            return rs;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

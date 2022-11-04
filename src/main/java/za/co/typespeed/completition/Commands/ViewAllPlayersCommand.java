package za.co.typespeed.completition.Commands;

import za.co.typespeed.completition.Json;
import za.co.typespeed.completition.persistence.Finder;
import za.co.typespeed.completition.persistence.Player;
import za.co.typespeed.completition.world.JoinedUser;
import za.co.typespeed.completition.rdbms.DatabaseManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

// added state command
public class ViewAllPlayersCommand extends Command{

    public ViewAllPlayersCommand(String command) throws SQLException {
        super("state",command);
    }

    @Override
    public Json execute(JoinedUser target) {

        Json jData = new Json();
        jData.setMap("result", "OK");
        HashMap<String, String> allPlayers = new HashMap<>();
        try {
            DatabaseManager databaseManager = new DatabaseManager("jdbc:sqlite:src/main/resources/MyTables/Players.db");
            Finder finder = new Finder(databaseManager);
            List<Player> players = finder.findAllPlayers();
            databaseManager.shutdown();
            for(Player player : players){
                allPlayers.put("all",player.toString());
            }
            jData.setMap("current position", players);
            return jData;
            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

package za.co.typespeed.completition.rdbms;

import za.co.typespeed.completition.persistence.Player;
import za.co.typespeed.completition.persistence.Results;
import za.co.typespeed.completition.world.JoinedUser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * A utility class for executing sql statements
 */
public class DatabaseManager {
    private final Connection connection;

    /**
     * Connect to an in-memory SQLite database
     *
     * @throws SQLException if the connection failed
     */
    public DatabaseManager(String location) throws SQLException {
        this.connection = DriverManager.getConnection(location);
    }

    /**
     * Get the database connection
     * @return the database connection
     */
    public Connection getConnection() {
        return this.connection;
    }

    /**
     * Close the database connection
     *
     * @throws SQLException the connection could not be closed
     */
    public void shutdown() throws SQLException {
        connection.close();
    }

    public List<Results> excuteSelectResultsSql(String filename) throws IOException {
        String sql = readFileInSourceResources(filename);
        System.out.println(sql+" fffff");
        List<Results> results = new ArrayList<>();
        try (final PreparedStatement s = connection.prepareStatement(sql)) {
            s.execute();
            ResultSet rs = s.getResultSet();
            while (rs.next()){
                results.add(new Results(rs.getString("name"), rs.getString("line") ,  rs.getString("speedTime")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    public boolean excuteCheckPlayerSql(String filename, JoinedUser target) throws IOException, SQLException {
        String sql = readFileInSourceResources(filename);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()){

            if(rs.getString("name").equals(target.getName())
                    && rs.getString("password").equals(target.getPassword())){
                return true;
            }
        }
        return false;
    }

    public boolean excuteInsertPlayer(String filename, JoinedUser target) throws IOException, SQLException {
        String sql = readFileInSourceResources(filename);

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "-");
        preparedStatement.setString(2, target.getName());
        preparedStatement.setString(3, target.getPassword());
        return preparedStatement.execute();
    }

    public boolean excuteInsertResult(String filename, String user, String speechline, String timeTaken) throws IOException, SQLException {
        String sql = readFileInSourceResources(filename);

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user );
        preparedStatement.setString(2, speechline);
        preparedStatement.setString(3, timeTaken);

        return preparedStatement.execute();
    }

    public List<Player> excuteSelectPlayerSql(String filename) throws IOException {
        String sql = readFileInSourceResources(filename);
        List<Player> Player = new ArrayList<>();
        try (final PreparedStatement s = connection.prepareStatement(sql)) {
            s.execute();
            ResultSet rs = s.getResultSet();
            while (rs.next()){
                Player.add(new Player(rs.getString("name"), rs.getString("number")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Player;
    }

    public boolean verifySelect(String filename, Predicate<ResultSet> validator) throws IOException {
        String sql = readFileInSourceResources(filename);
        try (final PreparedStatement s = connection.prepareStatement(sql)) {
            if (!s.execute()) return false;
            return validator.test(s.getResultSet());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * "Verify that the table exists in the database."
     *
     * The function is a bit more complicated than that, but that's the gist of it
     *
     * @param tableValidator This is an instance of the TableValidator interface. This interface has a single method called
     * validate. This method takes a DatabaseMetaData object as a parameter and returns a boolean.
     * @return A boolean value.
     */
    public boolean verifyTable(TableValidator tableValidator) throws SQLException {
        DatabaseMetaData metaData = this.connection.getMetaData();
        return tableValidator.validate(metaData);
    }

    public List<Results> executefindResultLikeSql(String sql, String pattern) throws IOException, SQLException {
        List<Results> findResultLike = new ArrayList<>();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,pattern);
        preparedStatement.execute();
        ResultSet rs = preparedStatement.getResultSet();
        while (rs.next()){
            findResultLike.add(new Results(rs.getString("name"), rs.getString("line") ,  rs.getString("speedTime")));
        }
        return findResultLike;
    }



    /**
     * > Reads a file from the source resources folder
     *
     * @param filename the name of the file to read
     * @return The contents of the file.
     */
    public String readFileInSourceResources(String filename) throws IOException {
        return readFile(getFile(filename));
    }

    /**
     * Get the path to the file with the given filename in the sql directory of the resources directory.
     *
     * @param filename The name of the file to read.
     * @return A Path object
     */
    private Path getFile(String filename) {
        return Paths.get("src", "main", "resources", "sql", filename);
    }


    /**
     * It reads the contents of a file, trims each line, and joins them together with a space
     *
     * @param path The path to the file to read.
     * @return A string of the contents of the file.
     */
    private String readFile(Path path) throws IOException {
        List<String> contents = Files.readAllLines(path);
        return contents.stream().map(String::trim).collect(Collectors.joining(" "));
    }
}

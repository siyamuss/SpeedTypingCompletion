/**
 * It's a class that implements the Predicate interface, and it's used to test the result set of a query
 */
package za.co.typespeed.completition.rdbms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class AllBooksValidator implements Predicate<ResultSet> {
    /**
     * > The function iterates through the result set and adds the title and description to a map.
     *
     * The function then calls the `checkRows` function to check if the map contains the expected values.
     *
     * @param resultSet The ResultSet object that is returned from the database.
     * @return A boolean value.
     */
    @Override
    public boolean test(ResultSet resultSet) {

        Map<String, String> rows = new HashMap<>();
        try {
            while (resultSet.next()) {
               rows.put(resultSet.getString("title"), resultSet.getString("description"));
            }
            return checkRows(rows);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * It checks that the given map contains the expected values
     *
     * @param rows a Map of book titles to their categories
     * @return A boolean value.
     */
    private boolean checkRows(Map<String, String> rows) {
        Map<String, String> expected = Map.of(
                "Test Driven Development", "Programming",
                "Programming in Haskell", "Programming",
                "Scatterlings of Africa", "Biography");
        if (rows.size() != expected.size()) return false;
        return expected.entrySet().stream()
                .allMatch(e -> e.getValue().equals(rows.get(e.getKey())));
    }
}

/**
 * It checks that the table has the correct number of rows and that the rows have the correct codes
 * similer to AllBooksValidator and AllGenresValidator classes
 */
package za.co.typespeed.completition.rdbms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class IoGenresValidator implements Predicate<ResultSet> {
    /**
     * > The function takes a ResultSet object as input and returns a boolean value
     *
     * @param resultSet The ResultSet object that is returned from the database.
     * @return A boolean value
     */
    @Override
    public boolean test(ResultSet resultSet) {

        Map<String, String> rows = new HashMap<>();
        try {
            while (resultSet.next()) {
               rows.put(resultSet.getString("code"), resultSet.getString("description"));
            }
            return checkRows(rows);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * It checks if the map contains exactly two rows, one with the key "BIO" and one with the key "SCIFI"
     *
     * @param rows a map of row codes to row names
     * @return A boolean value.
     */
    private boolean checkRows(Map<String, String> rows) {
        List<String> codes = Arrays.asList("BIO", "SCIFI");
        if (rows.size() != codes.size()) return false;
        return rows.keySet().containsAll(codes);
    }
}

/**
 * It checks that the result set contains the correct number of rows, and that the rows contain the correct values
 * just like AllBooksValidator class
 */
package za.co.typespeed.completition.rdbms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class AllGenresValidator implements Predicate<ResultSet> {
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
     * It returns true if the map has exactly three keys, and those keys are "PROG", "BIO", and "SCIFI"
     *
     * @param rows a Map of the rows in the table, where the key is the row code and the value is the row name.
     * @return A boolean value.
     */
    private boolean checkRows(Map<String, String> rows) {
        List<String> codes = Arrays.asList("PROG", "BIO", "SCIFI");
        if (rows.size() != codes.size()) return false;
        return rows.keySet().containsAll(codes);
    }
}

package za.co.typespeed.completition.rdbms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Predicate;

public class HistoryBooksValidator implements Predicate<ResultSet> {
    @Override
    public boolean test(ResultSet resultSet) {

        try {
            if (!resultSet.next()) return false;
            return resultSet.getInt(1) == 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

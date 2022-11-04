/**
 * It validates the columns of the genres table
 * similer to the BooksTableValidator
 */
package za.co.typespeed.completition.rdbms;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayersTableValidator extends AbstractTableValidator {

    // It's calling the constructor of the parent class.
    public PlayersTableValidator(String tablename) {
        super(tablename);
    }

    /**
     * > This function iterates through the columns of the table and checks if the column is correct
     *
     * @param rs ResultSet object
     * @return A boolean value.
     */
    @Override
    public boolean validateColumns(ResultSet rs) throws SQLException {
        while (rs.next()) {
            if (!isColumnCorrect(rs.getString("name"), rs)) return false;
        }
        return true;
    }

    /**
     * If the next row in the ResultSet is not null, and the column name is 'code', return true.
     *
     * @param rs The ResultSet object that contains the metadata of the table.
     * @return The name of the primary key column.
     */
    @Override
    public boolean validatePrimaryKey(ResultSet rs) throws SQLException {
        if (!rs.next()) return false;
        return rs.getString("COLUMN_NAME").equals("name");
    }

    /**
     * > If you want to validate the foreign key, override this function
     *
     * @param rs The ResultSet object that contains the data to be validated.
     * @return A boolean value.
     */
    @Override
    public boolean validateForeignKey(ResultSet rs) {
        return true;
    }

    /**
     * "If the column is code, then the column type must be TEXT and it must not be nullable. If the column is description,
     * then the column type must be TEXT and it must not be nullable. Otherwise, the column is not correct."
     *
     * The switch expression is a bit more than the if-else chain, but it's also more readable
     *
     * @param column the name of the column to check
     * @param rs The ResultSet object that contains the metadata of the table.
     * @return A boolean value.
     */
    private boolean isColumnCorrect(String column, ResultSet rs) throws SQLException {
        return switch (column) {
            case "code" -> rs.getString("TYPE_NAME").equals("TEXT") &&
                    rs.getString("IS_NULLABLE").equals("NO");
            case "description" -> rs.getString("TYPE_NAME").equals("TEXT") &&
                    rs.getString("IS_NULLABLE").equals("NO");
            default -> false;
        };
    }
}

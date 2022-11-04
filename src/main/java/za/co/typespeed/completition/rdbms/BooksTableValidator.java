/**
 * It validates the columns, primary key and foreign key of the Books table
 */
package za.co.typespeed.completition.rdbms;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BooksTableValidator extends AbstractTableValidator {

    // Calling the constructor of the parent class.
    public BooksTableValidator(String tablename) {
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
            if (!isColumnCorrect(rs.getString("COLUMN_NAME"), rs)) return false;
        }
        return true;
    }

    /**
     * If the ResultSet has a row, and the row's COLUMN_NAME is 'id', then return true.
     *
     * @param rs The ResultSet object that contains the results of the query.
     * @return The name of the primary key column.
     */
    @Override
    public boolean validatePrimaryKey(ResultSet rs) throws SQLException {
        if (!rs.next()) return false;
        return rs.getString("COLUMN_NAME").equals("id");
    }

    /**
     * It checks that the foreign key on the Books table's genre_code column references the Genres table's code
     * column
     *
     * @param rs The ResultSet returned by the DatabaseMetaData.getImportedKeys() method.
     * @return A boolean value.
     */
    @Override
    public boolean validateForeignKey(ResultSet rs) throws SQLException {
        if (!rs.next()) return false;
        return rs.getString("PKTABLE_NAME").equals("Genres") &&
               rs.getString("PKCOLUMN_NAME").equals("code") &&
               rs.getString("FKTABLE_NAME").equals("Books") &&
               rs.getString("FKCOLUMN_NAME").equals("genre_code");
    }

    /**
     * It returns true if the column is correct, false otherwise
     *
     * @param column the name of the column to check
     * @param rs The ResultSet object that contains the metadata of the table.
     * @return A boolean value.
     */
    private boolean isColumnCorrect(String column, ResultSet rs) throws SQLException {
        return switch (column) {
            case "id" -> rs.getString("TYPE_NAME").equals("INTEGER") &&
                    rs.getString("IS_NULLABLE").equals("NO") &&
                    rs.getString("IS_AUTOINCREMENT").equals("YES");
            case "title" -> rs.getString("TYPE_NAME").equals("TEXT") &&
                    rs.getString("IS_NULLABLE").equals("NO");
            case "genre_code" -> rs.getString("TYPE_NAME").equals("TEXT") &&
                    rs.getString("IS_NULLABLE").equals("NO");
            default -> false;
        };
    }
}

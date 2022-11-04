/**
 * It checks if the table exists in the database, and if it does, it checks if the table has a primary key, and if it does,
 * it checks if the table has a foreign key
 */
package za.co.typespeed.completition.rdbms;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractTableValidator implements TableValidator {
    protected final String tablename;

    // A constructor that takes a string as a parameter and sets the tablename to the string.
    public AbstractTableValidator(String tablename) {
        this.tablename = tablename;
    }

    public abstract boolean validateColumns(ResultSet rs) throws SQLException;
    public abstract boolean validatePrimaryKey(ResultSet rs) throws SQLException;
    public abstract boolean validateForeignKey(ResultSet rs) throws SQLException;

    /**
     * If the table exists, and the columns, primary keys, and foreign keys are correct, then return true.
     *
     * @param metaData The DatabaseMetaData object that is used to query the database.
     * @return A boolean value.
     */
    public boolean validate(DatabaseMetaData metaData) throws SQLException {
        return verifyTableExists(metaData) &&
                verifyColumns(metaData) &&
                verifyPrimaryKeys(metaData) &&
                verifyForeignKeys(metaData);
    }


    /**
     * If the table exists, return true, otherwise return false.
     *
     * @param metaData The metadata of the database.
     * @return A boolean value.
     */
    private boolean verifyTableExists(DatabaseMetaData metaData) throws SQLException {
        try (final ResultSet rs = metaData.getTables(null, "sqlite_master", tablename, new String[]{"TABLE"})) {
            if (rs.next()) return true;
        }
        return false;
    }

    /**
     * > It checks if the table exists in the database
     *
     * @param metaData The DatabaseMetaData object
     * @return A boolean value.
     */
    private boolean verifyColumns(DatabaseMetaData metaData) throws SQLException {
    /**
     * > It checks if the table has a primary key
     *
     * @param metaData The DatabaseMetaData object that you get from the connection object.
     * @return A boolean value.
     */
        try (final ResultSet rs = metaData.getColumns(null, "sqlite_master", tablename, null)) {
            if (validateColumns(rs)) return true;
        }
        return false;
    }

    /**
     * > If the table has a primary key, return true
     *
     * @param metaData The DatabaseMetaData object that you get from the connection object.
     * @return A boolean value.
     */
    private boolean verifyPrimaryKeys(DatabaseMetaData metaData) throws SQLException {
        try (final ResultSet rs = metaData.getPrimaryKeys(null, "sqlite_master", tablename)) {
            if (validatePrimaryKey(rs)) return true;
        }
        return false;
    }


    /**
     * It checks if the foreign key exists in the database
     *
     * @param metaData The DatabaseMetaData object that you get from the connection object.
     * @return A boolean value.
     */
    private boolean verifyForeignKeys(DatabaseMetaData metaData) throws SQLException {
        try (final ResultSet rs = metaData.getImportedKeys(null, "sqlite_master", tablename)) {
            if (validateForeignKey(rs)) return true;
        }
        return false;
    }
}

/**
 * It's a class that validates the structure of a table in a database
 */
package za.co.typespeed.completition.rdbms;

import java.sql.ResultSet;


public class SampleTableValidator extends AbstractTableValidator {
    // It's a constructor that calls the constructor of the parent class
    public SampleTableValidator(String tablename) {
        super(tablename);
    }

    /**
     * If you want to validate the columns in the result set, override this function and return true if the columns are
     * valid, false otherwise.
     *
     * @param rs The ResultSet object that contains the data to be validated.
     * @return A boolean value.
     */
    @Override
    public boolean validateColumns(ResultSet rs) {
        return true;
    }

    /**
     * > If you want to validate the primary key of a table, override this function and return true if the primary key is
     * valid
     *
     * @param rs The ResultSet object that contains the data to be validated.
     * @return A boolean value.
     */
    @Override
    public boolean validatePrimaryKey(ResultSet rs) {
        return true;
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
}

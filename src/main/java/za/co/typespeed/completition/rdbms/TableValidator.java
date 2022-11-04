package za.co.typespeed.completition.rdbms;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

// An interface that is used to validate the existence of a table in the database.
public interface TableValidator {
    boolean validate(DatabaseMetaData metaData) throws SQLException;
}

package com.company.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface INTdatabase {
    //For creating and deleting tables in SQL
    public void createTables(Connection connection);
    public void deleteTables(Connection connection) throws SQLException;
}

package com.company.db;

import com.company.bank.Banksystem;

import java.sql.Connection;
import java.sql.SQLException;

public class DBsystem {

    //REFRESHING DATA IN POSTGRESQL
    public void refresh(Banksystem banksystem, Connection connection) throws SQLException {
        //DELETE OLD DATA
        banksystem.deleteSQLdata(connection);
        //LOAD NEW
        banksystem.addCustomerSQL(connection);
        banksystem.addWorkerSQL(connection);
        System.out.println("UPDATED!");
    }
    public void data(Banksystem banksystem, Connection connection) throws SQLException {
        banksystem.showSQLdata(connection);
    }
    public void downloaddata(Banksystem banksystem, Connection connection) throws SQLException {
        banksystem.customerdownloadFromSQL(connection);
        banksystem.workerdownloadFromSQL(connection);
        System.out.println("DOWNLOADED!");
    }
}

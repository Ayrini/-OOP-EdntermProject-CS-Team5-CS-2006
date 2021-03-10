package com.company.db;

import com.company.bank.Banksystem;

import java.sql.Connection;
import java.sql.SQLException;

public class DBsystem {


    public void refresh(Banksystem banksystem, Connection connection) throws SQLException {
        banksystem.deleteSQLdata(connection);
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

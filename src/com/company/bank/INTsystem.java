package com.company.bank;

import com.company.exception.UserExistsException;
import com.company.users.Customer;
import com.company.users.Worker;

import java.sql.Connection;
import java.sql.SQLException;

public interface INTsystem {
    //add Users to local arrays
    public void addCustomer(Customer customer) throws UserExistsException;
    public void addWorker(Worker worker) throws UserExistsException;

    //add users to sql server
    public void addCustomerSQL(Connection connection) throws SQLException;
    public void addWorkerSQL(Connection connection) throws SQLException;

    //dowload data from sql server
    //If I have empty arrays of users, then I can just download users from the server
    public void workerdownloadFromSQL(Connection connection) throws SQLException;
    public void customerdownloadFromSQL(Connection connection) throws SQLException;

    //deleting data from sql
    public void deleteSQLdata(Connection connection) throws SQLException;

    //check to user exist
    public boolean customerExists(Customer customer);
    public boolean workersExists(Worker worker);

    //take data from sql
    public void showSQLdata(Connection connection) throws SQLException;

    //get number of users
    public int numberofusers(int count, boolean ind);

    //calculate total money in bank
    public int bankmoney();

    //transfer money to another user
    public void transfermoney();

    //add money
    public void addmoney();

    //Take money
    public void takemoney();

    //Смена валюты
    public void changeCurrency();

    //delete user(Customer/Worker)
    public void deleteworker();
    public void deletecustomer();

    public void setBankname(String bankname);
    public String getBankname();
    public int getCurrencyID();

}

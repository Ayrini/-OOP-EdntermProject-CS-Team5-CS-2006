package com.company.run;

import com.company.bank.Bankinfo;
import com.company.bank.Banksystem;
import com.company.db.*;
import com.company.exception.UserExistsException;
import com.company.users.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class systemRUN {

    public void run() throws UserExistsException {
        // write your code here
        Scanner in = new Scanner(System.in);//for input
        System.out.println("Bank Name:");
        Banksystem banksystem = new Banksystem(in.next());
        DBsystem dBsystem = new DBsystem();

        //---------SQL-----------

        String jdbcURL = "jdbc:postgresql://localhost:5432/oopEndterm";
        String username = "postgres";
        String password = "Qweef123";


        //------------------------
        String probel = "";
        for (int i = 0; i < 70; i++) {
            probel += "*";
        }//just for spacing
        //------------------------
        ArrayList<Customer> customersm = new ArrayList<>();
        ArrayList<Worker> workersm = new ArrayList<>();

        INTdatabase dataBase = new DataBase();
        Bankinfo bankinfo = new Bankinfo();
        //We need this for create users
        String fname;
        String lname;
        String position;
        int age;
        int gender;
        int cash;
        int salary;

        boolean a = true;
        int counter = 0;
        int workercounter = 2;


        //TESTING THE COMPILATION OF CODE
        Customer[] customers = new Customer[2];
        customers[0] = new Customer("Johan", "Felix", 22, 1, 120000);
        customers[1] = new Customer("Johan", "Felix", 22, 1, 120000);

        Worker worker1 = new Worker("Samuel", "Manuel", "Director", 900000, 45, 1);
        Worker worker2 = new Worker("Sandy", "Ramel", "Manager", 500000, 35, 0);
        banksystem.addWorker(worker1);
        banksystem.addWorker(worker2);
        workersm.add(worker1);
        workersm.add(worker2);

        for (int i = 0; i < customers.length; i++) {
            try {
                banksystem.addCustomer(customers[i]);
                customersm.add(customers[i]);
                counter++;
            } catch (UserExistsException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("Finished " + i);
            }
        }
        System.out.println(probel + "\n\n\n\n");

        //MENU
        while (a) {
            System.out.println(probel);
            int menu;
            int menu1;
            int menu2;
            boolean checkvalues = false;
            System.out.println("0. Bank info\n1. User list\n2. Refresh data base" +
                    "\n3. View data in SQL\n4. Download SQLdata\n5. Options\n6. Leave");
            menu = in.nextInt();
            if (menu == 0) {
                bankinfo.info(banksystem,workercounter,counter);
            }
            if (menu == 1) {
                System.out.println("0. Go back\n1. Users\n2. Add customer" +
                        "\n3. Add worker\n4. Transfer money\n5. Take money\n6. Delete user");
                menu1 = in.nextInt();
                if (menu1 == 1) {
                    System.out.println("Already registered " + bankinfo.numberofusers(banksystem,counter,true) + " Customer and " + bankinfo.numberofusers(banksystem,workercounter, false) + " Worker\n" + banksystem + "\n" + probel);
                }
                if (menu1 == 2) {
                    while (checkvalues == false) {
                        System.out.println("FName and Lname");
                        fname = in.next();
                        lname = in.next();
                        System.out.println("Age and gender(1 male, 0 female)");

                        age = in.nextInt();
                        gender = in.nextInt();
                        if ((age > 100 || age < 16) || (gender > 1 || gender < 0)) {
                            checkvalues = false;
                            System.out.println("Invalid values");
                        } else {
                            checkvalues = true;


                            System.out.println("Cash:");
                            cash = in.nextInt();

                            Customer customer2 = new Customer(fname, lname, age, gender, cash);
                            try {
                                banksystem.addCustomer(customer2);
                                customersm.add(customer2);
                                counter++;
                            } catch (UserExistsException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                }
                if (menu1 == 3) {
                    while (checkvalues == false) {
                        System.out.println("FName and Lname");
                        fname = in.next();
                        lname = in.next();
                        System.out.println("Position and Salary");
                        position = in.next();
                        salary = in.nextInt();
                        System.out.println("Age and gender(1 male, 0 female)");
                        age = in.nextInt();
                        gender = in.nextInt();

                        if ((age > 100 || age < 16) || (gender > 1 || gender < 0)) {
                            checkvalues = false;
                            System.out.println("Invalid values");
                        } else {
                            checkvalues = true;

                            Worker worker = new Worker(fname, lname, position, salary, age, gender);
                            try {
                                banksystem.addWorker(worker);
                                workersm.add(worker);
                                workercounter++;
                            } catch (UserExistsException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                }
                if (menu1==4){
                    banksystem.transfermoney();
                }
                if (menu1==5){
                    banksystem.takemoney();
                }
                if (menu1==6){
                    System.out.println("0. Go back\n1. Customer\n2. Worker");
                    menu2=in.nextInt();
                    if(menu2==1){
                        banksystem.deletecustomer();
                    }
                    if(menu2==2){
                        banksystem.deleteworker();
                    }
                }
                if(menu1 == 7){
                    System.out.println("*Secret function*");
                    System.out.println("Type 1 if you wan't to add money to user. 0 Go back");
                    if(in.nextInt()==1){
                        banksystem.addmoney();
                    }
                }
            }
            if (menu == 2) {
                try {
                    Connection connection = DriverManager.getConnection(jdbcURL, username, password);
                    System.out.println("Connected to server");
                    dBsystem.refresh(banksystem,connection);
                    connection.close();

                } catch (
                        SQLException e) {
                    System.out.println("Error in connecting to PostgresSQL server");
                    e.printStackTrace();
                }
            }
            if (menu == 3) {
                try {
                    Connection connection = DriverManager.getConnection(jdbcURL, username, password);
                    System.out.println("Connected to server");
                    dBsystem.data(banksystem,connection);
                    connection.close();
                } catch (
                        SQLException e) {
                    System.out.println("Error in connecting to PostgresSQL server");
                    e.printStackTrace();
                }
            }
            if (menu == 4) {
                try {
                    Connection connection = DriverManager.getConnection(jdbcURL, username, password);
                    System.out.println("Connected to server");
                    dBsystem.downloaddata(banksystem,connection);
                    connection.close();

                } catch (
                        SQLException e) {
                    System.out.println("Error in connecting to PostgresSQL server");
                    e.printStackTrace();
                }
            }
            if (menu == 5){
                System.out.println("0. Go back\n1. Create tables\n2. Delete tables\n3. Change currency");
                menu1=in.nextInt();
                if(menu1 == 0){
                    System.out.println("OK");
                }
                if(menu1 == 1) {
                    try {
                        Connection connection = DriverManager.getConnection(jdbcURL, username, password);
                        System.out.println("Connected to server");
                        dataBase.createTables(connection);
                        connection.close();
                    } catch (
                            SQLException e) {
                        System.out.println("Error in connecting to PostgresSQL server");
                        e.printStackTrace();
                    }
                }
                if(menu1 == 2){
                    try {
                        Connection connection = DriverManager.getConnection(jdbcURL, username, password);
                        System.out.println("Connected to server");
                        dataBase.deleteTables(connection);
                        connection.close();
                    } catch (
                            SQLException e) {
                        System.out.println("Error in connecting to PostgresSQL server");
                        e.printStackTrace();
                    }
                }
                if(menu1 == 3){
                    banksystem.changeCurrency();
                }
            }
            if(menu == 6){
                System.out.println("Sure? 1(YES) 0(NO)");
                if(in.nextInt()==1){
                    a = false;
                    System.out.println("Bye...");
                }else
                    System.out.println("ok");
            }
        }
    }
}


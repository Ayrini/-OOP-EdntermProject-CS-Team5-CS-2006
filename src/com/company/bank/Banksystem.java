package com.company.bank;

import com.company.exception.UserExistsException;
import com.company.users.Customer;
import com.company.users.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;



public class Banksystem implements INTsystem{
    private String bankname;
    int currencyID = 0;//0 KZT/1 USD/2 EUR
    ArrayList<Customer> customers= new ArrayList<>();
    ArrayList<Worker> workers= new ArrayList<>();
    public Banksystem(String name){
        bankname = name;
    }


    //add USER into array
    public void addCustomer(Customer customer) throws UserExistsException {
        if (customerExists(customer)){
            throw new UserExistsException("Customer "+ customer.getF_Name()+" already exists");
        }
        customers.add(customer);
    }
    public void addWorker(Worker worker) throws UserExistsException {
        if (workersExists(worker)){
            throw new UserExistsException("Customer "+ worker.getF_Name()+" already exists");
        }
        workers.add(worker);
    }

    //add users into postgresql
    public void addCustomerSQL(Connection connection) throws SQLException {
        String sql1 = "INSERT INTO Customers (first_name, last_name, age, gender, money)"
                + " VALUES (?,?,?,?,?)";
        PreparedStatement statement1 = connection.prepareStatement(sql1);
        for(int i = 0; i < customers.size();i++) {
            statement1.setString(1,customers.get(i).getF_Name());
            statement1.setString(2,customers.get(i).getL_Name());
            statement1.setInt(3,customers.get(i).getAge());
            statement1.setInt(4,customers.get(i).getGender());
            statement1.setDouble(5,customers.get(i).getMoney());
            int rows = statement1.executeUpdate();
            if (rows > 0) {
                System.out.println("new user added");
            }
        }
    }
    public void addWorkerSQL(Connection connection) throws SQLException {
        String sql1 = "INSERT INTO Workers (first_name, last_name, position , age, gender,salary)"
                + " VALUES (?,?,?,?,?,?)";
        PreparedStatement statement1 = connection.prepareStatement(sql1);
        for(int i = 0; i < workers.size();i++) {
            statement1.setString(1,workers.get(i).getF_Name());
            statement1.setString(2,workers.get(i).getL_Name());
            statement1.setString(3,workers.get(i).getPosition());
            statement1.setInt(4,workers.get(i).getAge());
            statement1.setInt(5,workers.get(i).getGender());
            statement1.setInt(6,workers.get(i).getSalary());

            int rows = statement1.executeUpdate();
            if (rows > 0) {
                System.out.println("new Worker added");
            }
        }
    }
    //Для того что бы скачать данные с SQL, если их нету у тебя в Java Array
    //Так сказать серверная инфа
    //А в компиляторе локальная
    public void workerdownloadFromSQL(Connection connection) throws SQLException{
        String sql0 = "SELECT * FROM workers";
        Statement statement = connection.createStatement();

        //workers:
        //first_name, last_name, position , age, gender, salary
        ResultSet result = statement.executeQuery(sql0);
        workers.clear();
        while (result.next()){
            int id = result.getInt("id");
            String firstname = result.getString("first_name");
            String lastname = result.getString("last_name");
            String position = result.getString("position");
            int age = result.getInt("age");
            int salary = result.getInt("salary");
            int gender = result.getInt("gender");
            Worker worker = new Worker(firstname,lastname,position,salary,age,gender);
            workers.add(worker);
        }

    } public void customerdownloadFromSQL(Connection connection) throws SQLException{
        //customers:
        //first_name, last_name, age, gender, money
        String sql1 = "SELECT * FROM customers";
        Statement statement1 = connection.createStatement();

        ResultSet result1 = statement1.executeQuery(sql1);
        customers.clear();
        while(result1.next()){
            int id = result1.getInt("id");
            String firstname = result1.getString("first_name");
            String lastname = result1.getString("last_name");
            int age = result1.getInt("age");
            int gender = result1.getInt("gender");
            double money = result1.getDouble("money");

            Customer customer = new Customer(firstname,lastname,age,gender,money);
            customers.add(customer);
        }
    }
    public void deleteSQLdata(Connection connection) throws SQLException {
        String sqlDelete = "DELETE FROM customers; DELETE FROM workers";

        Statement statement = connection.createStatement();

        int check = statement.executeUpdate(sqlDelete);
        if (check > 0) {
            System.out.println("\nSql data deleted");
        }
    }
    public boolean customerExists(Customer customer){
       for(int i = 0; i< customers.size();i++){
            //Проверка Юзера на Exist
            if(customer.getF_Name().equals(customers.get(i).getF_Name())
                    && customer.getL_Name().equals(customers.get(i).getL_Name())){
                System.out.println("check");
                return true;
            }
        }
        return  false;
    }
    public boolean workersExists(Worker worker){
       for(int i = 0; i< workers.size();i++){
            //Проверка Юзера на Exist
            if(worker.getF_Name().equals(workers.get(i).getF_Name())
                    && worker.getL_Name().equals(workers.get(i).getL_Name())){
                System.out.println("check");
                return true;
            }
        }
        return  false;
    }
    public void showSQLdata(Connection connection) throws SQLException {

        System.out.println("\n\nCustomers:");
        String sql1 = "SELECT * FROM customers";
        Statement statement1 = connection.createStatement();

        ResultSet result1 = statement1.executeQuery(sql1);
        while(result1.next()){
            int id = result1.getInt("id");
            String firstname = result1.getString("first_name");
            String lastname = result1.getString("last_name");
            int age = result1.getInt("age");
            int gender = result1.getInt("gender");
            double money = result1.getDouble("money");
            Customer customer = new Customer(firstname,lastname,age,gender,money);
            System.out.println("First name: " + firstname + ", last name: " + lastname
                    + ", age: " + age + " ,money: " + money
                    + ", gender: " + gender);
        }


        System.out.println("\n\nWorkers:");
        String sql0 = "SELECT * FROM workers";
        Statement statement = connection.createStatement();

        //workers:
        //first_name, last_name, position , age, gender, salary
        ResultSet result = statement.executeQuery(sql0);

        while (result.next()) {
            int id = result.getInt("id");
            String firstname = result.getString("first_name");
            String lastname = result.getString("last_name");
            String position = result.getString("position");
            int age = result.getInt("age");
            int salary = result.getInt("salary");
            int gender = result.getInt("gender");
            Worker worker = new Worker(firstname, lastname, position, salary, age, gender);
            System.out.println("First name: " + firstname + ", last name: " + lastname
                    + ", position: " + position + ", age: " + age + ",salary: " + salary
                    + ", gender: " + gender);
        }


    }


    public void transfermoney(){
        Scanner in = new Scanner(System.in);//for input
        int j = 1;
        int temp;
        int temp1;
        int cash;
        int tempcash;
        for(int i =0; i<customers.size(); i++){
            System.out.println(j+". "+customers.get(i).getF_Name()+" "+ customers.get(i).getL_Name()
                    + " | " + customers.get(i).getMoney());
            j++;
        }
        System.out.println("Which customer? type index:");
        temp=in.nextInt();
        System.out.println("to whom?");
        temp1=in.nextInt();
        System.out.println("How much? ("+customers.get(temp-1).getMoney()+")");
        cash=in.nextInt();
        System.out.println(customers.get(temp-1).getMoney()+"--->"+customers.get(temp1-1).getMoney());
        if(customers.get(temp-1).getMoney()>=cash) {
            tempcash = (int)customers.get(temp - 1).getMoney() - cash;
            customers.get(temp-1).setMoney(tempcash);
            customers.get(temp1-1).setMoney(customers.get(temp1-1).getMoney()+cash);
            System.out.println("*Success*");
            System.out.println(customers.get(temp-1).getMoney()+"--->"+customers.get(temp1-1).getMoney());
        }else System.out.println("Not enought money");
    }
    public void addmoney(){
        Scanner in = new Scanner(System.in);//for input
        int j = 1;
        int temp;
        int tempcash;
        for(int i =0; i<customers.size(); i++){
            System.out.println(j+". "+customers.get(i).getF_Name()+" "+ customers.get(i).getL_Name()
                    + " | " + customers.get(i).getMoney());
            j++;
        }
        System.out.println("Which customer? type index:");
        temp=in.nextInt();
        System.out.println("How much? ("+customers.get(temp-1).getMoney()+")");
        tempcash=in.nextInt();
        System.out.println(tempcash+"--->"+customers.get(temp-1).getMoney());
        System.out.println("*Success*");
        customers.get(temp-1).setMoney(customers.get(temp-1).getMoney()+tempcash);
    }

    public void takemoney(){
        Scanner in = new Scanner(System.in);//for input
        int j = 1;
        int temp;
        int tempcash;
        for(int i =0; i<customers.size(); i++){
            System.out.println(j+". "+customers.get(i).getF_Name()+" "+ customers.get(i).getL_Name()
                    + " | " + customers.get(i).getMoney());
            j++;
        }
        System.out.println("Which customer? type index:");
        temp=in.nextInt();
        System.out.println("How much? ("+customers.get(temp-1).getMoney()+")");
        tempcash=in.nextInt();
        if(customers.get(temp-1).getMoney()>=tempcash) {
            System.out.println("*Success*");
            customers.get(temp-1).setMoney(customers.get(temp-1).getMoney()-tempcash);
            System.out.println(customers.get(temp-1).getMoney());
        }else System.out.println("Not enought money");
    }

    public void deletecustomer(){
        Scanner in = new Scanner(System.in);//for input
        int j = 1;
        int temp;
        for(int i =0; i<customers.size(); i++){
            System.out.println(j+". "+customers.get(i).getF_Name()+" "+ customers.get(i).getL_Name());
            j++;
        }
        System.out.println("Which customer? type index:");
        temp=in.nextInt();
        customers.remove(temp-1);
        System.out.println("*Success*");
        j=1;
        for(int i =0; i<customers.size(); i++){
            System.out.println(j+". "+customers.get(i).getF_Name()+" "+ customers.get(i).getL_Name());
            j++;
        }
    }
    public void deleteworker(){
        Scanner in = new Scanner(System.in);//for input
        int j = 1;
        int temp;
        for(int i =0; i<workers.size(); i++){
            System.out.println(j+". "+workers.get(i).getF_Name()+" "+ workers.get(i).getL_Name());
            j++;
        }
        System.out.println("Which worker? type index:");
        temp=in.nextInt();
        workers.remove(temp-1);
        System.out.println("*Success*");
        j=1;
        for(int i =0; i<workers.size(); i++){
            System.out.println(j+". "+workers.get(i).getF_Name()+" "+ workers.get(i).getL_Name());
            j++;
        }
    }
    public void changeCurrency(){
        System.out.println("Current currency: ");
        Scanner in = new Scanner(System.in);//for input
        if(currencyID == 0){
            System.out.println("KZT");
            System.out.println("CHANGE TO USD(1), EUR(2)");
        }else if (currencyID == 1){
            System.out.println("USD");
            System.out.println("CHANGE TO KZT(0), EUR(2)");
        }else if (currencyID == 2){
            System.out.println("EUR");
            System.out.println("CHANGE TO KZT(0), USD(1)");
        }
        int menu = in.nextInt();
        if(menu == 0 && currencyID != 0){
            for(int i =0; i< customers.size();i++){
                if(currencyID == 1) {//USD > KZT
                    customers.get(i).setMoney(customers.get(i).getMoney() * 420.33);
                }
                if(currencyID == 2){//EUR > KZT
                    customers.get(i).setMoney(customers.get(i).getMoney() * 499.41);
                }
            }
            currencyID = 0;
        }
        if(menu == 1 && currencyID != 1){
            for(int i =0; i< customers.size();i++){
                if(currencyID == 0) {//KZT > USD
                    customers.get(i).setMoney(customers.get(i).getMoney() * 0.0024);
                }
                if(currencyID == 2){//EUR > USD
                    customers.get(i).setMoney(customers.get(i).getMoney() * 1.19);
                }
            }
            currencyID = 1;
        }if(menu == 2 && currencyID != 2){
            for(int i =0; i< customers.size();i++){
                if(currencyID == 0) {//KZT > EUR
                    customers.get(i).setMoney(customers.get(i).getMoney() * 0.0020);
                }
                if(currencyID == 1){//USD > EUR
                    customers.get(i).setMoney(customers.get(i).getMoney() * 0.84);
                }
            }
            currencyID = 2;
        }
    }
    public int getCurrencyID(){
        return currencyID;
    }

    public String toString(){
        return "List of Customers:\n"+customers.toString()+"\n\nList of Workers: \n"+ workers.toString();
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }


}

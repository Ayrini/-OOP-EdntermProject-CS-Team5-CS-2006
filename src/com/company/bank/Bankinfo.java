package com.company.bank;

import java.util.Scanner;

public class Bankinfo {
    //String name;
    int currencyid;
    String currency;
    Scanner in = new Scanner(System.in);//for input
    int menu1;

    //Show short information about bank(Bank Name/Money in bank and currency/Number of users)
    public void info(Banksystem banksystem,int wnumber,int cnumber){
        System.out.println("Bank name: " + banksystem.getBankname());
        currencyid=banksystem.getCurrencyID();
        if(currencyid == 0){
            currency = "KZT";
        }if(currencyid == 1){
            currency = "USD";
        }if(currencyid == 2){
            currency = "EUR";
        }
        System.out.println("Money in Bank: " + bankmoney(banksystem) +" "+ currency);
        System.out.println("Number of Workers: " + numberofusers(banksystem,wnumber, false));
        System.out.println("Number of Customers: " + numberofusers(banksystem,cnumber, true));
        System.out.println( "*************************" +
                "\nDo you want to change bank name?(1 YES/ 0 NO)");
        menu1 = in.nextInt();
        if (menu1 == 1) {
            System.out.println("Write the new name: ");
            banksystem.setBankname(in.next());
        }
        if (menu1 == 0) {
            System.out.println("OK");
        }
    }
    public int bankmoney(Banksystem banksystem){
        int temp=0;

        for(int i = 0; i < banksystem.customers.size(); i++){
            temp +=  banksystem.customers.get(i).getMoney();
        }
        return temp;
    }

    public int numberofusers(Banksystem banksystem,int count, boolean ind){//1 CUSTOMER \\0 WORKER
        if(ind == true){
            return count= banksystem.customers.size();

        }
        else if(ind == false) {
            return count =  banksystem.workers.size();
        }
        else {
            System.out.println("smth wrong");
            return -1;
        }
    }
}

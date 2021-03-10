package com.company.bank;

import java.util.Scanner;

public class Bankinfo {
    String name;
    int currencyid;
    String currency;
    Scanner in = new Scanner(System.in);//for input
    int menu1;
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
        System.out.println("Money in Bank: " + banksystem.bankmoney() +" "+ currency);
        System.out.println("Number of Workers: " + banksystem.numberofusers(wnumber, false));
        System.out.println("Number of Customers: " + banksystem.numberofusers(cnumber, true));
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
}

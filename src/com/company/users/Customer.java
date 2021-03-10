package com.company.users;

public class Customer extends User{

    private double money;

    public Customer(String fname, String lname, int age, int gender,double money){
        super(fname, lname, age, gender);
        this.money = money;
    }


    public String toString(){
        String str = "\nFull name:"+super.getF_Name()+" "+super.getL_Name()+", Age: "+super.getAge()+", ";
        if(super.getGender() == 1){
            str += "Male";
        }else if(super.getGender() == 0){
            str += "Female";
        }
        str += ", CASH: " + money;
        return str;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}

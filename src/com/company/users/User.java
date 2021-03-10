package com.company.users;

public class User {
    private String f_Name;
    private String l_Name;
    private int age;
    private int gender;//1 male, 0 female

    public User(String fname, String lname, int age, int gender)
    {
        f_Name=fname;
        l_Name=lname;
        this.age =age;
        this.gender = gender;
    }

    public String getF_Name() {
        return f_Name;
    }

    public void setF_Name(String f_Name) {
        this.f_Name = f_Name;
    }

    public String getL_Name() {
        return l_Name;
    }

    public void setL_Name(String l_Name) {
        this.l_Name = l_Name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}

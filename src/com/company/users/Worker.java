package com.company.users;

public class Worker extends User{
    private String position;
    private int salary;

    public Worker(String fname, String lname, String position,int salary, int age, int gender){
        super(fname, lname, age, gender);
        this.position=position;
        this.salary = salary;
    }

    public String toString(){
        String str = "\nFull name:"+super.getF_Name()+" "+super.getL_Name()
                +", Position: "+position+", Salary: "+ salary+", Age: "+super.getAge()+", ";
        if(super.getGender() == 1){
            str += "Male";
        }else if(super.getGender() == 0){
            str += "Female";
        }
        return str;
        //return "\nFull name:"+f_Name+" "+l_Name+", Age: "+age+", gender(1 male,0 female) "+gender+if(gender == 1)"male";
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}

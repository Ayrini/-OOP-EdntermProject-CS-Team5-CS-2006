package com.company;

import com.company.db.DataBase;
import com.company.exception.UserExistsException;
import com.company.run.systemRUN;

public class Main {

    public static void main(String[] args) throws UserExistsException {
	// write your code here

        systemRUN system = new systemRUN();
        system.run();
    }
}

# -OOP-EdntermProject-CS-Team5-CS-2006
Mambediyev Assan
Bektrumov Tamerlan
We have 5 folders where classes and interfaces are stored. Number of classes 10, interfaces 2
=======================
bank(package)
[
BankInfo(class)//Bank info contains Bank info contains information about the bank, such as the name of the bank, the amount of money in the bank, the number of employees and clients, as well as the currency of the bank

BankSystem(class)//in the Bank system, basic methods go through, such as downloading data from the postgresql server, adding / removing users, clearing data in postgresql, transactions between clients, changing the bank currency, viewing data in the postgresql server 

INTsystem(interface)
]
-------------------------
db(package)
[
DataBase(class)//if we dont have tables in postgresql, by using Database class we can easily create tables. Or delete tables

DBsystem(class)//in DBsystem we have methods such like refresh, which refreshing information in postgresql server, or take data from postgresql

INTdatabse(interface)
]
=======================
exception(package)
[
UserExistsException(class)//here we have an exception for an error when adding a user who is already in the system. This exception is used in the BankSystem class 
]
-------------------------
run(package)
[
systemRun(class)//here is the menu on which we navigate 
]
-------------------------
users(package)
[
Customer(class)//Customer class, which contains information like First name, Last name, Age, gender, and money in Bank

Worker(class)//Worker class, Which contains information like First name, Last name, Position, age, gender, and Salary

User(super-class)//super class
]
-------------------------
main(class)

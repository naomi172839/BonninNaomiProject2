/*
 * Copyright (c) 2019.
 * Author: Naomi Bonnin
 * Course: CMIS 242 6383
 * Project Name: Project 2 (Employee ATM
 * Filename: Account.java
 * Updated: 9/14/19, 7:25 PM
 * Description: This program creates a rudimentary ATM machine.
 */

package edu.bonnin;

//The account class contains the actual logic for the transactions.  It is quite rudimentary and could easily be expanded
class Account {


    private double balance;
    private final static double SERVICE_CHARGE = 1.50; //Default service charge
    private static int withdraws = 0; //Starts at one because the check is performed before a withdraw has been performed

    //Constructor to create the account.  Requires a starting balance
    Account(double balance) {
        this.balance = balance;
    }

    //Gets the balance of the account
    private double getBalance() {
        return balance;
    }

    //Sets the balance of the account
    private void setBalance(double balance) {
        this.balance = balance;
    }

    //Gets the total number of withdraws
    private int getWithdraws() {
        return withdraws;
    }

    //Sets the number of withdraws
    private void setWithdraws(int withdraws) {
        Account.withdraws = withdraws;
    }

    //Sets the account balance to the deposit amount
    void deposit(double depositAmount) {
        this.setBalance(this.getBalance() + depositAmount);
    }

    //Withdraws money from the account
    void withdraw(double withdrawAmount) throws InsufficientFunds {
        //Checks if there is enough money in the account to handle the withdraw if the service charge is applied
        if(Account.withdraws > 4 && this.getBalance() + SERVICE_CHARGE < withdrawAmount) {
            throw new InsufficientFunds(this);
            //Checks if there is enough money to handle the withdraw
        }else if(this.getBalance() < withdrawAmount) {
            throw new InsufficientFunds(this);
            //Sets the balance to be minus the withdraw amount
        }else {
            this.setBalance(this.getBalance() - withdrawAmount);

            //Applies the service charge if necessary
            if(Account.withdraws > 4) {
                this.setBalance(this.getBalance() - SERVICE_CHARGE);
            }
            this.setWithdraws(this.getWithdraws() + 1); //Adds to the withdraw totals.
        }
    }

    //Simply returns the balance
    double currentBalance() {
        return this.getBalance();
    }

    //Logic for the transfer from one account to another
    void transfer(Account receiving, double transferAmount) throws InsufficientFunds {
        if (this.getBalance() < transferAmount) { //Checks if there is enough money
            throw new InsufficientFunds(this);
            //Transfers the money and does not apply a fee.
        } else {
            receiving.deposit(transferAmount);
            this.setBalance(this.getBalance()-transferAmount);
        }
    }
}

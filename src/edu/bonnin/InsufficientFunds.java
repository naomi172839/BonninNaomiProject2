/*
 * Copyright (c) 2019.
 * Author: Naomi Bonnin
 * Course: CMIS 242 6383
 * Project Name: Project 2 (Employee ATM
 * Filename: InsufficientFunds.java
 * Updated: 9/14/19, 6:58 PM
 * Description: This program creates a rudimentary ATM machine.
 */

package edu.bonnin;

//Custom exception.  Returns the current balance
class InsufficientFunds extends Exception {

    private double balance;

    //Constructor that gets the account balance
    InsufficientFunds(Account a) {
        balance = a.currentBalance();
    }

    //Overridden getMessage to include a custom message with the balance.
    @Override
    public String getMessage() {
        return "Insufficient Funds! Current Balance: " + balance;
    }
}

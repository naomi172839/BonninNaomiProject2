/*
 * Copyright (c) 2019.
 * Author: Naomi Bonnin
 * Course: CMIS 242 6383
 * Project Name: Project 2 (Employee ATM
 * Filename: InsufficientFundsTest.java
 * Updated: 9/14/19, 7:43 PM
 * Description: This program creates a rudimentary ATM machine.
 */

package edu.bonnin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InsufficientFundsTest {

    @Test
    void getMessage() {
        Account one = new Account(500);
        try {
            one.withdraw(1000);
        } catch (InsufficientFunds e) {
            assertEquals(("Insufficient Funds! Current Balance: 500.0"), e.getMessage());
        }
    }
}
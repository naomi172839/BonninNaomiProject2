/*
 * Copyright (c) 2019.
 * Author: Naomi Bonnin
 * Course: CMIS 242 6383
 * Project Name: Project 2 (Employee ATM
 * Filename: AccountTest.java
 * Updated: 9/14/19, 7:25 PM
 * Description: This program creates a rudimentary ATM machine.
 */

package edu.bonnin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountTest {

    private Account one = new Account(500);
    private Account two = new Account(1000);

    @Test
    void deposit() {
        one.deposit(20);
        assertEquals(520, one.currentBalance());
    }

    @Test
    void withdraw() {
        try {
            two.withdraw(20);
        } catch (InsufficientFunds ignored) {
        }
        assertEquals(980, two.currentBalance());
    }

    @Test
    void currentBalance() {
        assertEquals(500, one.currentBalance());
    }

    @Test
    void transfer() {
        try {
            one.transfer(two, 250);
        } catch (InsufficientFunds ignored) {
        }
        assertEquals(250, one.currentBalance());
        assertEquals(1250, two.currentBalance());
    }

    @Test
    void multipleWithdraw() {
        try {
            one.withdraw(10);
            one.withdraw(10);
            one.withdraw(10);
            one.withdraw(10);
            one.withdraw(10);
        } catch (InsufficientFunds ignored) {
        }
        assertEquals(448.5, one.currentBalance());
    }

    @Test
    void insufficientFundsThrown() {
        assertThrows(InsufficientFunds.class, () -> one.withdraw(10000));
    }
}
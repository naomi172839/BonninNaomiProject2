package edu.bonnin;

public class Account {

    private double balance;
    private static int withdraws = 1;
    private final static double SERVICE_CHARGE = 1.50;

    Account(double balance) {
        this.balance = balance;
    }

    double getBalance() {
        return balance;
    }

    private void setBalance(double balance) {
        this.balance = balance;
    }

    private int getWithdraws() {
        return withdraws;
    }

    private void setWithdraws(int withdraws) {
        Account.withdraws = withdraws;
    }

    void deposit(double depositAmount) {
        this.setBalance(this.getBalance() + depositAmount);
    }

    void withdraw(double withdrawAmount) throws InsufficientFunds {
        if(Account.withdraws > 4 && this.getBalance() + SERVICE_CHARGE < withdrawAmount) {
            throw new InsufficientFunds(this);
        }else if(this.getBalance() < withdrawAmount) {
            throw new InsufficientFunds(this);
        }else {
            this.setBalance(this.getBalance() - withdrawAmount);

            if(Account.withdraws > 4) {
                this.setBalance(this.getBalance() - SERVICE_CHARGE);
            }
            this.setWithdraws(this.getWithdraws()+1);
        }
    }

    public double currentBalance() {
        return this.getBalance();
    }

    void transfer(Account receiving, double transferAmount) throws InsufficientFunds {
        if(this.getBalance() < transferAmount) {
            throw new InsufficientFunds(this);
        } else {
            receiving.deposit(transferAmount);
            this.setBalance(this.getBalance()-transferAmount);
        }
    }
}

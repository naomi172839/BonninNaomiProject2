package edu.bonnin;

class InsufficientFunds extends Exception {

    private double balance;

    InsufficientFunds(Account a) {
        balance = a.getBalance();
    }

    @Override
    public String getMessage() {
        return "Insufficient Funds! Current Balance: " + balance;
    }
}

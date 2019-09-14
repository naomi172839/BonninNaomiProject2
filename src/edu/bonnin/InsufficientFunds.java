package edu.bonnin;

class InsufficientFunds extends Exception {

    double balance;

    public InsufficientFunds(Account a) {
        balance = a.getBalance();
    }

    @Override
    public String getMessage() {
        return "Insufficient Funds! Current Balance: " + balance;
    }
}

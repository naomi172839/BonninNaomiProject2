package edu.bonnin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {

    	new GUI("ATM Machine");
    }

    public static class GUI extends JFrame implements ActionListener {

    	JFrame frame = new JFrame("Temp");

		JButton withdraw = new JButton("Withdraw");
		JButton deposit = new JButton("Deposit");
		JButton transferTo = new JButton("Transfer To");
		JButton balance = new JButton("Balance");
		JRadioButton checking = new JRadioButton("Checking");
		JRadioButton savings = new JRadioButton("Savings");
		JTextField inputBox = new JTextField(5);

		Account checkingAccount = new Account(10000);
		Account savingsAccount = new Account(0);

    	GUI(String title) {
    		frame.setTitle(title);
    		frame.setSize(400, 400);
    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		frame.setLocationRelativeTo(null);
    		frame.setLayout(new GridBagLayout());
    		GridBagConstraints c = new GridBagConstraints();
    		c.insets = new Insets(10,10,10,10);
    		c.weightx = 0.5;
    		c.gridx = 0;
    		c.gridy = 0;
    		c.fill = GridBagConstraints.HORIZONTAL;
    		frame.add(withdraw,c);
    		withdraw.addActionListener(this);
    		c.weightx = 0.5;
    		c.gridx = 1;
    		c.gridy = 0;
    		c.fill = GridBagConstraints.HORIZONTAL;
    		frame.add(deposit, c);
    		deposit.addActionListener(this);
    		c.weightx = 0.5;
    		c.gridx = 0;
    		c.gridy = 1;
    		c.fill = GridBagConstraints.HORIZONTAL;
    		frame.add(transferTo, c);
    		transferTo.addActionListener(this);
    		c.weightx = 0.5;
    		c.gridx = 1;
    		c.gridy = 1;
    		c.fill = GridBagConstraints.HORIZONTAL;
    		frame.add(balance, c);
    		balance.addActionListener(this);
    		c.weightx = 0.5;
    		c.gridx = 0;
    		c.gridy = 2;
    		c.fill = GridBagConstraints.HORIZONTAL;
    		frame.add(checking, c);
    		checking.setSelected(true);
    		c.weightx = 0.5;
    		c.gridx = 1;
    		c.gridy = 2;
    		c.fill = GridBagConstraints.HORIZONTAL;
    		frame.add(savings, c);
    		c.weightx = 0;
    		c.gridx = 0;
    		c.gridy = 3;
    		c.gridwidth = 2;
    		c.fill = GridBagConstraints.HORIZONTAL;
    		frame.add(inputBox,c);
    		frame.pack();
    		frame.setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {

    		if(e.getActionCommand().equals("Withdraw")) {
				int input = - 1;
				try {
					input = Integer.parseInt(inputBox.getText().trim());
				} catch (NumberFormatException error) {
					JOptionPane.showMessageDialog(frame, "Input must be numeric!");
				}
				if (input % 20 != 0 && input > 0) {
					JOptionPane.showMessageDialog(frame, "Input must be in multiples of 20!");
				} else if (input > 0){
					try {
						if (checking.isSelected()) {
							checkingAccount.withdraw(input);
						}
						if (savings.isSelected()) {
							savingsAccount.withdraw(input);
						}
						JOptionPane.showMessageDialog(frame, "Withdraw Successful");
					} catch (InsufficientFunds error) {
						JOptionPane.showMessageDialog(frame, error.getMessage());
					}

				}
			}

    		if(e.getActionCommand().equals("Deposit")) {
    			double input = 0.0;
    			try {
    				input = Double.parseDouble(inputBox.getText().trim());
				} catch (NumberFormatException error) {
    				JOptionPane.showMessageDialog(frame, "Input must be numeric!");
				}
    			if(checking.isSelected()) {
    				checkingAccount.deposit(input);
				}
    			if(savings.isSelected()) {
    				savingsAccount.deposit(input);
				}
			}

    		if(e.getActionCommand().equals("Transfer To")) {
				double input = 0.0;
				try {
					input = Double.parseDouble(inputBox.getText().trim());
				} catch (NumberFormatException error) {
					JOptionPane.showMessageDialog(frame, "Input must be numeric!");
				}
				try {
					if (checking.isSelected()) {
						checkingAccount.transfer(savingsAccount,input);
					}
					if (savings.isSelected()) {
						savingsAccount.transfer(checkingAccount, input);
					}

				} catch (InsufficientFunds error) {
					JOptionPane.showMessageDialog(frame, error.getMessage());
				}
			}

    		if(e.getActionCommand().equals("Balance")) {
				if (checking.isSelected()) {
					JOptionPane.showMessageDialog(frame, String.valueOf(checkingAccount.currentBalance()));
				}
				if (savings.isSelected()) {
					JOptionPane.showMessageDialog(frame, String.valueOf(savingsAccount.currentBalance()));
				}
			}

		}


	}
}

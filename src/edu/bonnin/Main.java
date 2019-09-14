/*
 * Copyright (c) 2019.
 * Author: Naomi Bonnin
 * Course: CMIS 242 6383
 * Project Name: Project 2 (Employee ATM
 * Filename: Main.java
 * Updated: 9/14/19, 6:58 PM
 * Description: This program creates a rudimentary ATM machine.
 */

package edu.bonnin;

//Imports the necessary dependencies
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

	//The main method exists only to create the GUI
	public static void main(String[] args) {

		new GUI("ATM Machine");
	}

	/*
    The bread and butter of the program.  The GUI class creates the ATM GUI as well as the action handlers.
     */
	public static class GUI extends JFrame implements ActionListener {

		//The default frame to be used
		JFrame frame = new JFrame("Temp");

		//The various swing features that are accessed from the action listener.
		JButton withdraw = new JButton("Withdraw");
		JButton deposit = new JButton("Deposit");
		JButton transferTo = new JButton("Transfer To");
		JButton balance = new JButton("Balance");
		JRadioButton checking = new JRadioButton("Checking");
		JRadioButton savings = new JRadioButton("Savings");
		JTextField inputBox = new JTextField(5);

		//Creates the two accounts necessary for the program to function.
		Account checkingAccount = new Account(10000);
		Account savingsAccount = new Account(0);

		//Constructor that allows you to set the title in the main method.
		GUI(String title) {
			frame.setTitle(title);
			frame.setSize(600, 600);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Tells java that the program should exit when closed
			frame.setLocationRelativeTo(null);  //Places the window in the center of the screen
			frame.setLayout(new GridBagLayout());  //The most flexible layout
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(10, 10, 10, 10);  //Creates padding around all the features
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
			frame.pack();  //Verifies that everything is placed in a valid position
			frame.setVisible(true); //Shows the window
		}

		//Action listener to check when the buttons are pressed.
		@Override
		public void actionPerformed(ActionEvent e) {

			//Checks if the withdraw button is pressed
			if(e.getActionCommand().equals("Withdraw")) {
				int input = -1;  //Negative input to make sure that a withdraw is not performed if the number is not numeric
				//Checks to see if number is numeric
				try {
					input = Integer.parseInt(inputBox.getText().trim());
				} catch (NumberFormatException error) {
					JOptionPane.showMessageDialog(frame, "Input must be numeric!");
				}
				//Checks to see if imput is a multiple of 20
				if (input % 20 != 0 && input > 0) {
					JOptionPane.showMessageDialog(frame, "Input must be in multiples of 20!");
				} else if (input > 0) {  //If all the above pass, then performs the withdraw action
					try {
						if (checking.isSelected()) {
							checkingAccount.withdraw(input);
						}
						if (savings.isSelected()) {
							savingsAccount.withdraw(input);
						}
						JOptionPane.showMessageDialog(frame, "Withdraw Successful");
					} catch (InsufficientFunds error) {  //If there is not enough money it calls the insufficient funds exception
						JOptionPane.showMessageDialog(frame, error.getMessage());
					}

				}
			}


			//Checks if the deposit button is pressed
			if(e.getActionCommand().equals("Deposit")) {
				double input = 0.0; //Default input in case no value is entered
				//Checkes if the number is numeric
				try {
					input = Double.parseDouble(inputBox.getText().trim());
				} catch (NumberFormatException error) {
					JOptionPane.showMessageDialog(frame, "Input must be numeric!");
				}
				//Performs the actual deposit operation
				if(checking.isSelected()) {
					checkingAccount.deposit(input);
				}
				if(savings.isSelected()) {
					savingsAccount.deposit(input);
				}
			}

			//Checks if the transfer button is pressed
			if(e.getActionCommand().equals("Transfer To")) {
				double input = -1.0;  //To check if the number was numeric or not
				//Checks if the number is numeric
				try {
					input = Double.parseDouble(inputBox.getText().trim());
				} catch (NumberFormatException error) {
					JOptionPane.showMessageDialog(frame, "Input must be numeric!");
				}
				//If the number was valid attempts a transfer
				if (input > 0) try {
					if (checking.isSelected()) {
						checkingAccount.transfer(savingsAccount,input);
					}
					if (savings.isSelected()) {
						savingsAccount.transfer(checkingAccount, input);
					}
					JOptionPane.showMessageDialog(frame, "Transfer Successful!");

				} catch (InsufficientFunds error) {  //If theres is not enough money, it tells the user
					JOptionPane.showMessageDialog(frame, error.getMessage());
				}
			}

			//Checks if the Balance Button is pressed
			if(e.getActionCommand().equals("Balance")) {
				if (checking.isSelected()) {  //Shows the current balance of checking
					JOptionPane.showMessageDialog(frame, String.valueOf(checkingAccount.currentBalance()));
				}
				if (savings.isSelected()) {  //Shows the current balance of savings
					JOptionPane.showMessageDialog(frame, String.valueOf(savingsAccount.currentBalance()));
				}
			}

		}


	}
}

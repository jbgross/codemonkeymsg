package edu.psu.ist.login;
import javax.swing.JOptionPane;

/**
 * This is a separate class from the account class because
 * it will be the main interaction class for the IM client.
 * 
 * @author Code Monkeys, Inc.
 *
 */

public class User {
	String name;
	String id;
	
	public User (String[] info) {
		this.name = info[0];
		this.id = info[1];
		JOptionPane.showMessageDialog(null, "Welcome, " + this.name + "!");
	}
}

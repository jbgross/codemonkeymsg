package edu.psu.ist.login;


import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JPanel.*;
import java.lang.*;
import java.util.*;




/**
 * This is the main runner class for the login process. A
 * user will log in, the Account function verifyUser will
 * verify the login, and a User Object will be created for
 * further use in the IM client.
 * @author Code Monkeys, Inc.
 *
 */



public class Login extends JFrame {

	public static java.util.ArrayList userList = new java.util.ArrayList();
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	
	protected Container contents;
	public JPanel graphicPanel, contentPanel;
	public JLabel userName, password;
	public TextArea userNameField;
	protected JPasswordField passWordField;
	public JButton login;
	public Image monkeys;
	
	public Login(){

		super("Welcome to MonkeyMSG!");
		
		login = new JButton("Log On");
		
		userName = new JLabel("User Name:");
		password = new JLabel("Password:");
		
		userNameField = new TextArea();
		passWordField = new JPasswordField();
		
		ImageIcon monkeys = new ImageIcon("monkeys.jpg");
		//File image = new File("monkeys.jpg");
		//monkeys = ImageIO.read(image);
		
		graphicPanel = new JPanel();
		contentPanel = new JPanel();
		
		contents = new Container();
		
		contents.setLayout(new FlowLayout());
		
		contentPanel.add(login);
		contentPanel.add(userName);
		contentPanel.add(userNameField);
		contentPanel.add(password);
		contentPanel.add(passWordField);
		
		graphicPanel.add(new JLabel(monkeys));
		
		contentPanel.setLayout(null);
		
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		int userNumber;
		generateAccountCollection();
		userNumber = verifyUser();
		if (userNumber > 0) {
			loginUser(userNumber);
		}
	}
	
	public static void generateAccountCollection () throws FileNotFoundException {
		java.io.File file = new java.io.File("Accounts.txt");
		java.util.Scanner input = new java.util.Scanner(file);
		while(input.hasNext()) {
			int idNumber = input.nextInt();
			String userName = input.next();
			String password = input.next();
			Account.addAccount(new Account(idNumber, userName, password));
		}
	}
	public static int verifyUser () {
		boolean loggedIn = false;
		int userId = 0;
		for (int i = 0; i < 5; i++) {
			String userName = 
				JOptionPane.showInputDialog(null,
						"Enter User Name",
						"Login",
						JOptionPane.QUESTION_MESSAGE);
			String password = 
				JOptionPane.showInputDialog(null,
						"Enter Password",
						"Password",
						JOptionPane.QUESTION_MESSAGE);
			userId = Account.verifyLogIn(userName, password);
			if (userId > 0) {
				JOptionPane.showMessageDialog(null, "Correct Login!");
				loggedIn = true;
				break;
			}
			else {
				JOptionPane.showMessageDialog(null, "Incorrect Login!");
			}
		}
		if (loggedIn == false) {
			JOptionPane.showMessageDialog(null, "Failed to Log In!");
			return -1;
		}
		else {
			return userId;
		}
	}
	
	public static void loginUser (int id) {
		userList.add(new User(Account.getInfo(id)));
	}
}

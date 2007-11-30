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



public class Login extends JFrame implements ActionListener {

	public static java.util.ArrayList userList = new java.util.ArrayList();
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	
	private Container contents;
	public JPanel graphicPanel, contentPanel;
	public JLabel userName, password;
	public JTextField userNameField;
	protected JPasswordField passWordField;
	public JButton loginButton, newAccountButton;
	public Image monkeys;
	
	public Login(){

		super("MonkeyMessenger v1.0");
		contents = getContentPane();
		contents.setBounds(300,600, 100,900);
		contents.setVisible(true);
		
		loginButton = new JButton("Log On");
		newAccountButton = new JButton("Create Acccount");
		
		userName = new JLabel("User Name:");
		password = new JLabel("Password:");
		
		userNameField = new JTextField(30);
		passWordField = new JPasswordField(30);
		
		ImageIcon monkeys = new ImageIcon("monkeys.jpg");
		//File image = new File("monkeys.jpg");
		//monkeys = ImageIO.read(image);
		
		graphicPanel = new JPanel();
		contentPanel = new JPanel();
		
		contents.setLayout(new FlowLayout());
		
		contentPanel.add(loginButton);
		contentPanel.add(newAccountButton);
		contentPanel.add(userName);
		contentPanel.add(userNameField);
		contentPanel.add(password);
		contentPanel.add(passWordField);
		
		userNameField.addActionListener(this);
		passWordField.addActionListener(this);
		
		loginButton.addActionListener(this);
		newAccountButton.addActionListener(this);
		
		graphicPanel.add(new JLabel(monkeys));
		
		contentPanel.setLayout(null);
		userName.setLocation(20, 10);
		userNameField.setLocation(50,10);
		password.setLocation(20, 25);
		passWordField.setLocation(50,25);
		
		
	}
	
	public void ActionPerformed(ActionEvent evt){
		if (evt.getSource() == loginButton){
			
		}
		if (evt.getSource() == newAccountButton){
			createLogin();
		}
	}
	
	public static void main(String[] args) throws IOException {

//		createLogin();
//		int userNumber;
//		generateAccountCollection();
//		userNumber = verifyUser();
//		if (userNumber >= 0) {
//			loginUser(userNumber);
//		}
//		try {
//			new Account(1, "Login", "Password");
//			new Account(2, "jgross311", "j0sh0cr4cy");
//			new Account(3, "guitargeek230", "******"); 
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Login gui1 = new Login();
	}
	
	public static void generateAccountCollection () throws IOException {
		DataInputStream accountsInput = 
			new DataInputStream(new FileInputStream("Accounts.dat"));
		DataInputStream countInput =
			new DataInputStream(new FileInputStream("Count.dat"));
		int count = countInput.readInt();
		int idNumber;
		String userName;
		String password;
		for (int i = 0; i < count; i++) {
			idNumber = accountsInput.readInt();
			userName = accountsInput.readUTF();
			password = accountsInput.readUTF();
			Account.addAccount(idNumber, userName, password);
		}
	}
	public static int verifyUser () {
		boolean loggedIn = false;
		int userId = -1;
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
			if (userId >= 0) {
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
	
	/**
	 * This function creates a login account for a user.
	 * The user has five attempts to achieve an original combination
	 * before the program terminates.
	 * @throws IOException
	 */
	 
	public static void createLogin () throws IOException {
		boolean loggedIn = true;
		int userId = -1;
		String userName;
		String password;
		for (int i = 0; i < 5; i++) {
			userName = 
				JOptionPane.showInputDialog(null,
						"Enter User Name",
						"Login",
						JOptionPane.QUESTION_MESSAGE);
			password = 
				JOptionPane.showInputDialog(null,
						"Enter Password",
						"Password",
						JOptionPane.QUESTION_MESSAGE);
			userId = Account.verifyLogIn(userName, password);
			if (userId >= 0) {
				JOptionPane.showMessageDialog(null, "That Combination is Already Used!");
			}
			else {
				JOptionPane.showMessageDialog(null, "New Combination!");
				new Account(userName, password);
				loggedIn = false;
				break;
			}
		}
		if (loggedIn == true) {
			JOptionPane.showMessageDialog(null, "Failed to Create New Account!");
		}
	}
}



/**
 * /* Team Code Monkeys, Inc.
 * Kevin Ahrens, Severn Everett, Nick Weidman
 * IST 311
 * 11/15/07
 

package edu.psu.ist.login;
import java.io.*;

import javax.swing.JOptionPane;

/**
 * This is the main runner class for the login process. A
 * user will log in, the Account function verifyUser will
 * verify the login, and a User Object will be created for
 * further use in the IM client.
 * @author Code Monkeys, Inc.
 *
 

public class Login {

	public static java.util.ArrayList userList = new java.util.ArrayList();
	/**
	 * @param args
	 * @throws IOException 
	 
	public static void main(String[] args) throws IOException {

//		createLogin();
//		int userNumber;
//		generateAccountCollection();
//		userNumber = verifyUser();
//		if (userNumber >= 0) {
//			loginUser(userNumber);
//		}
//		try {
//			new Account(1, "Login", "Password");
//			new Account(2, "jgross311", "j0sh0cr4cy");
//			new Account(3, "guitargeek230", "******"); 
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public static void generateAccountCollection () throws IOException {
		DataInputStream accountsInput = 
			new DataInputStream(new FileInputStream("Accounts.dat"));
		DataInputStream countInput =
			new DataInputStream(new FileInputStream("Count.dat"));
		int count = countInput.readInt();
		int idNumber;
		String userName;
		String password;
		for (int i = 0; i < count; i++) {
			idNumber = accountsInput.readInt();
			userName = accountsInput.readUTF();
			password = accountsInput.readUTF();
			Account.addAccount(idNumber, userName, password);
		}
	}
	public static int verifyUser () {
		boolean loggedIn = false;
		int userId = -1;
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
			if (userId >= 0) {
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
	
	/**
	 * This function creates a login account for a user.
	 * The user has five attempts to achieve an original combination
	 * before the program terminates.
	 * @throws IOException
	 
	public static void createLogin () throws IOException {
		boolean loggedIn = true;
		int userId = -1;
		String userName;
		String password;
		for (int i = 0; i < 5; i++) {
			userName = 
				JOptionPane.showInputDialog(null,
						"Enter User Name",
						"Login",
						JOptionPane.QUESTION_MESSAGE);
			password = 
				JOptionPane.showInputDialog(null,
						"Enter Password",
						"Password",
						JOptionPane.QUESTION_MESSAGE);
			userId = Account.verifyLogIn(userName, password);
			if (userId >= 0) {
				JOptionPane.showMessageDialog(null, "That Combination is Already Used!");
			}
			else {
				JOptionPane.showMessageDialog(null, "New Combination!");
				new Account(userName, password);
				loggedIn = false;
				break;
			}
		}
		if (loggedIn == true) {
			JOptionPane.showMessageDialog(null, "Failed to Create New Account!");
		}
	}
}
*/

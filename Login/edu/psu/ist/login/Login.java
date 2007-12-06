package edu.psu.ist.login;


import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;





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
	
	private Container contentPane;
	public static JPanel graphicPanel, userNamePanel, passWordPanel, buttonPanel, imPanel;
	public JLabel userName, password;
	public JTextField userNameField;
	protected JPasswordField passWordField;
	public JButton loginButton, newAccountButton, logoutButton;
	public Image monkeys;
	
	public Login(){

		super("MonkeyMessenger v1.0");

		contentPane = getContentPane();
		
		loginButton = new JButton("Log On");
		newAccountButton = new JButton("Create Acccount");
		
		userName = new JLabel("User Name:");
		password = new JLabel("Password:");
		
		userNameField = new JTextField(15);
		passWordField = new JPasswordField(15);
		
		ImageIcon monkeys = new ImageIcon("monkeys.jpg");
		//File image = new File("monkeys.jpg");
		//monkeys = ImageIO.read(image);
		
		graphicPanel = new JPanel();
		userNamePanel = new JPanel();
		passWordPanel = new JPanel();
		buttonPanel = new JPanel();
		
		userNamePanel.add(userName);
		userNamePanel.add(userNameField);
		passWordPanel.add(password);
		passWordPanel.add(passWordField);
		buttonPanel.add(loginButton);
		buttonPanel.add(newAccountButton);
		
		//monkeys.getScaledInstance(300,300);
		
		graphicPanel.add(new JLabel(monkeys));
		
		userNamePanel.setLayout(new FlowLayout());
		contentPane.setLayout(new FlowLayout());
		
		contentPane.add(graphicPanel);
		contentPane.add(userNamePanel);
		contentPane.add(passWordPanel);
		contentPane.add(buttonPanel);
		
		userNameField.addActionListener(this);
		passWordField.addActionListener(this);
		
		loginButton.addActionListener(this);
		newAccountButton.addActionListener(this);
		
		this.setBounds(0,0,310,600);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent evt){
		if (evt.getSource() == loginButton){
			int userNumber = verifyUser(userNameField.getText(), 
					new String(passWordField.getPassword()));
			if(userNumber >= 0) {
				loginUser(userNumber);
				this.remove(graphicPanel);
				this.remove(userNamePanel);
				this.remove(passWordPanel);
				this.remove(buttonPanel);
				repaint();

				imPanel = new JPanel();
				logoutButton = new JButton("Log Out");
				imPanel.add(logoutButton);
				logoutButton.addActionListener(this);
				contentPane.add(imPanel);
				this.setVisible(true);
				repaint();
			}
			else {
				userNameField.setText("");
				passWordField.setText("");
			}
		}
		if (evt.getSource() == newAccountButton){
			try {
				createLogin();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (evt.getSource() == logoutButton){
			this.remove(imPanel);
			JOptionPane.showMessageDialog(null, "You Are Logged Out!");
			contentPane.add(graphicPanel);
			contentPane.add(userNamePanel);
			contentPane.add(passWordPanel);
			contentPane.add(buttonPanel);
			userNameField.setText("");
			passWordField.setText("");
			repaint();
			this.setVisible(true);
		}
	}
	
	public static void main(String[] args) throws IOException {

//		createLogin();
//		int userNumber;
		generateAccountCollection();
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
		gui1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
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
	public int verifyUser (String userName, String passWord) {
		int userId = -1;

		//System.out.println("Username: " + userName);
		//System.out.println("Password: " + passWord);
		userId = Account.verifyLogIn(userName, passWord);
		if (userId >= 0) {
			JOptionPane.showMessageDialog(null, "Correct Login!");
			return userId;
		}
		else {
			JOptionPane.showMessageDialog(null, "Incorrect Login!");
			return -1;
		}
	}
	
	public static void loginUser (int id) {
		User tempUser = new User(Account.getInfo(id));
		userList.add(tempUser);
		tempUser = null;
	}
	
	/**
	 * This function creates a login account for a user.
	 * The user has five attempts to achieve an original combination
	 * before the program terminates.
	 * @throws IOException
	 */
	 
	public void createLogin () throws IOException {
		boolean loggedIn = true;
		int userId = -1;
		String userName;
		String password;
		for (int i = 0; i < 5; i++) {
			userName = userNameField.getText();
				//JOptionPane.showInputDialog(null,
				//		"Enter User Name",
				//		"Login",
				//		JOptionPane.QUESTION_MESSAGE);
			password = new String(passWordField.getPassword());
				//JOptionPane.showInputDialog(null,
				//		"Enter Password",
				//		"Password",
				//		JOptionPane.QUESTION_MESSAGE);
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




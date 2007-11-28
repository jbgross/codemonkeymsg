package edu.psu.ist.login;

public class Account {
	private static java.util.ArrayList accountCollection = new java.util.ArrayList();
	private int idNumber;
	private String userName;
	private String password;
	
	public Account(int idNumber, String userName, String password) {
		this.idNumber = idNumber;
		this.userName = userName;
		this.password = password;
	}
	/**
	 * This function creates a temporary Account object in order
	 * to verify that the supplied User Name and Password are valid.
	 * @param userName
	 * @param password
	 * @return int userID or negative (indicating failure)
	 */
	public static int verifyLogIn (String userName, String password){
		for (int i = 0; i < accountCollection.size(); i++) {
			Account accountHolder = (Account) accountCollection.get(i);
			if ((userName.equals(accountHolder.userName)) && (password.equals(accountHolder.password))) {
				return i;
			}
			accountHolder = null;
		}
		return -1;
	}

	public static String[] getInfo (int i) {
		Account accountHolder = (Account) accountCollection.get(i);
		String[] info = {accountHolder.userName, Integer.toString(accountHolder.idNumber)};
		return info;
	}
	
	public static void addAccount(Account account) {
		accountCollection.add(account);
	}
}

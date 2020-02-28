//Customer class
package Persons;

import Accounts.*;
import java.util.List;
import java.util.LinkedList;
import Log.Logs;

public class Customer extends Person implements Logs{

	private int id;
	private String email;
	private String password;
	List<String> statement;
	
	public List<String> getStatement() {
		return statement;
	}

	public void setStatement(List<String> statement) {
		this.statement = statement;
	}

	// The account number defaults can be 0 because
	Checking checkingAccount = new Checking(0, 0);
	Savings savingsAccount = new Savings(0, 0);
	Credit creditAccount = new Credit(0, 0, 0);

	// Constructor
	public Customer(String firstName, String lastName, String dateOfBirth, int id, String address, String phoneNum,
			String email, String password, int checkingNum, int savingsNum, int creditNum, double checkingBalance,
			double savingsBalance, double creditBalance, int maxCredit) {

		super(firstName, lastName, dateOfBirth, address, phoneNum);

		setId(id);
		setEmail(email);
		setPassword(password);

		checkingAccount.setAccountNum(checkingNum);
		checkingAccount.setBalance(checkingBalance);

		savingsAccount.setAccountNum(savingsNum);
		savingsAccount.setBalance(savingsBalance);

		creditAccount.setAccountNum(creditNum);
		creditAccount.setBalance(creditBalance);
		creditAccount.setMaxCredit(maxCredit);

		statement = new LinkedList<String>();

	}

	// Getters
	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Checking getCheckingAccount() {
		return checkingAccount;
	}

	public Savings getSavingsAccount() {
		return savingsAccount;
	}

	public Credit getCreditAccount() {
		return creditAccount;
	}

	// Setters
	public void setId(int id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void logActions(String actions) 
	{
	statement.add(actions);
	}
}
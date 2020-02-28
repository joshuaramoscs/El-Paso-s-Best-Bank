//Abstract Account class
package Accounts;

public abstract class Account {
	private int accountNum;
	protected double balance;
	
	
	// Constructor
	Account(int accountNum, double balance) {
		setAccountNum(accountNum);
		setBalance(balance);
	}

	// Setters
	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	// Getters
	public int getAccountNum() {
		return accountNum;
	}

	public double getBalance() {
		return balance;
	}

	// Methods
	public void inquireBalance() {
		System.out.println("Your balance is " + balance);
	}

	public double depositMoney(double deposit) {
		if (deposit > 0) {// went through
			balance += deposit;
			System.out.println("$" + deposit + " was diposited into your account.");
			inquireBalance();
			return deposit;
		} else {// invalid input
			System.out.println("Deposit must be greater than $0");
			return 0;
		}
	}

	public double withdrawMoney(double ammount) {
		if (balance >= ammount && ammount > 0) {//
			balance -= ammount;
			System.out.println("$" + ammount + " is being withdrawn from your account.");
			inquireBalance();
			return ammount;
		} else {
			System.out.println(
					"Transaction rejected. \nWithdrawal amount must be less than your balance and greater than 0.");
			inquireBalance();
			return 0;
		}
	}
}
//Credit class
package Accounts;

public class Credit extends Account {
	int maxCredit;

	// Constructor
	public Credit(int accountNum, double startingBalance, int maxCredit) {
		super(accountNum, startingBalance);
		setMaxCredit(maxCredit);
	}

	// Setters
	public void setMaxCredit(int maxCredit) {
		this.maxCredit = maxCredit;
	}

	// Getters
	public int getMaxCredit() {
		return maxCredit;
	}

	// Methods
	public double payCreditBalance(double amount) {
		if (balance < 0) {
			if (amount > 0) {
				if (balance + amount <= 0) {
					balance += amount;
					System.out.println("You payed $" + amount + " towards your credit account");
					inquireBalance();
					return balance;
				} else {
					System.out.println("Transaction rejected! \nYou are trying to pay more than what you owe.");
					inquireBalance();
					return 0;
				}
			} else {
				System.out.println("Payment must be greater than $0");
				return 0;
			}
		} else {
			System.out.println("Your credit balance is 0. There is no need to pay anything.");
			return 0;
		}
	}
}
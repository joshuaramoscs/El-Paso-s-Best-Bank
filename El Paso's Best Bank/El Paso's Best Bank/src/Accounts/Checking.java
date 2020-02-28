//Checking class
package Accounts;

public class Checking extends Account {
	//Constructor
	public Checking(int accountNum, double balance) {
		super(accountNum, balance);
	}

	//Called from paySomeone()
	//This is done to the person receiving the money.
	public void recieveMoney(double amount) {
		balance += amount;
	}
}
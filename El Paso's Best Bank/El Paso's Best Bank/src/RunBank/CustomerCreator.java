//CustomerCreator class
package RunBank;

import Persons.Customer;

public class CustomerCreator {
	// Index's are set in validheaderCheck() and are used to know where on the
	// database each element is.
	int firstNameIndex;
	int lastNameIndex;
	int dobIndex;
	int iDIndex;
	int emailIndex;
	int passwordIndex;
	int adressIndex;
	int phoneNumIndex;
	int checkingNumIndex;
	int checkingBalanceIndex;
	int savingsNumIndex;
	int savingsBalanceIndex;
	int creditNumIndex;
	int creditBalanceIndex;
	int creditMaxIndex;

	// values[] is used to store the values read on each line.
	String[] values;
	
	// These values are used to create a customer.
	String firstName;
	String lastName;
	String dateOfBirth;
	int id;
	String address;
	String phoneNum;
	String email;
	String password;
	int checkingNum;
	double checkingBalance;
	int savingsNum;
	double savingsBalance;
	int creditNum;
	double creditBalance;
	int creditMax;

	public CustomerCreator() {
	}
	
	public boolean setHeader(String line) {
		// Split the input line and store the values in the values array.
		values = line.split("	");

		if (validHeaderCheck(values)) {
			System.out.println("Header has been set");
			return true;
		} else {
			System.out.println(
					"Your list is missing elements. Make sure it contains the following in no particular order:");
			System.out.println(
					"First Name\nLast Name\nDate of Birth\nIdentification Number\nAddress\nPhone Number\nChecking Account Number"
							+ "\nSavings Account Number\nCredit Account Number\nChecking Starting Balance\nSavings Starting Balance\nCredit Starting Balance");
			return false;
		}
	}

	// Check if the list meets the requirements to be a valid list.
	public boolean validHeaderCheck(String[] values) {

		int counter = 0;
		for (int i = 0; i < values.length; i++) {
			switch (values[i]) {
			case "First Name":
				this.firstNameIndex = i;
				counter++;
				break;
			case "Last Name":
				this.lastNameIndex = i;
				counter++;
				break;
			case "Date of Birth":
				this.dobIndex = i;
				counter++;
				break;
			case "Address":
				this.adressIndex = i;
				counter++;
				break;
			case "Phone Number":
				this.phoneNumIndex = i;
				counter++;
				break;
			case"Email":
				this.emailIndex = i;
				counter++;
				break;
			case"Password":
				this.passwordIndex = i;
				counter++;
				break;
			case "Identification Number":
				this.iDIndex = i;
				counter++;
				break;
			case "Checking Account Number":// not required
				this.checkingNumIndex = i;
				counter++;
				break;
			case "Savings Account Number":
				this.savingsNumIndex = i;
				counter++;
				break;
			case "Credit Account Number":// not required
				this.creditNumIndex = i;
				counter++;
				break;
			case "Checking Starting Balance":// not required
				this.checkingBalanceIndex = i;
				counter++;
				break;
			case "Savings Starting Balance":
				this.savingsBalanceIndex = i;
				counter++;
				break;
			case "Credit Starting Balance":// not required
				this.creditBalanceIndex = i;
				counter++;
				break;
			case "Credit Max":// not required
				this.creditMaxIndex = i;
				counter++;
				break;
			}
		}
		if (counter >= 10) {
			System.out.println("Header Check Passed");
			return true;
		} else {
			System.out.println("Items found in header: " + counter);
			return false;
		}
	}

	public Customer createCustomer(String line) {
		// Split the input line and store the values in the values array.
		values = line.split("	");
		
		
		this.firstName = values[this.firstNameIndex].trim();
		this.lastName = values[this.lastNameIndex].trim();
		this.dateOfBirth = values[this.dobIndex].trim();
		this.address = values[this.adressIndex].trim();
		this.phoneNum = values[this.phoneNumIndex].trim();
		this.id = Integer.parseInt(values[this.iDIndex].trim());
		this.email = values[this.emailIndex].trim();
		this.password = values[this.passwordIndex].trim();
		//if fifth to last index in values array is empty, then the customer only has a savings account.
		if(values.length < 11) {
			this.checkingNum = 0;
			this.savingsNum = Integer.parseInt(values[values.length-2].trim());
			this.creditNum = 0 ;
			this.checkingBalance = 0;
			this.savingsBalance = Double.parseDouble(values[values.length-1].trim());
			this.creditBalance = 0;
			this.creditMax = 0;
		//if third to last index in values array is empty, then the customer only has a checking and a savings account.
		} else if(values.length < 14) {
			this.checkingNum = Integer.parseInt(values[values.length-4].trim());
			this.savingsNum = Integer.parseInt(values[values.length-3].trim());
			this.creditNum = 0 ;
			this.checkingBalance = Double.parseDouble(values[values.length-2].trim());
			this.savingsBalance = Double.parseDouble(values[values.length-1].trim());
			this.creditBalance = 0;
			this.creditMax = 0;
		//else, the customer has all accounts.
		}else {
			this.checkingNum = Integer.parseInt(values[this.checkingNumIndex].trim());
			this.savingsNum = Integer.parseInt(values[this.savingsNumIndex].trim());
			this.creditNum = Integer.parseInt(values[this.creditNumIndex].trim());
			this.checkingBalance = Double.parseDouble(values[this.checkingBalanceIndex].trim());
			this.savingsBalance = Double.parseDouble(values[this.savingsBalanceIndex].trim());
			this.creditBalance = Double.parseDouble(values[this.creditBalanceIndex].trim());
			this.creditMax = Integer.parseInt(values[this.creditMaxIndex].trim());
		}
		Customer newCustomer = new Customer(this.firstName, this.lastName, this.dateOfBirth, this.id, this.address,
				this.phoneNum,this.email,this.password, this.checkingNum, this.savingsNum, this.creditNum, this.checkingBalance,
				this.savingsBalance, this.creditBalance, this.creditMax);
		return newCustomer;
	}
}
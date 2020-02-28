//RunBank class (main)
package RunBank;

import java.io.*;
import java.util.*;
import Persons.Customer;

public class RunBank {
	public static void main(String[] args) throws FileNotFoundException, IOException {

		// Create buffer reader to read the file "Bank Users.txt".
		// line is used to store what the buffered reader reads.
		BufferedReader br = new BufferedReader(new FileReader("BankUsers.txt"));
		String line = null;

		// Create a CustomerCreator to set header and create customers.
		CustomerCreator createCustomer = new CustomerCreator();

		// HashMap is used to store all the Customers. The key is their ID#.
		HashMap<String, Customer> mapOfCustomers = new HashMap<String, Customer>();
		String firstAndLastNameKey;

		// temCustomer is used to create customers
		Customer tempCustomer = null;

		// lastCustomer is a reference to the last customer
		Customer lastCustomer = null;

		// Try to create customers.
		try {
			line = br.readLine(); // Read the header.
			// Check if header meets the minimum requirements.
			if (createCustomer.setHeader(line)) { // If header is good.
				System.out.println("Customers are being created.");
				line = br.readLine(); // Read the first customer.

				while (line != null) { // While a new line is read, create a new customer and put it in the
										// mapOfCustomer.
					tempCustomer = createCustomer.createCustomer(line);
					firstAndLastNameKey = tempCustomer.getFirstName() + " " + tempCustomer.getLastName();
					mapOfCustomers.put(firstAndLastNameKey.toLowerCase(), tempCustomer);
					try { // Try to read the next customer.
						line = br.readLine();
					} catch (IOException e) {
						System.out.println("There was a problem reading the \"Bank User.txt\" file.");
						e.printStackTrace();
					}
				}
				lastCustomer = tempCustomer;
				System.out.println("Customers are in the system.");
			} else { // If header is not acceptable
				System.out.println("Customer accounts could not be created.");
			}

		} catch (IOException e) { // Catch not being able to create the customers.
			System.out.println("Bank Users.txt could not be found");
		}

		try { // Try to close the BufferedReader as it will no longer be used.
			br.close();
		} catch (IOException e) { // Catch closing the "Bank Users.txt"
			System.out.println("There was a problem trying to close the \"Bank Users.txt\" file.");
			e.printStackTrace();
		} // end of creating customers

		// **********START BANK**********
		boolean activeBank = true; // Bank exits if false
		String selection; // Stores user selection
		String firstName; // Stores First Name
		String lastName; // Stores Last Name
		String password;
		double amount = 0; // Stores the amount of money the user wants to use.
		String otherPersonNameKey;

		List<String> records = new LinkedList<String>();
		String recordsInput = "";
		double recordsCheck = 0;

		Scanner input = new Scanner(System.in);

		while (activeBank) {
			System.out.println("=================================");
			System.out.println("\n\nWELCOME TO THE BEST BANK IN EL PASO!");
			System.out.println("Enter your first name" + "\nOr \"New\" if you are a new customer"
					+ "\nOr \"Manager\" if you are a manager" + "\nOr \"Transaction\" to run automatic transactions:");
			selection = input.next().toLowerCase();

			if (selection.matches("manager")) { // Manager

				System.out.println("You are now logged in as a Manager.");
				System.out.println("=================================");
				System.out.println("[1] View individual customer's account\n[2] View all bank accounts"
						+ "\n[3] Create Statement\n[4] Shut down the bank application");
				selection = input.next();

				switch (selection) {
				case "1": // View individual customer's account
					System.out.println("Enter the customer's first name.");
					firstName = input.next().toLowerCase();
					System.out.println("Enter the customer's last name");
					lastName = input.next().toLowerCase();
					firstAndLastNameKey = firstName + " " + lastName;
					if (mapOfCustomers.containsKey(firstAndLastNameKey)) {
						viewIndividualCustomerInfo(mapOfCustomers.get(firstAndLastNameKey));
					} else {
						System.out.println("Customer \"" + firstName + " " + lastName + "\" is not in the system.");
						backToMainMenu();
					}
					break;

				case "2": // View all bank accounts
					for (String key : mapOfCustomers.keySet()) {
						viewIndividualCustomerInfo(mapOfCustomers.get(key));
					}
					break;

				case "3": // Create Statement
					System.out.println("Enter the customer's first name.");
					firstName = input.next().toLowerCase();
					System.out.println("Enter the customer's last name");
					lastName = input.next().toLowerCase();
					firstAndLastNameKey = firstName + " " + lastName;
					if (mapOfCustomers.containsKey(firstAndLastNameKey)) {
						Statements(mapOfCustomers.get(firstAndLastNameKey).getFirstName(),mapOfCustomers.get(firstAndLastNameKey).getStatement() );
					} else {
						System.out.println("Customer \"" + firstName + " " + lastName + "\" is not in the system.");
						backToMainMenu();
					}
					break;

				case "4": // Shut down bank
					activeBank = false;
					break;
				default:
					System.out.println("Thats not an option.");
					backToMainMenu();
				}
			} else if (selection.matches("new")) { // New User
				String dobMonth;
				String dobDay;
				String dobYear;
				String dob;
				int id;
				String email;
				String address;
				String phoneNum;
				int checkingNum = 0;
				int savingsNum = 0;
				int creditNum = 0;
				double checkingBalance = 0;
				double savingsBalance = 0;
				double creditBalance = 0;
				int creditMax = 0;

				System.out.println("Please enter your FIRST name:");
				firstName = input.next().toLowerCase();
				System.out.println("Please enter your LAST name:");
				lastName = input.next().toLowerCase();
				System.out.println("Please spell out the month you were born in:");
				dobMonth = input.next().toLowerCase();
				System.out.println("Please enter the day you were born in:");
				dobDay = input.next().toLowerCase();
				System.out.println("Please enter the year you were born in:");
				dobYear = input.next().toLowerCase();
				dob = "\"" + dobMonth + dobDay + dobYear + "\"";
				id = lastCustomer.getId() + 1;
				System.out.println("Please enter the name of your address");
				address = "\"" + input.next().toLowerCase() + "\"";
				System.out.println("Please enter your phone number");
				phoneNum = "\"" + input.next().toLowerCase() + "\"";
				System.out.println("Please enter your email address");
				email = input.next().toLowerCase();
				System.out.println("Please enter the password you would like attached to your account");
				password = input.next().toLowerCase();
				savingsNum = lastCustomer.getSavingsAccount().getAccountNum() + 1;
				boolean check = false;
				System.out.println(
						"Please deposit your money to savings. You may move it to a checking account later if you wish to open an account.");
				try {
					savingsBalance = input.nextDouble();
					check = false;
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Account will not be created.");
					check = true;
				}

				if (!check) {
					System.out.println("Would you like a checking account? Press 1 for YES, or 2 for NO.");
					boolean check1 = false;
					boolean check2 = false;
					do {
						selection = input.next();
						if (selection.contentEquals("1")) {
							checkingNum = lastCustomer.getCheckingAccount().getAccountNum() + 1;
							System.out.println(
									"Checking account has been created. You can manage it by logging in after this set up.");
							System.out.println("Would you like a credit account? Press 1 for YES, or 2 for NO.");
							selection = input.next();
							do {
								if (selection.contentEquals("1")) {
									creditNum = lastCustomer.getCreditAccount().getAccountNum() + 1;
									System.out.println(
											"Credit account has been created. What is the max credit that you want?");
									creditMax = input.nextInt();
									System.out.println("Your max credit will be: " + creditMax);
									check2 = false;
								} else if (selection.contentEquals("2")) {
									System.out.println("No credit account will be created for you at this moment.");
									check2 = false;
								} else {
									System.out.println("Press 1 for YES, or 2 for NO.");
									check2 = true;
								}
							} while (check2);
							check1 = false;
						} else if (selection.contentEquals("2")) {
							check1 = false;
							System.out.println("No checking account will be created for you at this moment.");
						} else {
							check1 = true;
							System.out.println("Press 1 for YES, or 2 for NO.");
						}
					} while (check1);
					firstAndLastNameKey = firstName + " " + lastName;
					System.out.println();
					lastCustomer = createCustomer.createCustomer(firstName + "\t" + lastName + "\t" + dob + "\t" + email
							+ "\t" + id + "\t" + password + "\t" + address + "\t" + phoneNum + "\t" + checkingNum + "\t"
							+ savingsNum + "\t" + creditNum + "\t" + checkingBalance + "\t" + savingsBalance + "\t"
							+ creditBalance + "\t" + creditMax);
					mapOfCustomers.put(firstAndLastNameKey.toLowerCase(), lastCustomer);
				}

			} else if (selection.matches("transaction")) { // Transaction Reader
				String accountType = "";
				String[] values = new String[8];

				try {
					BufferedReader br2 = new BufferedReader(new FileReader("Transaction Actions.txt"));
					line = null;
					List<String> valuesList;
					try {
						boolean transactionReaderDone = false;
						line = br2.readLine();
						line = br2.readLine();
						valuesList = new ArrayList<String>(Arrays.asList(line.split("	")));
						while (!transactionReaderDone) {
							// System.out.println(line);
							try {
								values = line.split("	");
								valuesList = new ArrayList<String>(Arrays.asList(line.split("	")));

								// if pays
								if (valuesList.contains("pays")) {
									firstAndLastNameKey = values[0].toLowerCase() + " " + values[1].toLowerCase();
									otherPersonNameKey = values[4].toLowerCase() + " " + values[5].toLowerCase();
									amount = Double.parseDouble(values[7]);
									System.out.println("___________________________________________________");
									System.out.println("Performing pays transaction for: " + firstAndLastNameKey);
									if (mapOfCustomers.containsKey(firstAndLastNameKey)) {
										recordsCheck = mapOfCustomers.get(firstAndLastNameKey).getCheckingAccount()
												.withdrawMoney(amount);
										if (recordsCheck != 0) {
											mapOfCustomers.get(otherPersonNameKey).getCheckingAccount()
													.recieveMoney(amount);
										}
										recordsInput = mapOfCustomers.get(firstAndLastNameKey).getFirstName() + " "
												+ mapOfCustomers.get(firstAndLastNameKey).getLastName() + " Payed: "
												+ mapOfCustomers.get(otherPersonNameKey).getFirstName() + " "
												+ mapOfCustomers.get(otherPersonNameKey).getLastName() + " "
												+ recordsCheck;

										mapOfCustomers.get(firstAndLastNameKey).logActions(recordsInput);
										records.add(recordsInput);

									} else {
										System.out.println(firstAndLastNameKey + " is not in the system.");
									}

									// if transfers
								} else if (valuesList.contains("transfers")) {
									firstAndLastNameKey = values[0].toLowerCase() + " " + values[1].toLowerCase();
									otherPersonNameKey = values[4].toLowerCase() + " " + values[5].toLowerCase();
									amount = Double.parseDouble(values[7]);
									accountType = values[2];
									System.out.println("___________________________________________________");
									System.out.println("Performing transfers transaction for: " + firstAndLastNameKey);
									if (mapOfCustomers.containsKey(firstAndLastNameKey)) {
										if (accountType.contentEquals("Savings")) {
											mapOfCustomers.get(firstAndLastNameKey).getSavingsAccount()
													.withdrawMoney(amount);
											recordsCheck = mapOfCustomers.get(firstAndLastNameKey).getSavingsAccount()
													.depositMoney(amount);
											recordsInput = mapOfCustomers.get(firstAndLastNameKey).getFirstName() + " "
													+ mapOfCustomers.get(firstAndLastNameKey).getLastName()
													+ " Transfered from Savings to Checkings: " + recordsCheck;
										} else if (accountType.contentEquals("Checking")) {
											mapOfCustomers.get(firstAndLastNameKey).getCheckingAccount()
													.withdrawMoney(amount);
											recordsCheck = mapOfCustomers.get(firstAndLastNameKey).getCheckingAccount()
													.depositMoney(amount);
											recordsInput = mapOfCustomers.get(firstAndLastNameKey).getFirstName() + " "
													+ mapOfCustomers.get(firstAndLastNameKey).getLastName()
													+ " Transfered from Checking to Savings: " + recordsCheck;
										}
										mapOfCustomers.get(firstAndLastNameKey).logActions(recordsInput);
										records.add(recordsInput);
									} else {
										System.out.println(firstAndLastNameKey + " is not in the system.");
									}

									// if deposits
								} else if (valuesList.contains("deposits")) {
									firstAndLastNameKey = values[4].toLowerCase() + " " + values[5].toLowerCase();
									amount = Double.parseDouble(values[7]);
									System.out.println("___________________________________________________");
									System.out.println("Performing deposits transaction for: " + firstAndLastNameKey);
									if (mapOfCustomers.containsKey(firstAndLastNameKey)) {
										recordsCheck = mapOfCustomers.get(firstAndLastNameKey).getCheckingAccount()
												.depositMoney(amount);
										recordsInput = mapOfCustomers.get(firstAndLastNameKey).getFirstName() + " "
												+ mapOfCustomers.get(firstAndLastNameKey).getLastName()
												+ " Deposited to " + values[3] + ": " + recordsCheck;
										mapOfCustomers.get(firstAndLastNameKey).logActions(recordsInput);
										records.add(recordsInput);
									} else {
										System.out.println(firstAndLastNameKey + " is not in the system.");
									}

									// if withdraws
								} else if (valuesList.contains("withdraws")) {
									firstAndLastNameKey = values[0].toLowerCase() + " " + values[1].toLowerCase();
									amount = Double.parseDouble(values[7]);
									accountType = values[2];
									System.out.println("___________________________________________________");
									System.out.println("Performing withdraws transaction for: " + firstAndLastNameKey);
									if (mapOfCustomers.containsKey(firstAndLastNameKey)) {
										if (accountType.contentEquals("Savings")) {
											recordsCheck = mapOfCustomers.get(firstAndLastNameKey).getSavingsAccount()
													.withdrawMoney(amount);
										} else if (accountType.contentEquals("Checking")) {
											recordsCheck = mapOfCustomers.get(firstAndLastNameKey).getCheckingAccount()
													.withdrawMoney(amount);
										}
										recordsInput = mapOfCustomers.get(firstAndLastNameKey).getFirstName() + " "
												+ mapOfCustomers.get(firstAndLastNameKey).getLastName()
												+ " Withdrew from " + values[3] + ": " + recordsCheck;
										mapOfCustomers.get(firstAndLastNameKey).logActions(recordsInput);
										records.add(recordsInput);
									} else {
										System.out.println(firstAndLastNameKey + " is not in the system.");
									}

									// if inquires
								} else if (valuesList.contains("inquires")) {
									firstAndLastNameKey = values[0].toLowerCase() + " " + values[1].toLowerCase();
									accountType = values[2];
									System.out.println("___________________________________________________");
									System.out.println("Performing inquires transaction for: " + firstAndLastNameKey);
									if (mapOfCustomers.containsKey(firstAndLastNameKey)) {
										if (accountType.contentEquals("Savings")) {
											mapOfCustomers.get(firstAndLastNameKey).getSavingsAccount().inquireBalance();
										} else if (accountType.contentEquals("Checking")) {
											mapOfCustomers.get(firstAndLastNameKey).getCheckingAccount().inquireBalance();
										}
										recordsInput = mapOfCustomers.get(firstAndLastNameKey).getFirstName() + " "
												+ mapOfCustomers.get(firstAndLastNameKey).getLastName() + " Inquired "
												+ values[3] + " Balance";
										mapOfCustomers.get(firstAndLastNameKey).logActions(recordsInput);
										records.add(recordsInput);
									} else {
										System.out.println(firstAndLastNameKey + " is not in the system.");
									}
								}

							} catch (NullPointerException e) {
								System.out.println("Transaction Reader is done");
								transactionReaderDone = true;
							}

							for (int i = 0; i < values.length; i++) {
								values[i] = null;
							}
							valuesList.clear();

							line = br2.readLine();
						}
					} catch (IOException e) {
						System.out.println("There was a problem reading the \"Transaction Actions.txt\" file.");
					}

				} catch (FileNotFoundException e) {
					System.out.println("\"Transaction Actions.txt\" was not found.");
				}

			} else { // If user is a Customer
				firstName = selection.toLowerCase();
				System.out.println("Please enter your last name, or \"Cancel\" to go back:");
				lastName = input.next().toLowerCase();
				firstAndLastNameKey = firstName + " " + lastName;

				if (mapOfCustomers.containsKey(firstAndLastNameKey)) {

					System.out.println("Please enter your password:");
					password = input.next();

					if (mapOfCustomers.get(firstAndLastNameKey).getPassword().matches(password)) {
						// User will now select what it wants to do.
						System.out.println("Welcome " + mapOfCustomers.get(firstAndLastNameKey).getFirstName() + " "
								+ mapOfCustomers.get(firstAndLastNameKey).getLastName() + "!");
						System.out.println("=================================");
						System.out.println("You are now logged in. \nWhich account would you like to access?: ");
						System.out.println("=================================");
						System.out.println("[1] Checking\n[2] Savings\n[3] Credit");

						selection = input.next();

						// User Selects Checking
						if (selection.contentEquals("1")) {
							System.out.println("You are now in your Checking account.");
							System.out.println("What would you like to do?:");
							System.out.println(
									"[1] Inquire Balance\n[2] Deposit Money\n[3] Withdraw Money \n[4] Transfer Money to Savings \n[5] Pay Credit Account \n[6] Pay Someone");
							selection = input.next();

							switch (selection) {
							// User selects Inquire Balance
							case "1":
								mapOfCustomers.get(firstAndLastNameKey).getCheckingAccount().inquireBalance();
								recordsInput = mapOfCustomers.get(firstAndLastNameKey).getFirstName() + " "
										+ mapOfCustomers.get(firstAndLastNameKey).getLastName()
										+ " Inquired Checking Balance";
								mapOfCustomers.get(firstAndLastNameKey).logActions(recordsInput);
								records.add(recordsInput);
								break;
							// User selects Deposit Money
							case "2":
								System.out.println("Enter the money you would like to deposit.");
								amount = input.nextInt();
								recordsCheck = mapOfCustomers.get(firstAndLastNameKey).getCheckingAccount()
										.depositMoney(amount);
								recordsInput = mapOfCustomers.get(firstAndLastNameKey).getFirstName() + " "
										+ mapOfCustomers.get(firstAndLastNameKey).getLastName()
										+ " Deposited to Checking: " + recordsCheck;
								mapOfCustomers.get(firstAndLastNameKey).logActions(recordsInput);
								records.add(recordsInput);
								break;
							// User selects Withdraw
							case "3":
								System.out.println("Enter the money you would like to withdaraw.");
								amount = input.nextInt();
								recordsCheck = mapOfCustomers.get(firstAndLastNameKey).getCheckingAccount()
										.withdrawMoney(amount);
								recordsInput = mapOfCustomers.get(firstAndLastNameKey).getFirstName() + " "
										+ mapOfCustomers.get(firstAndLastNameKey).getLastName()
										+ " Withdrew from Checking: " + recordsCheck;
								mapOfCustomers.get(firstAndLastNameKey).logActions(recordsInput);
								records.add(recordsInput);
								break;
							// User selects Transfer Money to Savings
							case "4":
								System.out.println("Enter the money you would to transfer to Savings.");
								amount = input.nextInt();
								if (mapOfCustomers.get(firstAndLastNameKey).getCheckingAccount()
										.withdrawMoney(amount) > 0) {
									recordsCheck = mapOfCustomers.get(firstAndLastNameKey).getSavingsAccount()
											.depositMoney(amount);
									recordsInput = mapOfCustomers.get(firstAndLastNameKey).getFirstName() + " "
											+ mapOfCustomers.get(firstAndLastNameKey).getLastName()
											+ " Transfered from Checking to Savings: " + recordsCheck;
									mapOfCustomers.get(firstAndLastNameKey).logActions(recordsInput);
									records.add(recordsInput);
								}
								break;
							// User selects Pay Credit Account
							case "5":
								System.out.println("Enter the money you would like to pay towards your Credit.");
								amount = input.nextDouble();
								mapOfCustomers.get(firstAndLastNameKey).getCheckingAccount().withdrawMoney(amount);
								recordsCheck = mapOfCustomers.get(firstAndLastNameKey).getCreditAccount()
										.payCreditBalance(amount);
								recordsInput = mapOfCustomers.get(firstAndLastNameKey).getFirstName() + " "
										+ mapOfCustomers.get(firstAndLastNameKey).getLastName() + " Payed: "
										+ recordsCheck + " to Credit";
								mapOfCustomers.get(firstAndLastNameKey).logActions(recordsInput);
								records.add(recordsInput);
								break;
							// User selects Pay Someone
							case "6":
								System.out.println("Type the First Name of the person you are trying to pay.");
								firstName = input.next();
								System.out.println("Type the Last Name of the person you are trying to pay.");
								lastName = input.next();
								otherPersonNameKey = firstName + " " + lastName;
								if (mapOfCustomers.containsKey(otherPersonNameKey)) {
									System.out.println("Enter the money you would like to pay "
											+ mapOfCustomers.get(otherPersonNameKey).getFirstName());
									amount = input.nextDouble();
									recordsCheck = mapOfCustomers.get(firstAndLastNameKey).getCheckingAccount()
											.withdrawMoney(amount);
									mapOfCustomers.get(otherPersonNameKey).getCheckingAccount().recieveMoney(amount);
								}
								recordsInput = mapOfCustomers.get(firstAndLastNameKey).getFirstName() + " "
										+ mapOfCustomers.get(firstAndLastNameKey).getLastName() + " Payed: "
										+ mapOfCustomers.get(otherPersonNameKey).getFirstName() + " "
										+ mapOfCustomers.get(otherPersonNameKey).getLastName() + " " + recordsCheck;
								mapOfCustomers.get(firstAndLastNameKey).logActions(recordsInput);
								records.add(recordsInput);
							}
							selection = "";
						}

						// User Selects Savings
						if (selection.contentEquals("2")) {
							System.out.println("You are now in your Savings account.");
							System.out.println("What would you like to do?:");
							System.out.println("[1] Inquire Balance\n[2] Transfer Money to Checkings");
							selection = input.next();

							switch (selection) {
							// User selects Inquire Balance
							case "1":
								mapOfCustomers.get(firstAndLastNameKey).getSavingsAccount().inquireBalance();
								recordsInput = mapOfCustomers.get(firstAndLastNameKey).getFirstName() + " "
										+ mapOfCustomers.get(firstAndLastNameKey).getLastName()
										+ " Inquired Checking Balance";
								mapOfCustomers.get(firstAndLastNameKey).logActions(recordsInput);
								records.add(recordsInput);
								break;
							// User selects Transfer to Checking
							case "2":
								System.out.println("Enter the money you would like to transfer to Checkings.");
								amount = input.nextInt();
								if (mapOfCustomers.get(firstAndLastNameKey).getSavingsAccount()
										.withdrawMoney(amount) > 0) {
									recordsCheck = mapOfCustomers.get(firstAndLastNameKey).getCheckingAccount()
											.depositMoney(amount);
								}
								recordsInput = mapOfCustomers.get(firstAndLastNameKey).getFirstName() + " "
										+ mapOfCustomers.get(firstAndLastNameKey).getLastName()
										+ " Transfered from Savings to Checkings: " + recordsCheck;
								mapOfCustomers.get(firstAndLastNameKey).logActions(recordsInput);
								records.add(recordsInput);
							}
							selection = "";
						}

						// User Selects Credit
						if (selection.contentEquals("3")) {
							System.out.println("You are now in your Credit account.");
							System.out.println("What would you like to do?:");
							System.out.println("To pay dept, you must go to your checking account");
							System.out.println("[1] Inquire Balance");
							selection = input.next();

							// User selects Inquire Balance
							if (selection.contentEquals("1")) {
								mapOfCustomers.get(firstAndLastNameKey).getCreditAccount().inquireBalance();
								recordsInput = mapOfCustomers.get(firstAndLastNameKey).getFirstName() + " "
										+ mapOfCustomers.get(firstAndLastNameKey).getLastName()
										+ " Inquired Credit Balance";
								mapOfCustomers.get(firstAndLastNameKey).logActions(recordsInput);
								records.add(recordsInput);
							}
						}
						selection = ""; // clear selection for next selection
					} else {
						System.out.println("Incorrect Password");
						backToMainMenu();
					} // end of customer
				} else { // If user is not in the system
					System.out.println(
							firstName + " " + lastName + " could not be found in our system. \nPlease try again.");
					backToMainMenu();
				}
			} // end of customer
		} // End of Bank Simulation
		input.close();
		System.out.println("Shutting down the bank...\nThank you");

		// Print Updated Balance Sheet
		PrintStream out = new PrintStream(new FileOutputStream("Updated Balance Sheet.txt"));
		System.setOut(out);
		System.out.println(
				"First Name	Last Name	Date of Birth	Email	Identification Number	Password	Address	Phone Number	Checking Account Number	Savings Account Number	Credit Account Number	Checking Starting Balance	Savings Starting Balance	Credit Starting Balance	Credit Max");
		for (HashMap.Entry<String, Customer> entry : mapOfCustomers.entrySet()) {
			System.out.println(entry.getValue().getFirstName() + "\t" + entry.getValue().getLastName() + "\t"
					+ entry.getValue().getDateOfBirth() + "\t" + entry.getValue().getEmail() + "\t"
					+ entry.getValue().getId() + "\t" + entry.getValue().getPassword() + "\t"
					+ entry.getValue().getAdress() + "\t" + entry.getValue().getPhoneNum() + "\t"
					+ entry.getValue().getCheckingAccount().getAccountNum() + "\t"
					+ entry.getValue().getSavingsAccount().getAccountNum() + "\t"
					+ entry.getValue().getCreditAccount().getAccountNum() + "\t"
					+ entry.getValue().getCheckingAccount().getBalance() + "\t"
					+ entry.getValue().getSavingsAccount().getBalance() + "\t"
					+ entry.getValue().getCreditAccount().getBalance() + "\t"
					+ entry.getValue().getCreditAccount().getMaxCredit());
		}
		out.close();
		Statements("BankLogs",records);
	}

	// Methods
	// General Methods
	public static void backToMainMenu() {
		System.out.println("Going back to main menu...");
	}

	// Manager Methods
	public static void viewAllCustomerInfo(HashMap<String, Customer> mapOfCustomers) {
		for (String key : mapOfCustomers.keySet()) {
			viewIndividualCustomerInfo(mapOfCustomers.get(key));
		}
	}

	public static void viewIndividualCustomerInfo(Customer customer) {
		if (customer.getCheckingAccount().getAccountNum() == 0) { // Customer only has a savings
			System.out.println("\n" + printBasicCustomerInfo(customer) + "\n");
		} else if (customer.getCreditAccount().getAccountNum() == 0) { // Customer only has checking and savings
			System.out.println("\n" + printBasicCustomerInfo(customer) + printCustomerCheckingInfo(customer) + "\n");
		} else { // Customer has all three counts
			System.out.println("\n" + printBasicCustomerInfo(customer) + printCustomerCheckingInfo(customer)
					+ printCustomerCreditInfo(customer) + "\n");
		}
	}

	public static String printBasicCustomerInfo(Customer customer) {
		return customer.getFirstName() + " " + customer.getLastName() + "\nDOB: " + customer.getDateOfBirth()
				+ "\nID #: " + customer.getId() + "\nAddress: " + customer.getAdress() + "\nPhone #: "
				+ customer.getPhoneNum() + "\nSavings Account Number: " + customer.getSavingsAccount().getAccountNum()
				+ "	Balance: $" + customer.getSavingsAccount().getBalance();
	}

	public static String printCustomerCheckingInfo(Customer customer) {
		return "\nChecking Account Number: " + customer.getCheckingAccount().getAccountNum() + "	Balance: $"
				+ customer.getCheckingAccount().getBalance();
	}

	public static String printCustomerCreditInfo(Customer customer) {
		return "\nCredit Account Number: " + customer.getCreditAccount().getAccountNum() + "	Balance: $"
				+ customer.getCreditAccount().getBalance() + "	Max Credit: "
				+ customer.getCreditAccount().getMaxCredit();
	}

	// Customer Methods
	public static String balanceTemplate(String a, String b, String acc, String balance) {// template for balance
		// transactions
		String y = ("First Name		Last Name		Account		Balnace:\n");
		String z = ("" + a + "			" + b + "			" + acc + "		" + balance);
		return y + z;
	}
	
	public static void Statements(String txtName,List<String>listOfActions) throws IOException {
		//PrintStream out = new PrintStream(new FileOutputStream("Updated Balance Sheet.txt"))
		System.out.println("Creating Statement...");
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(txtName + ""));
		String header = ("From First Name\tFrom Last Name\tFrom Where\tAction\tTo First Name\tTo Last Name\tTo Where\tAmmount\n");
		buffWrite.write(header);
		for (int i = 0; i < listOfActions.size(); i++) {
			System.out.println(listOfActions.get(i));
			buffWrite.write(listOfActions.get(i) + "\n");
		}
		buffWrite.close();
		System.out.print("Statement Complete");
	}
}
	
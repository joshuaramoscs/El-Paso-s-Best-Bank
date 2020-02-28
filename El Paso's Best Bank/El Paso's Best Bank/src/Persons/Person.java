//Person class
package Persons;

public abstract class Person {
	protected String firstName;
	protected String lastName;
	protected String dateOfBirth;
	protected String address;
	protected String phoneNum;


	Person(String firstName, String lastName, String dateOfBirth, String address, String phoneNum) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.phoneNum = phoneNum;
	}
	
	// Setters
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setAddress(String adress) {
		this.address = adress;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	// Getters
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getAdress() {
		return address;
	}

	public String getPhoneNum() {
		return phoneNum;
	}
}
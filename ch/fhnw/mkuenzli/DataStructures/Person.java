package ch.fhnw.mkuenzli.DataStructures;

import java.util.Random;

public class Person implements Comparable<Person> {
	private String FirstName;
	private String LastName;
	private int PersonNr;
	
	/**
	 * Represents a basic person.
	 * 
	 * @param firstName String First Name
	 * @param lastName String Last Name
	 */
	public Person(String firstName, String lastName) {
		Random rn = new Random();
		PersonNr = (int)(rn.nextDouble()*10000);
		FirstName = firstName;
		LastName = lastName;
	}
	
	@Override
	public String toString() {
		return PersonNr + " - " + FirstName + " " + LastName;
	}

	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	public int getPersonNr() {
		return PersonNr;
	}

	@Override
	public int compareTo(Person p) {
		return this.getPersonNr()-((Person)p).getPersonNr();
	}
	
}

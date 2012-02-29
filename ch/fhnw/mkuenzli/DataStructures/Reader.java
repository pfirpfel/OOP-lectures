package ch.fhnw.mkuenzli.DataStructures;

import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
	
	public static ArrayList<Person> getInput(){
		ArrayList<Person> input = new ArrayList<Person>();
		boolean doContinue = true;
		Scanner sc = new Scanner(System.in);
		
		while(doContinue){
			String firstName, lastName;
			
			System.out.println("First name?");
			firstName = sc.next();
			System.out.println("Last name?");
			lastName = sc.next();
			
			input.add(new Person(firstName, lastName));
			
			System.out.println("New person? (y/n)");
			String cont = sc.next();
			if(!(cont.equals("y"))) doContinue = false;
		}
		
		return input;
	}
	
}

package ch.fhnw.mkuenzli.DataStructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class Runner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// setup
		ArrayList<Person> linear = new ArrayList<Person>();
		TreeSet<Person> tree = new TreeSet<Person>();
		HashMap<Integer, Person> hashTable = new HashMap<Integer, Person>();
		SinglyLinkedList<Person> linkedList = new SinglyLinkedList<Person>();
		
		// 1: collect data
		//linear = Reader.getInput();
		
		// random names powered by behindthename.com/random
		linear.add(new Person("Tobias", "Hartmann"));
		linear.add(new Person("Wenzeslaus", "Kraus"));
		linear.add(new Person("Orel", "Kruger"));
		linear.add(new Person("Dietmar", "Blumenthal"));
		linear.add(new Person("Dennis", "Gass"));
		linear.add(new Person("Egon", "Zilberschlag"));
		
		// 2: fill data structures
		Iterator<Person> itr = linear.iterator();
		while(itr.hasNext()){
			Person currentPerson = itr.next();
			tree.add(currentPerson);
			hashTable.put(currentPerson.getPersonNr(), currentPerson);
			linkedList.add(currentPerson);
		}
		
		// 3: print data structures
		System.out.println("Linear (ArrayList):");
		itr = linear.iterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
		}
		
		System.out.println();
		
		System.out.println("Tree (TreeSet):");
		for(Person dude : tree){
			System.out.println(dude);
		}

		System.out.println();
		
		System.out.println("Hash table (HashMap):");
		Set<Map.Entry<Integer, Person>> set = hashTable.entrySet();
		for(Map.Entry<Integer, Person> dude : set) {
			System.out.println(dude.getValue());
		}
		
		System.out.println();
		System.out.println("SinglyLinkedList:");
		System.out.println(linkedList);

	}

}

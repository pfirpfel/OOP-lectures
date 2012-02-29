package ch.fhnw.mkuenzli.DataStructures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Generic SinglyLinkedList
 * @author Michael Kuenzli, <michael.kuenzli@students.fhnw.ch>
 *
 * @param <T> type of elements
 */
public class SinglyLinkedList<T> implements Iterable<T> {
	private SinglyLinkedElement head = null;
	
	public SinglyLinkedList() {
	}
	
	/**
	 * @return boolean true, when list is empty
	 */
	public boolean isEmpty() {
		return (head == null);
	}
	
	/**
	 * Adds an element at a specific position to the list.
	 * @param position integer, position where the element gets inserted, starting with zero
	 * @param o element to add
	 * @throws ListError thrown when adding element is not possible
	 */
	public void add(int position, T o) throws ListError {
		SinglyLinkedElement newElement = new SinglyLinkedElement(o);
		if(position == 0){
			newElement.next = head;
			head = newElement;
			
		} else {
			SinglyLinkedElement cursor = head;
			for(int i = 1; i < position; i++) {
				if(cursor == null) throw new ListError("Position too large");
				cursor = cursor.next;
			}
			if(cursor == null) throw new ListError("Position too large");
			newElement.next = cursor.next;
			cursor.next = newElement;
		}
	}
	
	/**
	 * Adds an element to the last position of the list.
	 * @param o element to add
	 */
	public void add(T o) {
		SinglyLinkedElement newElement = new SinglyLinkedElement(o);
		SinglyLinkedElement cursor = head;
		if(cursor == null){
			head = newElement;
		} else {
			while(cursor.next != null){
				cursor = cursor.next;
			}
			cursor.next = newElement;
		}
	}
	
	/**
	 * Returns the element from the specified position.
	 * @param position integer, position of the element, starting with zero
	 * @return element
	 * @throws ListError thrown when element can't be retrieved
	 */
	public T get(int position) throws ListError{
		SinglyLinkedElement cursor = head;
		for(int i = 1; i <= position; i++){
			if(cursor.next == null) throw new ListError("Position too large");
			cursor = cursor.next;			
		}
		return cursor.getData();
	}
	
	/**
	 * Removes an element at the specified position.
	 * @param position integer, position of the element, starting with zero
	 * @return removed element
	 * @throws ListError thrown when element can't be deleted
	 */
	public T remove(int position) throws ListError{
		T removedObject = null;
		if(position == 0) {
			removedObject = head.getData();
			head = head.next;
		} else {
			SinglyLinkedElement cursor = head;
			for(int i = 1; i < position; i++){
				if(cursor.next == null) throw new ListError("Position too large");
				cursor = cursor.next;	
			}
			removedObject = cursor.next.getData();
			cursor.next = cursor.next.next;
		}
		return removedObject;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for(T element: this) {
			str.append(element.toString()).append("\n");
		}
		return str.toString();
	}
	
	/**
	 * Returns list as a String Array.
	 * If the elements aren't Strings, their toString-output will be used.
	 * @return String Array of the list
	 */
	public String[] toStringArray() {
		ArrayList<String> list = new ArrayList<String>(); //TODO nice solution without other complex data structures?
		for(T element: this){
			list.add(element.toString());
		}
		return (String[])list.toArray();
	}
	
	/**
	 * Transforms a String Array into a SinglyLinkedList<String>
	 * @param inputArray String[] which should be transformed
	 * @return SinglyLinkedList<String> transformed String Array
	 */
	public static SinglyLinkedList<String> parseStringArray(String[] inputArray) {
		SinglyLinkedList<String> list = new SinglyLinkedList<String>();
		for(String element: inputArray){
			list.add(element);
		}
		return list;
	}

	@Override
	public Iterator<T> iterator() {
		return new SinglyLinkedListIterator(head);
	}
	
	public class SinglyLinkedListIterator implements Iterator<T> {
		private SinglyLinkedElement head = null;
		private SinglyLinkedElement cursor = null;
		private int currentPosition = 0; //needed for deletion, allows duplicate elements
		private boolean allowDelete = false; // deleting allowed after first call of next()-method
		
		public SinglyLinkedListIterator(SinglyLinkedElement head){
			this.cursor = head;
			this.head = head;
		}

		@Override
		public boolean hasNext() {
			return (cursor != null); // false if calling next-method would cause an exception
		}

		@Override
		public T next() {
			SinglyLinkedElement before = cursor;			
			if(cursor == null) throw new NoSuchElementException();
			cursor = cursor.next;
			currentPosition++;
			allowDelete = true; // reset delete flag
			return before.getData();
		}
		
		@Override
		public void remove() {
			if(!allowDelete) throw new IllegalStateException(
				"Next method has not yet been called, or the remove method has already been called after the last call to the next method."
			);
			
			if(currentPosition == 0){
				head = head.next;
			} else {
				SinglyLinkedElement deleteCursor = head;
				for(int i = 0; i < currentPosition; i++){
					deleteCursor = deleteCursor.next;
				}
				deleteCursor.next = deleteCursor.next.next;
			}
			allowDelete = false; // allow deleting only once per next-cycle
		}
		
	}
		
	private class SinglyLinkedElement {
		private T data;
		protected SinglyLinkedElement next = null;
		
		protected SinglyLinkedElement(T data) {
			this.data = data;
		}
		public T getData() {
			return data;
		}
		
		@Override
		public String toString() {
			return data.toString();
		}
	}
	
	static class ListError extends Exception {
		private static final long serialVersionUID = -2955181984427923203L;

		public ListError (String message) {
			super(message);
		}
	}
}

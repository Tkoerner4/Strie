// TO DO: add your implementation and JavaDocs.

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * class that is a simple linked list , acts as a queue with first in first out functionality.
 * @param <T> generic type to be used with class
 */
class SimpleList<T> implements Iterable<T>
{
	
	// A linked list class 
	// You decide the internal attributes and node structure
	// But they should all be private

	// Class for the internal node: not visible to the outside 
	// Do not change the provided fields: otherwise the provided iterator() will not work
	
	//DO NOT CHANGE THIS CLASS EXCEPT TO ADD JAVADOCS
	/**
	 * amount of nodes in list.
	 */
	private int num = 0;
	/**
	 * node def class.
	 * @param <T> type of data to be held in node.
	 */
	private class Node<T> {
		/**
		 * value in node.
		 */
		T value;		// data to store
		/**
		 * pointer to sequential node.
		 */
		Node<T> next;	// link to the next node

		/**
		 * public constructor for node.
		 * @param value data inside node.
		 */
		public Node(T value){
			this.value = value;
		}
	}

	/**
	 * head of node list, next goes to tail.
	 */
	private Node<T> head;  	// first node, not dummy
	/**
	 * end of list, oppisite side of head, tail has no next pointer.
	 */
	private Node<T> tail;  	// last node, not dummy

	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!

	/**
	 * creates empty list, all vars are already initalized and null.
 	 */
	public SimpleList(){

		// Constructor
		// Initialize an empty list
		
		// O(1)
	}

	/**
	 * gets num of nodes in list.
	 * @return num of nodes in list.
	 */
	public int size(){
		//Return the number of nodes in list
		//O(1)
		return this.num;
	}

	/**WORKING TESTED.
	 * adds a list to the tail end of list.
	 * @param value value to be placed in new node to be added.
	 * @throws IllegalArgumentException if value give is null.
	 */
	public void addLast(T value) throws IllegalArgumentException
	{
		// Add a new node to the tail of the linked list to hold value
		if(value.equals(null))
		{
			throw new IllegalArgumentException("Cannot add null value!");
		}
		Node newNode = new Node<T>(value);
		if(num == 0)// if list empty
		{
			this.head = newNode;
			this.tail= newNode;
			num++;
			return;
		}
		// O(1) 

		this.tail.next= newNode; // sets curr tail next pointer to new node
		this.tail= newNode; // updates lists tail pointer to newly added node
		this.num++;// increment size of list
		// Note: The value to be added cannot be null.
		// - Throw IllegalArgumentException if value is null. 
		// - Use this _exact_ error message for the exception 
		//  (quotes are not part of the message):
		//    "Cannot add null value!"
	}

	/**WORKING TESTED.
	 * removes and returns head node val.
	 * @return val inside of former head node.
	 */
	public T removeFirst(){
		if (num == 0)
		{
			return null;
		}
		T returnVal = (T) this.head.value;// stores value of head node
		//System.out.println("T return_val = (T) this.head.value;");
		this.head = this.head.next;// updates head pointer to null

		//System.out.println("this.head.next = this.head;");
		this.num--;
		//System.out.println("this.num--;");
		return returnVal;

		// Remove the node from the head of the linked list 
		// and return the value from the node.
		// If linked list is empty, return null.
		
		// O(1)

	}

	/**
	 * checks if value is in list.
	 * @param value valye to be checked in each node for.
	 * @return if value in list or not.
	 */
	private boolean contains(T value)
	{
		if(num==0)
		{
			return false;
		}
		Node parse=this.head;
		while(parse!=null)
		{
			if(parse.value.equals(value))
			{
				return true;
			}
			parse=parse.next;
		}
		return false;
	}
	/** working removal on middle tail and head.
	 * REMOVES node with desired value from list.
	 * @param value value with associated node to be removed.
	 * @return true or false value removed or not.
	 */
	public boolean remove(T value){
		// Given a value, remove the first occurrence of that value
		//if list empty
		if(!this.contains(value))
		{
			return false;
		}
		boolean deletion = false;
		if(this.num==0)
		{
			return deletion;
		}


		if(this.head.value.equals(value))
		{
			this.removeFirst();// already decrements list size

			deletion = true;
			return deletion;
		}

		Node parse = this.head;

		for(int i =1 ; i < this.num;i++)
		{
			if(parse.next.value.equals(value)) {
				parse.next = parse.next.next;
				this.num--;
				return true;
			}
			System.out.println(i);



			parse=parse.next;


		}

		// Return true if value removed
		// Return false if value not present
		
		// O(N) where N is the number of nodes in list
		
		// NOTE: remember to use .equals() for comparison
		//if none removed then V V
		return false; //default return; change or remove as needed
	}

	/**
	 * returns desired value.
	 * @param value value to be searched for.
	 * @return value found in hash table, if existing, null if not existing.
	 */
	public T get(T value)
	{
		if(num==0)
		{// if list empty
			return null;
		}
		// special cases for head and tail
		if(this.head.value.equals(value))
		{
			return this.head.value;
		}
		if(this.tail.value.equals(value))
		{
			return this.tail.value;
		}
		Node parse = this.head;
		for(int i = 0 ; i< this.num;i++)
		{
			if(parse.value.equals(value))
			{
				return (T) parse.value;
			}
			parse = parse.next;
		}


		return null; //default return; change or remove as needed

	}

	// Provided: do not change but you will need to add JavaDoc

	/**
	 * iterates thru list.
	 * @return iterator object to go thru the list.
	 */
	public Iterator<T> iterator(){
		// return a basic iterator of T
		// Note that this method uses your linked list!
		// so if the iterator doesn't work, that's on you...

		return new Iterator<T>(){
			private Node<T> current = head;
		
			public boolean hasNext(){			
				return (current!=null);
			}
		
			public T next(){
				if(!hasNext()) {
					throw new NoSuchElementException();
				}
				T toReturn = current.value;
				current = current.next;
				return toReturn;
			}
		};
	}


	
	// Provided: do not change but you will need to add JavaDoc

	/**
	 * parses thru string to get string representation of list.
	 * @return string representation of list.
	 */
	@Override
	public String toString(){
		// list all values from head to tail
		StringBuilder s = new StringBuilder("[");
		Node<T> current = head;
		String prefix="";
		while (current!=null){
			s.append(prefix);
			s.append(current.value);
			prefix=",";
			current = current.next;
		}
		s.append("]");
		return s.toString();

	}
	
	
	//----------------------------------------------------
	// example testing code... make sure you pass all ...
	// and edit this as much as you want!

	/**
	 * main testing method for simplelist.
	 * @param args args to feed into program when using.
	 */
	public static void main(String[] args){
		//These are _sample_ tests. If you're seeing all the "yays" that's
		//an excellend first step! But it does NOT guarantee your code is 100%
		//working... You may edit this as much as you want, so you can add
		//own tests here, modify these tests, or whatever you need!



		//add, get
		SimpleList<Integer> nums = new SimpleList<Integer>();
		nums.addLast(11);
		nums.addLast(5);
		nums.addLast(20);



		System.out.println(nums);


		//System.out.println(nums.remove(5));

		//System.out.println();



		if (nums.size()==3 && nums.get(5).equals(5) &&
			nums.get(2) == null){
			System.out.println("Yay 1");
		}


		//uncomment to check the list details
		//System.out.println(nums);
		System.out.println(nums);
		//remove
		if (!nums.remove(16) && nums.remove(11) &&
			nums.get(11)==null && nums.removeFirst().equals(20)){
			System.out.println("Yay 2");			
		}


		//toString and iterator
		nums.addLast(31);
		nums.addLast(10);
		String expectedString = "[5,31,10]";
		Iterator iter = nums.iterator();
		if (nums.toString().equals(expectedString) && iter.hasNext() && 
			iter.next().equals(5) && iter.hasNext() && iter.next().equals(31)){
			System.out.println("Yay 3");						
		}

		
	}

}
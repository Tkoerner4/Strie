// TO DO: add your implementation and JavaDocs.

/**
 * class for threetenhashset object.
 * @param <T> generic object type to be used with class.
 */
class ThreeTenHashSet<T>
{
	// This is the class that you need to write to implement a set 
	// using a hash table with _separate chaining_.

	// Underlying storage table -- you MUST use this for credit!
	// Do NOT change the name or type
	/**
	 * table for storing objects hashed.
	 */
	private SimpleList<T>[] table;
	/**
	 * private var for amount of objects currently stored in hash table.
	 */
	private int size=0;


	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!


	/**
	 * constructor object for threetenhashset object.
	 * @param initLength length of threetenhashset to create.
	 */
	@SuppressWarnings("unchecked")
	public ThreeTenHashSet(int initLength){

		this.table = new SimpleList[initLength];
		// Create a hash table where the storage is with initLength 
		// Initially the table is empty 
		// You can assume initLength is >= 2
		
		// O(1)
	}

	/**
	 * retrusn total capacity of hash table arr.
	 * @return length of hash table arr.
	 */
	public int capacity() {
		// return the storage length
		// O(1)
		
		return table.length; //default return; change or remove as needed
	}

	/**
	 * gets number of elements stored in hash table arr.
	 * @return number of elements stored in hash table arr.
	 */
	public int size() {
		// return the number of items in the table
		// O(1)
		
		return this.size; //default return; change or remove as needed
	}

	/**
	 * gets hash code to within bounds of hash table.
	 * @param hc hash code given.
	 * @param size size of hash table to bound hash code to
	 * @return a hash code that will fit within hash table limits.
	 */
	private int bound(int hc, int size)
	{
		if(hc<0)
		{// if out of bounds on negative sie
			hc=Math.abs(hc);
		}
		if(hc>=size)
		{// if the hc is still too high then bound with modulo
			hc=hc% size;
		}
		return hc;// returns hash code within bounds
	}

	/**
	 * adds a value to the hash table.
	 * @param value value to be added.
	 * @return if additon was sucessfull or not, null or duplicate values cannot be added.
	 */
	public boolean add(T value) {
		if(value.equals(null))
		{
			return false;// null val given to add
		}
		if(this.contains(value))
		{
			return false;// value added already
		}// since both falses cases already taken care of,
		int load = this.size/table.length;
		if(load>2) {
			this.rehash(this.capacity() * 2);
		}

		int hc = value.hashCode();// otherwise if can be added
		hc=bound(hc, table.length);
		if(table[hc]==null)
		{
			SimpleList<T> index = new SimpleList<>();
			table[hc]= index;
		}


		table[hc].addLast(value);
		this.size++;

		return true;
		// Add an item to the set 
		// - return true if you successfully add value; 
		// - return false if the value can not be added
		//    (i.e. the value already exists or is null)

		// NOTES:
		// - Always add value to the tail of the chain.
		// - If load of table is at or above 2.0, rehash() to double the length.
				
		// Time complexity not considering rehash(): 
		// O(N) worst case, where N is the number of values in table
		// O(N/M) average case where N is the number of values in table and M is the table length
		

	}

	/**
	 * removes value from hash table.
	 * @param value value to be removed.
	 * @return if removal was sucessful or not.
	 */
	public boolean remove(T value) {
		if(this.contains(value)==false)
		{
			return false;
		}
		for(int i = 0; i < table.length;i++)
		{
			if(table[i]!=null)// check if entry has a chain
			{// if selected chain has desired value
				if(table[i].get(value)!=null)
				{
					table[i].remove(value);// remove from table
					if(table[i].size()==0)// if element removed is last one there
					{
						table[i]=null;
					}
					this.size--;
					return true;
				}

			}
		}

		// Removes a value from the set
		// - return true if you remove the item
		// - return false if the item is not present
		
		// O(N) worst case, where N is the number of values in table
		// O(N/M) average case where N is the number of values in table and M is the table length

		return false; //default return; change or remove as needed
	}

	/**
	 * returns true or not if value is already in hash table.
	 * @param value value to be checked for.
	 * @return true if value in hash tablle, false if not.
	 */
	public boolean contains(T value)
	{
		if(size==0)
		{
			return false;
		}
		// Return true if value is in the set
		// Return false otherwise
		for(int i = 0;i < table.length;i++)
		{
			if(table[i]!=null)
			{
				if(table[i].get(value)!=null)
				{
					return true;
				}

			}
		}

		// O(N) worst case, where N is the number of values in table
		// O(N/M) average case where N is the number of values in table and M is the table length

		return false; //default return; change or remove as needed
	}

	/**
	 * gets value from hash table, if it exists.
	 * @param value value to be checked for.
	 * @return value from table if it exists.
	 */
	public T get(T value) {
		if(this.contains(value)==false)
		{
			return null;
		}
		T returnVal;
		// since table contains value
		for(int i = 0; i < table.length;i++)
		{
			if(table[i]==null)
			{
				continue;
			}
			if(table[i].get(value)!=null)
			{
				returnVal = table[i].get(value);
				return returnVal;
			}
		}

		// Return null if value is not present in set.
		// Return the item _FROM THE HASH TABLE_ if it was found
		//  - do not return the incoming parameter, while "equivalent" they
		// may not be the same)
		
		// O(N) worst case, where N is the number of values in table
		// O(N/M) average case where N is the number of values in table and M is the table length
		
		
		// NOTE: HashMap uses a ThreeTenHashSet of Pair<Key,Value>. In that class,
		// this method is used in the following way:
		//
		// - HashMap passes in a Pair<Key,Value> to search for
		// - The key is "real", the value may be a "dummy" or null
		// - The Pair<Key,--> passed in and the Pair<Key,Value> in the hash table
		//   will match with .equals() -- see equals() in the Pair class
		// - If this method finds the Pair<Key,-->, the returned value must be the 
		//   **actual** hash table entry, which includes both matching key and a valid
		//   non-null value.  
		//
		// Because of this, get() in this class need to be careful too, and it *must*
		// return the value from the hash table and not the parameter.

		
		return null; //default return; change or remove as needed
	}

	/**
	 * rehashes the table to a new size.
	 * @param newCapacity the size of the new table.
	 * @return if the rehash was sucessful or not.
	 */
	@SuppressWarnings("unchecked")
	public boolean rehash(int newCapacity) {
		if(newCapacity<= table.length)
		{
			return false;
		}
		SimpleList<T>[] originalTableCopy = this.table;// copy of old table
		SimpleList<T>[] newTable = new SimpleList[newCapacity];// new table ,empty w new length
		this.table= newTable;// set pointer of threetenhashset table to new table
		// copy over vals

		T val;
		for(int i=0; i < originalTableCopy.length;i++)// go thru the old hash table
		{
			int x = newTable.length;
			while(originalTableCopy[i].size()>0)// if the list at the element being looked at still has elements in it
			{
				val = originalTableCopy[i].removeFirst();// remove the elements in list at index
				this.add(val);// add the value from the old table to new one

			}

		}

		for(int j = 0; j < table.length;j++)// go thru list
		{
			if(table[j]==null)// if null at entry
			{
				continue;// skip
			}
			if(table[j].size()==0)// if empty list at entry
			{
				table[j]=null;// set entry to null
			}
		}


		return true;

		// Rehash to table size newCapacity
		// - If the new capacity is no greater than the current capacity,
		//   do not rehash and return false;
		// - otherwise, return true after resizing
		
		// You can assume the newCapacity is always < Integer.MAX_VALUE - 50.
		
		// O(N+M) where N is the number of values in table and M is the table size
		// Hint: Take advantage of the iterator of SimpleList to meet big-O requirements.
		

	}
	
	// Provided: do not change but you will need to add JavaDoc
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("ThreeTenHashSet (non-empty entries):\n");
		for(int i = 0; i < table.length; i++) {
			if(table[i] != null) {
				s.append(i);
				s.append(" :");
				s.append(table[i]);
				s.append("\n");
			}
		}
		return s.toString().trim();
	}
	
	// Provided: do not change but you will need to add JavaDoc

	/**
	 * gets string represenation of object.
	 * @return a string representation of hashset.
	 */
	public String toStringDebug() {
		StringBuilder s = new StringBuilder("ThreeTenHashSet (all entries):\n");
		for(int i = 0; i < table.length; i++) {
			s.append(i);
			s.append(" :");
			s.append(table[i]);
			s.append("\n");
		}
		return s.toString().trim();
	}

	// Provided: do not change but you will need to add JavaDoc

	/**
	 * provides a simplelist of all the values without their associated keys in the list.
	 * @return a simplelist of all values from the hash set
	 */
	public SimpleList<T> allValues(){
		// return all items in set as a list
		SimpleList<T> all = new SimpleList<>();
		for(int i = 0; i < table.length; i++) {
			if (table[i]!=null){
				for (T value: table[i])
					all.addLast(value);
			}
		}
		return all;
	}

	//----------------------------------------------------
	// example testing code... make sure you pass all ...
	// and edit this as much as you want!
	//----------------------------------------------------

	/**
	 * testing method for threetenhashset class.
	 * @param args args to be used with testing
	 */
	public static void main(String[] args) {

		// Again, these are limited sample tests.  Showing all "yays" 
		// does NOT guarantee your code is 100%. 
		// You must do more testing.
	
		ThreeTenHashSet<String> names = new ThreeTenHashSet<>(5);



		//basic table w/o collision: add / size / capacity
		if(names.add("Alice") && names.add("Bob") && !names.add("Alice") 
			&& names.size() == 2 && names.capacity() == 5) 	{
			System.out.println("Yay 1");
		}


		//remove / contains / get
		if(!names.remove("Alex") && names.remove("Bob") && names.contains("Alice") 
			&& !names.contains("Bob") && names.get("Bob")==null) {
			System.out.println("Yay 2");
		}

		//System.out.println(names.toString());
		//System.out.println("-----------------------");
		ThreeTenHashSet<Integer> nums = new ThreeTenHashSet<>(5);



		//table with collision

		for(int i = 0; i <7 ; i++) {
			nums.add(i);
		}
		//System.out.println(nums);
		String expectedString = 
			"ThreeTenHashSet (non-empty entries):\n0 :[0,5]\n1 :[1,6]\n2 :[2]\n3 :[3]\n4 :[4]";
		String allValueString = "[0,5,1,6,2,3,4]";
		if (nums.size()==7 && nums.toString().equals(expectedString)
			&& nums.allValues().toString().equals(allValueString)){
			System.out.println("Yay 3");			
		}
		//System.out.println(nums.toString());


		//rehash
		String rehashedString = 
			"ThreeTenHashSet (non-empty entries):\n0 :[0]\n1 :[1]\n2 :[2]\n3 :[3]\n4 :[4]\n5 :[5]\n6 :[6]";
		if (!nums.rehash(3) && nums.rehash(11) && nums.capacity()==11
			&& nums.toString().equals(rehashedString)){
			System.out.println("Yay 4");					
		}		




		
	}
}
// TO DO: add your implementation and JavaDocs.

/**
 * class for the nodes in the strie.
 */
public class StrieNode{

	// Use a HashMap to hold children nodes.
	// Keys of the map can be any Character while values are the children nodes.
	// Each key in the map leads to a child node of this node.
	/**
	 * hashmap with key var pair.
	 * char is character associated w node, node is node holding it
	 */
	private HashMap<Character, StrieNode> children;

	// Marks the end of a word
	/**
	 * marks within strie where a word would end.
	 */
	private boolean endMarker;
	/**
	 * var for keeping track of how many children there are.
	 */
	private int childCount;
	
	// OPTIONAL boolean flag that you can use.
	// It is completely optional to use this in your implementation.
	// We will NOT test its usage but it is provided for more flexibility.
	// Still, remember to write JavaDoc for it.
	/**
	 * boolean flag.
	 */
	private boolean flag;

	/**
	 * initial length of hash map table.
	 */
	private static final int INIT_MAP_LENGTH = 5; //default length of the hashmap to start

	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED

	/**
	 * node constructor to use in strie structure.
	 */
	public StrieNode(){
		endMarker=false;
		this.flag=false;
		// Constructor
		// Initialize anything that needs initialization
		// HashMap must start with INIT_MAP_LENGTH entries
		children = new HashMap<Character, StrieNode>(INIT_MAP_LENGTH);
		this.childCount =0;
		
		// O(1)
	}

	/**
	 * getter method for number of children.
	 * @return number of children that this strienode has.
	 */
	public int getNumChildren(){
		//report number of children nodes
		//O(1)
		
		return childCount; 	//default return; change or remove as needed
	}

	/**
	 * getter method for children of strienode.
	 * @return childrren hashmap conatining children nodes of this node.
	 */
	public HashMap<Character, StrieNode> getAllChildren()
	{
		// return the storage of all children
		// O(1)
		
		return this.children;//default return; change or remove as needed
	}

	/**
	 * sets a strie node as an end marker, meaning end of a word.
	 */
	public void setEnd(){
		this.endMarker=true;
		// Sets the end marker to indicate this node is the end of a string/word
		// O(1)
	}

	/**
	 * unsets this treinode as being the end of a word.
	 */
	public void unsetEnd(){
		this.endMarker=false;
		// Unsets the end marker
		// O(1)
	}

	/**
	 * checks if this trienode is designated as an end marker.
	 * @return if strienode is an end marker or not.
	 */
	public boolean isEnd(){
		return endMarker;
		// Checks whether the current node is marked as the end of a string/word
		// O(1)
		

	}

	/**
	 * checks to see if char ch is a child of this node.
	 * @param ch char to be checked if is a child of this node.
	 * @return if the char ch is a child of this node, true if yes, false if no.
	 */
	public boolean containsChild(char ch){

		// returns true if node has a child node corresponding to ch;
		// return false otherwise 

		// O(1)
		// You can assume all HashMap operations are O(1)
		
		return children.contains(ch); //default return; change or remove as needed
	}

	/**
	 * gets char ch if it exists in this nodes children storage.
	 * @param ch character ch to check if its a child of this node.
	 * @return the node containing the selected child from the array.
	 */
	public StrieNode getChild(char ch){
		// returns the child node corresponding to ch
		// returns null if no such node

		// O(1)
		// You can assume all HashMap operations are O(1)
		
		return children.getValue(ch); //default return; change or remove as needed
	}

	/**
	 * puts char and its node in the data stroage of this node for its children.
	 * @param ch char ch to be added in to strie, acts as key.
	 * @param node node containing ch which will act as the value.
	 */
	public void putChild(char ch, StrieNode node){
		// set a child node corresponding to ch to node
		// if a node already exists, change the mapping of ch to the specified node

		children.add(ch,node);
		this.childCount++;
		// O(1)
		// You can assume all HashMap operations are O(1) except getKeys() and toString()
	}

	/**
	 * removes node and char ch from children  list of this node.
	 * @param ch char to be removed from child list.
	 * @return if removal is sucessfull or not.
	 */
	public boolean removeChild(char ch){
		// remove child node corresponding to ch if a node is present
		// return true if a child was removed;

		// if no such child node, return false
		boolean result;
		if(!this.children.contains(ch))// if strienode does not have this char in its children list
		{
			return false;
		}
		else {// if its in its child list
			result = children.remove(ch);// attempt removal and store result of attempted removal
			if (result == true) {// if done sucessfully
				this.childCount--;// decrement child counter
				return true;
			} else {
				return false;// if child not removed sucessfully
			}
		}
		// O(1)
		// You can assume all HashMap operations are O(1) except getKeys() and toString()
		

	}

	// Below are methods with the optional flag
	// - implementation of those are optional 
	// - no testing on them in grading
	// Still, remember to write JavaDoc for them.

	/**
	 * sets node as a flagged node.
	 */
	public void setFlag(){
		this.flag=true;
		// set the optional flag to be true
		// O(1)
	}

	/**
	 * sets node as a no longer flagged node.
	 */
	public void unSetFlag(){
		this.flag=false;
		// set the optional flag to be false
		// O(1)
	}

	/**
	 * checks statues of flag.
	 * @return flag boolean var.
	 */
	public boolean checkFlag(){
		// report the status of the optional flag
		// O(1)
		
		return this.flag;//default return; change or remove as needed
	}

	@Override
	public String toString() {
		return "StrieNode{" + "children=" + children + ", is End?=" + endMarker + ", childCount=" + childCount + ", flag=" + flag + '}';
	}
}

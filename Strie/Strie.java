// TO DO: add your implementation and JavaDocs.

import javax.xml.crypto.dsig.SignatureMethod;

/**
 * class for the strie data object to hold strings and their chars in nodes connecting to eachother in a tree form.
 */
public class Strie{
	//----------------------------------------------------
	// NO MORE INSTANCE VARIABLES ALLOWED! >:(y
	//----------------------------------------------------
	
	// Do NOT change the name or type of these variables
	/**
	 * placeholder for root node of strie.
	 */
	private StrieNode root;  // the root of a strie
	/**
	 * amount of words in strie.
	 */
	private int numWords = 0; // number of words represented by the strie
	
	//----------------------------------------------------
	// NO MORE INSTANCE VARIABLES ALLOWED!
	//----------------------------------------------------

	/**
	 * constructor for strie data structure, creates empty root node.
	 */
	public Strie(){
		// Constructor
		// Initialize root to be an empty node; initially no words are in the strie
		root = new StrieNode();
		// O(1)
	}

	/**
	 * returns number of words in strie.
	 * @return number of words in strie.
	 */
	public int numWords(){
		// return number of words in the strie
		return this.numWords;
		// O(1)
	

	}

	/**
	 * getter method for root node of strie.
	 * @return root of strie.
	 */
	public StrieNode getRoot(){
		// return root of the strie

		// O(1)
		
		return this.root;
	
	}

	/**
	 * adds a word into the strie.
	 * @param word word to be added into strie
	 */
	public void insert(String word) {
		char c;
		StrieNode parse = this.root;
		StrieNode s = new StrieNode();
		if (this.contains(word)) {
			return;
		}
		if (this.numWords == 0) {// if empty
			for (int i = 0; i <= word.length()-1; i++)
			{// go thru word

				c = word.charAt(i);

				parse.putChild(c, s);

				parse = s;
				s=new StrieNode();

			}
			//once list cone thru
			parse.setEnd();
			this.numWords++;
			return;
		}
		// if not empty
		char c1;
		for (int i = 0; i < word.length(); i++) {// for every char in word being added

			c = word.charAt(i);// store char
			if(parse.containsChild(c))
			{
				parse=parse.getChild(c);// go to the node
				parse.setFlag();
				continue;
			}
			//if it doesnt have the nod
			parse.putChild(c,s);
			parse=s;
			s=new StrieNode();

		}
		parse.setEnd();
		this.numWords++;
		return;
	}







	/**
	 * checks if strie contains a selected word.
	 * @param word word to be scanned
	 * @return true if word stored, false if not
	 */
	public boolean contains(String word)
	{

		StrieNode parse=this.root;
		if (this.numWords == 0) {
			return false;
		}
		else
		{

			for(int i = 0;i<word.length();i++)
			{
				if(parse.containsChild(word.charAt(i))==false)
				{
					return false;
				}
				else
				{
					parse=parse.getChild(word.charAt(i));
				}
			}
		}
		if(parse.isEnd())
		{
			return true;
		}
		return false;
	}




	/**
	 * removes selected word from strie.
	 * @param word word to be removed from strie
	 * @return if removal was sucessfull or not
	 */
	public boolean remove(String word)
	{

		if(!this.contains(word))
		{
			return false;
		}
		StrieNode parse = this.root;
		for(int i = 0 ;i<word.length();i++)
		{
			if(parse.containsChild(word.charAt(i)))
			{
				if(parse.getChild(word.charAt(i)).checkFlag()==false)
				{
					parse.removeChild(word.charAt(i));
					return true;
				}
				parse=parse.getChild(word.charAt(i));
			}
		}
		// Removes the given word from Strie.
		
		// If word is not present in strie, return false;
		// Otherwise, remove word and return true.
		
		// Hint: Consider using recursion in your implementation.
		// Hint: You can define recursive helper functions.
		
		// Note: While there are **no Big-O restrictions** on this
		// method, it can be done in O(n) where n is the number
		// of characters in word


		
		return true;//default return; change or remove as needed


	}

	/**
	 * recursive method for level order traversal.
	 * @param node node to be recrusivley searched
	 * @param data list of chars gathered so far
	 * @return a full list of chars once tree has been fully traversed
	 */
	private SimpleList<Character> numNodes(StrieNode node,SimpleList<Character> data)
	{//breadth first
		//list of chars associated with children nodes we need to go thru V
		SimpleList<Character> children = node.getAllChildren().getKeys();
		while(children.size()>0)
		{
			char c = children.removeFirst();
			data.addLast(c);
			numNodes(node.getChild(c),data);
		}
		return data;
	}

	/**
	 * traverses the tree in level order.
	 * @return a list of chars , in level order of the tree
	 */
	public String levelOrderTraversal(){
		//BREADTH FIRST
		// no recursion
		//simplelist of things
		// special case, what if tree is empty?
		// - If a Strie has no words, return an empty string.
		if(this.numWords==0)
		{
			return "";
		}
		if(root==null)
		{
			return "";
		}
		// first children added is the roots children
		String chars="";
		StrieNode parse;
		int childSize;
		SimpleList<StrieNode> nodes = new SimpleList<StrieNode>();
		nodes.addLast(root);
		SimpleList<Character> children;
		char c;
		while(nodes.size()>0)// while children remaining to be handled
		{
			parse = nodes.removeFirst();// remove first child
			children = parse.getAllChildren().getKeys();// gather its children keys
			childSize=children.size();// save size of children list
			for(int i = 0 ;i< childSize;i++)// iterate once for each child of parse node
			{
				c=children.removeFirst();// grab char of first child of parse
				chars=chars+" "+c;// add to output string
				nodes.addLast(parse.getChild(c));// add node corresponding with char to list to check for children
			}
		}



		return chars.substring(1);






		// 	   process first one (loop through that first element's children) and combine all the keys to form the string
		//     add value into the arrayList/queue
		//     remove/pop first from queue (because you are done with firs tone)

		// return finalResult;


		// Perform a Breadth First Traversal of your Strie tree
		// and return a string of all characters encountered in the traversal.

		// - A single space should be padded between characters.
		// - For multiple children of a single node, use the order of characters in 
		// getKeys() of the hash map to determine the traverse order.
		//
		// Check main() for examples.
		
		// Hint: you can use SimpleList to implement a queue easily.
		
		// Note: While there are **no Big-O restrictions** on this
		// method, level order traversals are traditionally O(n)
		// where n is the number of nodes in the tree. This may not
		// be the case here due to the hash table implementation
		// of children.
		

	}



	/**
	 * gets a simplelist of all strings inserted into the strie.
	 * @return a list of strings in the strie
	 */
	public SimpleList<String> getStrieWords(){
		//BREADTH FIRST
		// no recursion
		//simplelist of things
		// special case, what if tree is empty?
		// - If a Strie has no words, return an empty string.
		SimpleList<String> words = new SimpleList<>();

		if(this.numWords==0)
		{
			return words;
		}
		if(root==null)
		{
			return words;
		}
		// first children added is the roots children
		SimpleList<Character> letters = new SimpleList<> ();

		StrieNode parse = root;
		String partOf="";
		String temp="";
		char c;
		letters = numNodes(root,letters);//gather all the chars by traversing recursviely
		int size = letters.size();
		for(int i = 0; i< size;i++)// go thru all chars
		{
			c= letters.removeFirst();// char being looked at
			if((parse==null)||(parse.getNumChildren()==0))// if parse node is null or no children, used to prevent nullpointerexception
			{

				partOf="";//reset all and continue to next iteration
				temp="";
				continue;
			}
			if(parse.containsChild(c)==false)// if the char being looked at is not in parses children, go back to root
			{
				parse=root;
				partOf="";
			}
			if(parse.getChild(c).checkFlag()==true)// if part of another word
			{
				partOf=partOf+c;
			}
			else
			{
				temp=temp+c;// if not part of another word add to temp
			}
			if(parse.getChild(c).isEnd())
			{// if char is end of a word

				if(parse.getChild(c).checkFlag()==false)// if end of word and not part of another word
				{
					words.addLast(partOf+temp);
					temp="";// remove the temp part, focus on part thats part of other words


					continue;
				}
				words.addLast(partOf);// add to words list

				parse=parse.getChild(c);


			}
			else
			{
				parse = parse.getChild(c);// next node
			}
		}
		//letters = recur(root,letters);

		return words;
		// Return all words currently stored in Strie.
		// If Strie has no words, return null.
		//
		// When there are multiple characters to continue from one node,
		// use the order of characters in getKeys() of the hash map to 
		// determine the traverse order.
		//
		// Also, prefix-words come before words that they are prefixes of. 
		// For example, 'bar' comes before 'barn'. 
		//
		// Check main() for examples.
		
		// Hint: Consider using recursion in your implementation.
		// Hint: You can define recursive helper functions.
		
		// Note: There are **no Big-O restrictions** on this
		// method, but it **can** be done in the same runtime as
		// a walk of the tree.
		
		//return null;//default return; change or remove as needed
	}




	//----------------------------------------------------
	// example testing code... make sure you pass all ...
	// and edit this as much as you want!
	//----------------------------------------------------

	/**
	 * testing method for strie.
	 * @param args args to tested with
	 */
	public static void main(String[] args)
	{
		Strie myStrie = new Strie();
		myStrie.insert("add");
		myStrie.insert("and");
		System.out.println(myStrie.getStrieWords());

		System.out.println(myStrie.numWords);
		//System.out.println(myStrie.root.getChild('b').getAllChildren());


		/*
		System.out.println(myStrie.contains("barn"));


		if(myStrie.numWords()==0 && myStrie.getRoot().getNumChildren() == 0) {
			System.out.println("Yay 1");
		}
			
		myStrie.insert("a");
		StrieNode myRoot = myStrie.getRoot();
		if(myStrie.numWords()==1) {
			if (myRoot.getChild('a').getNumChildren() == 0) {
				if (myRoot.getChild('a').isEnd()) {
					if (myRoot.containsChild('a')) {
						System.out.println("Yay 2");
					}
				}
			}
		}




		myStrie.insert("a");
		myStrie.insert("bat");
		myStrie.insert("bar");
		myStrie.insert("barn");
		myStrie.insert("cat");

		System.out.println("removal");
		//System.out.println(myStrie.remove("bar"));
		//System.out.println(myStrie.remove("bare"));

		System.out.println(myStrie.root.getChild('c').getChild('a').isEnd());
		System.out.println(myStrie.numWords);
		System.out.println(myStrie.contains("ca"));
		System.out.println(myStrie.contains("c"));

		System.out.println(myStrie.contains("cat"));
		System.out.println(myStrie.contains("bar"));





		System.out.println(myStrie.numWords);
 
		String res = myStrie.levelOrderTraversal();
		String actualOut = "a b c a a t r t n";
		System.out.println(res);
		if(res.equals(actualOut))
			System.out.println("Yay 4");


		SimpleList<String> yourWords = myStrie.getStrieWords();
		String display = "[a,bat,bar,barn,cat]";
		if (yourWords.size()==5 && yourWords.toString().equals(display))
			System.out.println("Yay 5");
		System.out.println(yourWords.toString());
		System.out.println(myStrie.numWords);


		if(myStrie.remove("cat") && !myStrie.contains("cat"))
			System.out.println("Yay 6");
		System.out.println(myStrie.numWords);


		 */

	}
}

import java.util.*;

import java.io.*;

/**
 * A general tree that is can be used to keep 
 * track of parent child relationships between data.
 * 
 * Nodes in a general tree can have multiple children.
 * 
 * This tree is built by reading lines from a file.
 * 
 * To help with program 4, this class also has a method
 * that returns a stack of String (names) that are the
 * ancestors of the specified node.  See getAncestorStack.
 */
public class GenealogyTree{

	public static final String LOAD_GENEALOGY_ERROR_MESSAGE = "Error loading genealogy from file";

	// The root node of the GenealogyTree
	private TreeNode<String> root;

	public GenealogyTree(){
		root = null;
	}

	// Get the root node of the GenealogyTree
	public TreeNode<String> getRoot(){
		return root;
	}
	
	/**
	 * return a Stack that contains the ancestors of the node with matching data
	 * The root is at the bottom of the stack and the matching node is at the top
	 * and the stack contains all ancestors of the matching data node.
	 *
	 * THIS METHOD CALLS A COMPANION (HELPER) METHOD that is recursive
	 * 
	 * If the top of the stack is not target,
	 * return an empty stack indicating that the target
	 * was not found.
	 *
	 * @param target the data you are trying to find
	 * @return a stack with the target data node at top and the root at the bottom,
	 * or return an empty stack if the target was not found.
	 */
	public StackADT<String> getAncestorStack(String target) {
		// DO NOT CHANGE THIS METHOD
		StackADT<String> stack = new Stack<String>(); 
		stack = getAncestorStack(stack,root,target);  
		if (stack.peek().equals(target)) {
			return stack;
		}	
		return new Stack<String>(); // empty stack
	}

	/**
	 * Perform a pre-order traversal of the current node (root of Genealogy Tree)
	 * and return a Stack that contains the ancestors of the target node.
	 * 
	 * The root is at the bottom of the stack and the matching node is at the top
	 * and the stack contains all and only ancestors of the matching data node.
	 * 
	 * NOTE: If target data is not found, the stack returned does not have
	 * target at the top.  Be sure to check this in the calling method.
	 *
	 * @param target the data you are trying to find
	 * @return a stack with the target data node at top and the root at the bottom 
	 * or an empty stack if target is not found
	 */
	private StackADT<String> getAncestorStack(StackADT<String> st, TreeNode<String> curr, String target) {	
		//**If the tree is not empty, then search for the target's ancestors, put them in a stack.**		
		if (curr != null) {
			st.push(curr.getData()); //Push a current node from tree to top of stack. peek() to compare it to target. 

			if (st.peek().equals(target)) { 
				return st;			 //If target is found in tree and returned to top of stack, return stack.
			}
			
			//**Else, keep searching down the path of the potential target's ancestors.**
			ListADT<TreeNode<String>> children = curr.getChildren();
			for (TreeNode<String> child : children) {  //Finding valid ancestor path that leads up to the target node.
				StackADT<String> ancestorPath = getAncestorStack(st, child, target);
				/*
				 * After a stack is returned to a recursive call for one of the children in the for-each loop, 
				 * determine if that stack contains the target node. If it does then it's the ancestor path, else
				 * pop each node from that incorrect path until we get back to root. Continue pre-order search in 
				 * the other subtree.
				 */
				if (ancestorPath.peek().equals(target)) {				
					return ancestorPath; 				//Return path of ancestors from target, if target found.
				}
				ancestorPath.pop();			  //Before continuing search, pop invalid nodes from returned stack. 
			}
			return st; 	//When we reach end of tree (the leaves), return current stack back to recursive caller.
		}
		
		return st; 		//Return stack if done processing. Returns empty stack if root == null.
	}

	/**
	 * Load a tree from file.
	 *
	 * If there are IOException when loading the tree from file, 
	 * print LOAD_GENEALOGY_ERROR_MESSAGE 
	 * and throw the IOException.
	 *
	 * All the lines in the file "parent -> children" relationships.
	 *
	 * The relationships are listed in a pre-order traversal order starting from root.
	 *
	 * For example, for the following tree:
	 *     a
	 *  /  |  \
	 *  b  c  d
	 *  |     | \
	 *  e     f g
	 *
	 * The input file must follow this form:
	 * a -> b
	 * a -> c
	 * a -> d
	 * b -> e
	 * d -> f
	 * d -> g
	 *
	 * Note: all lines of a file must contain a relationship to be a valid format.
	 * Blank lines will cause exceptions.
	 * 
	 * Pseudocode for the work done by this method:
	 * 
	 * 	// Create a queue, add each new node to the queue
	 *	// create a Scanner connect to the file
	 *  // for each line of the file
	 *  	// read the line
	 *      // parse the line into parent and child
	 *
	 *      // if root is null
	 *      	// create the root node
	 *          // add the root's first child
	 *          // add the root and child to the queue
	 *
	 *      // else Construct other TreeNode
	 *      	// while queue is not empty
	 *          	// get next node from queue without removing it from queue
	 *              // if "front" node matches the parent
	 *              	// create a TreeNode for the child
	 *                  // add the child node to the current "front" node (its parent)
	 *                  // add the child to the queue
	 *                  // break out of the loop
	 *              	// else dequeue the front node 
	 *
	 *  // catch IO exceptions, display error message and rethrow the exception
	 *	// close the file scanner
	 * 
	 */
	public void buildFromFile(String filename) throws IOException {
		QueueADT<TreeNode<String>> queueOfNodes = new Queue<TreeNode<String>>(); //Queue gets node references from file.
		File inputFile = null;												     //File object made from the filename.
		Scanner scnr = null;												  	 //Scanner to read from the file.
		
		try {
			inputFile = new File(filename);
			scnr = new Scanner(inputFile);
			
			while (scnr.hasNextLine()) {		//Read each line of the file for a parent-child relationship.
				String fileLine = scnr.nextLine().trim();
				//**Check the file lines for valid format before making nodes to add to nodeQueue.**
				if (fileLine.isEmpty()) { 
					throw new IOException();	//If there are any blank lines, skip them, and read next line of file.
				}
				if (!fileLine.contains("->")) { 
					continue; 	  //If no arrow to indicate parent-child relationship, skip that line, read next line. 
				}
				//**Check if a child follows from ->. If it does, then we can make nodes out of current line.**
				String[] parentAndChild = fileLine.split("->"); 
				ArrayList<String> parentChildList = new ArrayList<String>();
				
				for (String node : parentAndChild) {
					parentChildList.add(node);
				}
				if (parentChildList.size() != 2) { 
					continue;	//If there aren't exactly 2 items in this list, child & parent, then get next line.
				}
				
				//**After passing format checks, construct new nodes out of file String data.**
				String parent = parentChildList.get(0).trim(); //Parent comes before child in the file line.
				String child = parentChildList.get(1).trim();  //Child comes after parent.				
				TreeNode<String> parentNode = new TreeNode<String>(parent);
				TreeNode<String> childNode = new TreeNode<String>(child);
				
				//If the Genealogy Tree is empty, then a new tree is going to be made from this file data.
				if (getRoot() == null) {
					root = parentNode; 					//Creating root from first parent in file.
					root.addChild(childNode);			//Root node now holds the child node from same line.
					queueOfNodes.enqueue(root);			//Enqueue root with child to the nodeQueue.
					queueOfNodes.enqueue(childNode);
				}
				//Else, there is an already existing tree, so continue adding nodes to it.
				else {
					while (!queueOfNodes.isEmpty()) {
						TreeNode<String> nextNodeInQueue = queueOfNodes.element();
						/*
						 * If item returned from the front of the list matches the parent from the file line,
						 * then add the respective child to that parent. Avoids duplicate parents being added into
						 * the same Genealogy Tree.
						 */
						if (nextNodeInQueue.getData().equals(parentNode.getData())) {
							nextNodeInQueue.addChild(childNode); 
							queueOfNodes.enqueue(childNode);		
							break;	//Break inner-loop, go to next line in the file to process data to tree.
						}
						/*
						 * If no match between parentNode and front node in queue, dequeue, then check next front node
						 * for a match until either nodeQueue is empty or we find a parent node to add child to.
						 */
						else {
							queueOfNodes.dequeue(); 
						}
					}
				}
			}
		} catch(IOException e) {  //Display error message and throw IOException if any error occur for file-read.
			System.out.println(LOAD_GENEALOGY_ERROR_MESSAGE);
			throw new IOException();
		} finally {
			scnr.close();
		}
	}            

	/**
	 * Display the contents of the tree in a horizontal tree form.
	 * 
	 * This method is a private recursive helper method for the
	 * printTree() method.
	 * 
	 * It uses the indentation levels to indicate how many 
	 * dots (two per each level) to print for the node
	 * 
	 * @param current node to print
	 * @param indent_count indicates how many dots .. to print for the current level
	 * @param indent_str indicates string of characters precede each print level
	 */
	private void printTreeWithIndent(TreeNode<String> current, int indent_count, String indent_str){
		if (current.getData().equals(root.getData())) { 
			System.out.println(current.getData()); //Print root data 1st without any leading periods ("..").
		}
		else {
			indent_count += 1;					   //Increment indent_count by 1 for every new level of tree reached.		
			
			for (int i = 0; i < indent_count; i++) {
				System.out.print(indent_str);
			}
			System.out.println(current.getData()); //Print data after i-amount of leading periods are printed.
		}
		
		ListADT<TreeNode<String>> children = current.getChildren(); //Getting the children of each node in the tree.
		for (TreeNode<String> node : children) {
			printTreeWithIndent(node, indent_count, indent_str); 
		}
		return;	//Explicitly return after a leaf is processed and printed. 
	}

	/**
	 * Print a tree with indent.
	 *
	 * You should use pre-order to print a tree, which means:
	 * (1) Print the data at current node
	 * (2) For all children nodes of current node,
	 *       recursively use pre-order to print children nodes.
	 *
	 * Each line of output represents a node, use indent (number of spaces before node data)
	 * to indicate which the current node belongs to.
	 * For root node (at level 0), use 0 spaces.
	 * For nodes at other levels, add 2 spaces of indent each level.
	 *
	 * Like for the following tree:
	 *     a
	 *  /  |  \
	 *  b  c  d
	 *  |     | \
	 *  e     f g
	 *
	 * The displayed output should be:
	 * <pre>
	 *  a
	 *  ..b
	 *  ....e
	 *  ..c
	 *  ..d
	 *  ....f
	 *  ....g
	 * </pre>
	 */
	public void printTree() {
		// call recursive helper method
		printTreeWithIndent(root, 0, "..");
	}

}
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
	//TODO PLEASE REVERT THE PUBLIC METHOD HEADER BACK TO PRIVATE. SOLELY FOR TESTING PURPOSES.
	public StackADT<String> getAncestorStack(StackADT<String> st, TreeNode<String> curr, String target) {
		//**If the tree is not empty, then search for the target's ancestors, put them in a stack.**
		System.out.println("In getAncestorStack() method");
		
		if (curr != null) {
			System.out.println(curr.getData());
			st.push(curr.getData()); //Push a current node from tree to top of stack. peek() to compare it to target. 
			
			if (st.peek().equals(target)) { //If target is found in tree and returned to top of stack, return stack.
				return st;
			}
			
			//**Else, keep searching down the path of the potential target's ancestors.**
			
			ListADT<TreeNode<String>> children = curr.getChildren();
			System.out.println(children.isEmpty() + " -children list is empty"); //check.
			//Finding the valid path of ancestor that lead up to the target node.
			for (TreeNode<String> child : children) { 
				StackADT<String> ancestorPath = getAncestorStack(st, child, target);
				/*
				 * After a stack is returned to a recursive call for one of the children in the for-each loop, 
				 * determine if that stack contains the target node. If it does then it's the ancestor path, else
				 * pop each node from that incorrect path until we get back to root. Continue pre-order search in 
				 * the other subtree.
				 */
				if (ancestorPath.peek().equals(target)) {				
					return ancestorPath; //Return path of ancestors from target, if target found.
				}
				//FIXME we get an empty stack, no root node remaining for some reason.
					//Check the concept of adding B...
				
				//FIXME what stack to pop from???
				ancestorPath.pop();
//				st.pop(); //Pop from st so it isn't returning with incorrect nodes from a previous search.
				
				//
				System.out.println(st.isEmpty() + " - st");	
				System.out.println(ancestorPath.isEmpty() + " - ancestorPath");
			}
			return st; //When we reach end of tree (the leaves), return current stack back to recursive caller.
		}
		
		return st; //Return stack if done processing. Returns empty stack if root == null.
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
        // TODO: COMPLETE THIS METHOD

		// Create a queue, add each new node to the queue
			// create a Scanner connect to the file

			// for each line of the file

				// read the line

				// parse the line into parent and child

				// if root is null
		
					// create the root

					// add its first child

					// add the root and child to the queue
		
				// else Construct other TreeNode

					// while queue is not empty

						// get next node from queue without removing it from queue

						// if "front" node matches the parent

							// create a TreeNode for the child

							// add the child node to the current "front" node (its parent)

							// add the child to the queue

							// break out of the loop

						// else dequeue the front node 

			// catch IO exceptions, display error message and rethrow the exception

			// close the file scanner
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
        // TODO: COMPLETE THIS METHOD

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
	 * to indicate which level the current node belongs to.
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

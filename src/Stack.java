import java.util.EmptyStackException;

public class Stack<T> implements StackADT<T> {
	private LinkedList<T> items; //Internal Data Structure of the Stack is a LinkedList.
	
	/**
	 * The top of the Stack corresponds to the front/beginning of the Linked List (the leftmost node of Linked List).
	 * Pushing and popping is only done from the top of the stack.
	 */
	public Stack() {
		items = new LinkedList<T>();
	}
	
	/**
	 * @return True iff there are no nodes with data in the Stack. 
	 */
	@Override
	public boolean isEmpty() {
		return items.isEmpty();
	}
	
	/**
	 * Add the data item to top of the Stack.
	 * 
	 * @throws IllegalArgumentException If data is null.
	 */
	@Override
	public void push(T data) throws IllegalArgumentException {
		if (data == null) {
			throw new IllegalArgumentException();
		}
		items.add(0, data);	//Add at 0 (and only at 0) so items build up on top of the stack.
	}

	/**
	 * Allows viewing of the top item in the Stack.
	 * 
	 * @return The top item of the Stack.
	 */
	@Override
	public T peek() throws EmptyStackException {
		return items.get(0);
	}

	/**
	 * Pop off the item from the top of the Stack.
	 * 
	 * @throws EmptyStackException if Stack is empty.
	 */
	@Override
	public T pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		T data = items.get(0);
		items.remove(0); 
		
		return data;
	}

	/**
	 * Reverses the Stack by reversing the order of nodes in the 
	 * Stack. Returns an empty stack if the original stack is also empty.
	 * 
	 * @return the reversed Stack.
	 */
	@Override
	public StackADT<T> reverse() {
		StackADT<T> reversedStack = new Stack<T>();
		
		//**Copying data from original Linked List to new-reversed Linked List.**
		LinkedListIterator<T> itr = items.iterator(); 
		while (itr.hasNext()) {
			T data = itr.next();
			reversedStack.push(data);	//The push method allows data to be added in reverse order to reversedStack.
		}
		
		return reversedStack;
	}
	
	/**
	 * @return An instance of the LinkedListIterator.
	 */
	public LinkedListIterator<T> iterator() {
		return items.iterator();
	}
}

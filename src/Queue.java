
public class Queue<T> implements QueueADT<T> {
	private LinkedList<T> items; //Internal Data Structure of the Queue is a LinkedList.
	
	/**
	 * The left-most node/beginning of the Linked List corresponds to the Front of the Queue.
	 * The right-most node/end of the Linked List corresponds to the Rear of the Queue.
	 * 
	 * We enqueue from the front of the Queue and dequeue from the rear of the Queue.
	 */
	public Queue() {
		items = new LinkedList<T>();	
	}
	
	/**
	 * @return True iff there are no nodes with data in the Queue. 
	 */
	@Override
	public boolean isEmpty() {
		return items.isEmpty();
	}
	
	/**
	 * Adds a node to the rear of the Queue.
	 * 
	 * @throws IllegalArgumentException if null data is passed as an argument.
	 */
	@Override
	public void enqueue(T data) throws IllegalArgumentException {
		if (data == null) {
			throw new IllegalArgumentException();
		}
		items.add(data); //The linked list implementation already adds nodes to end of itself (rear of Queue).
	}

	/**
	 * Removes a node from the front of the Queue.
	 * 
	 * @throws EmptyQueueException if Queue is empty
	 */
	@Override
	public T dequeue() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException();
		}
		return items.remove(0);
	}

	/**
	 * Get and return the next item in the front of the Queue.
	 * 
	 * @throws EmptyQueueException if Queue is empty
	 */
	@Override
	public T element() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException();
		}
		return items.get(0);
	}
	
	/**
	 * @return An instance of the LinkedListIterator.
	 */
	public LinkedListIterator<T> iterator() {
		return items.iterator();
	}
}

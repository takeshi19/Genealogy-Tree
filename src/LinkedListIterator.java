import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListIterator<T> implements Iterator<T> {
	private Listnode<T> curr;
	
	/**
	 * Constructs a LinkedListIterator when given the first node
	 * with data for a chain of nodes.
	 * 
	 * Tip: do not construct with a "blank" header node.
	 * 
	 * @param a reference to a List node with data. 
	 */
	public LinkedListIterator(Listnode<T> head) {
		curr = head; //Gets the first node from the LinkedList class iterator constructor.
	}
	
	/**
	 * Returns the next element in the iteration and then advances the
	 * iteration reference.
	 * 
	 * @return the next data item in the iteration that has not yet been returned 
	 * @throws NoSuchElementException if the iteration has no more elements 
	 */
	@Override
	public T next() {
		if (curr == null) {
			throw new NoSuchElementException();
		}
		
		T data = curr.getData(); //Get data from the node that the curr object was pointing to.
		curr = curr.getNext();   //Advances the pointer to the next node to operate on.
		return data;
	}
	
	/**
	 * 
	 * @return true if their are any remaining data items to iterate through
	 */
	@Override
	public boolean hasNext() {
		return curr != null; 
	}
       
    /**
     * The remove operation is not supported by this iterator
     * @throws UnsupportedOperationException if the remove operation is not 
     * supported by this iterator
     */
    @Override
	public void remove() {
	  throw new UnsupportedOperationException();
	}
}
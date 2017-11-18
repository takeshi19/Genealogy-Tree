/**
 * The internal node structure of {@link LinkedList}.
 *
 * @param <T> the generic type of the data content stored in the list
 * 
 * DO NOT EDIT OR HAND IN THIS CLASS
 */
public class Listnode<T> {
	
	/** 
	 * A reference to the data item for this node.  May be null
	 * if this node is used a header node or other.
	 */
    private T data;             
    
    /**
     * A reference to the next node in a singly linked chain of nodes.
     * May be null if this node is the last node in the chain.
     */
    private Listnode<T> next;  

    /**
     * Constructs a new list node with no link to a next node in the chain.
     * @param data the data to be stored in this node.  The data reference
     * may be null.
     */
    public Listnode(T data) {
        this(data, null);
    }
    
    /**
     * Constructs a new list node with a link to the next node in a chain of 
     * nodes.
     * 
     * @param data the data to be stored in this node.  May be null.
     * @param next the node after this one. May be null.
     */
    public Listnode(T data, Listnode<T> next) {
        this.data = data;
        this.next = next;
    }

    /**
     * Returns a reference to data item for this node.
     * @return the current data
     */
    public T getData() {
        return data;
    }

    /**
     * Returns a reference to the next node after this node.
     * @return the next node in the chain
     */
    public Listnode<T> getNext() {
        return next;
    }

    /**
     * Changes what data is referenced by this node.
     * @param data the new data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Changes what node is next after this node in a chain of node.
     * @param the new next node
     */
    void setNext(Listnode<T> next) {
        this.next = next;
    }
}
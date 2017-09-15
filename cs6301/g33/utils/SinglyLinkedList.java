/**
 * 
 */
package cs6301.g33.utils;

/**
 * @author Sushma
 *
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements Iterable<T> {

    /** Class Entry holds a single node of the list */
    static class Entry<T> {
        T element;
        Entry<T> next;

        Entry(T x, Entry<T> nxt) {
            element = x;
            next = nxt;
        }
    }

    // Dummy header is used.  tail stores reference of tail element of list
    Entry<T> head, tail;
    int size;

    public SinglyLinkedList() {
        head = new Entry<>(null, null);
        tail = head;
        size = 0;
    }

    public Iterator<T> iterator() { return new SLLIterator<>(this); }

    private class SLLIterator<E> implements Iterator<E> {
	SinglyLinkedList<E> list;
	Entry<E> cursor, prev;
	boolean ready;  // is item ready to be removed?

	SLLIterator(SinglyLinkedList<E> list) {
	    this.list = list;
	    cursor = list.head;
	    prev = null;
	    ready = false;
	}
	
	
	public boolean hasNext() {
	    return cursor.next != null;
	}
	
	public E next() {
	    prev = cursor;
	    cursor = cursor.next;
	    ready = true;
	    return cursor.element;
	}

	// Removes the current element (retrieved by the most recent next())
	// Remove can be called only if next has been called and the element has not been removed
	public void remove() {
	    if(!ready) {
		throw new NoSuchElementException();
	    }
	    prev.next = cursor.next;
	    // Handle case when tail of a list is deleted
	    if(cursor == list.tail) {
		list.tail = prev;
	    }
	    cursor = prev;
	    ready = false;  // Calling remove again without calling next will result in exception thrown
	    size--;
	}
    }

    // Add new elements to the end of the list
    public void add(T x) {
	tail.next = new Entry<>(x, null);
	tail = tail.next;
	size++;
    }

    public void printList() {
	// Code without using implicit iterator in for each loop:

        Entry<T> x = head;
        while(x != null) {
            System.out.print(x.element + " ");
            x = x.next;
        }
	/*System.out.print(this.size + ": ");
	for(T item: this) {
	    System.out.print(item + " ");
	}

	System.out.println();*/
    }

    // Rearrange the elements of the list by linking the elements at even index
    // followed by the elements at odd index. Implemented by rearranging pointers
    // of existing elements without allocating any new elements.
    public void unzip() {
	if(size < 3) {  // Too few elements.  No change.
	    return;
	}

	Entry<T> tail0 = head.next;
	Entry<T> head1 = tail0.next;
	Entry<T> tail1 = head1;
	Entry<T> c = tail1.next;
	int state = 0;

	// Invariant: tail0 is the tail of the chain of elements with even index.
	// tail1 is the tail of odd index chain.
	// c is current element to be processed.
	// state indicates the state of the finite state machine
	// state = i indicates that the current element is added after taili (i=0,1).
	while(c != null) {
	    if(state == 0) {
		tail0.next = c;
		tail0 = c;
		c = c.next;
	    } else {
		tail1.next = c;
		tail1 = c;
		c = c.next;
	    }
	    state = 1 - state;
	}
	tail0.next = head1;
	tail1.next = null;
    }
    /**
     * 
     * @return
     */
    //Method to reverse a list in iterative fashion
    public void reverseListIterative()
	{
    	this.head = this.head.next;
    	//If head is null return since head is null
		if(this.head == null)
			return;	
		
		Entry<T> prevNode = null;
		Entry<T> currentNode = this.head;
		Entry<T> nextNode = null;
		
		//At 1st iteration current node is head node, set previous to null 
		while(currentNode != null)
			{
			//Set next node to the next of current node.
			nextNode = currentNode.next;
			//Set next of current node to the previous node.
			currentNode.next = prevNode;
			//Set Previous node to the current node
			prevNode = currentNode;
			//Set current node to the next node
			currentNode = nextNode;
		}
		
		//Set head to the previous node.
		head = prevNode;
		return;
	}
    /**
     * Function to print elements recursively
     */
    public void printListElementsRecursive()
    {
    	printRecursivelyReverse(this.head);
    }
    
    /**
     * 
     * @param head - Head Element of the list.
     */
    
    private void printRecursivelyReverse(Entry<T> head) {
    	//Base case of the function , if head is null return then
    	if(head == null)
			return;
    	//Recursively call the function with next element of the list
		printRecursivelyReverse(head.next);
		System.out.print(head.element+" ");	
	}

    
    /**
     * 
     * @param stack - Stack to store the elements of the list
     */
	public void printListReverseIterative(Stack<T> stack)
    {
		//If list is empty return 
    	 if(head == null)
    		 return;
    	 else
    	 {
    		//If head is not null, push elements to the stack
    		while(head != null)
    		{
    			stack.push(head.element);
    			head = head.next;
    		}
    		//Once list is empty and stack is not null, print elements in the stack
    		while(!stack.isEmpty())
    			System.out.print(stack.pop()+" ");
    	 }
    }
	/**
	 * Reverse elements in a list recursively
	 */
	public void reverseListRecursive()
	{
		reverseLinkedListRecursively(head,null);
	}
	/**
	 * 
	 * @param currPointer
	 * @param prevPointer
	 */
	public void reverseLinkedListRecursively(Entry<T> currPointer,Entry<T> prevPointer){
		if(currPointer.next == null)
		{
			head = currPointer;
			currPointer.next = prevPointer;
			return;
		}
		Entry<T> nextPointer = currPointer.next;
		currPointer.next = prevPointer;
		reverseLinkedListRecursively(nextPointer, currPointer);
	}
}
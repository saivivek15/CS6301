package cs6301.g33.shortProject2;


/**
 * @author Sai Vivek Kanaparthy
 * @author Sushma Eati
 * 
 * Stack implemented using ArrayDeque
 */

import java.util.ArrayDeque;
import java.util.EmptyStackException;
import java.util.Iterator;

public class StackImplArrayDeque<T> {
	ArrayDeque<T> stck; //ArrayDeque to store elements of stack
	final int size; //size of the stack is fixed
	
	StackImplArrayDeque(int s){
		size=s;
		stck = new ArrayDeque<T>(s) ;
	}
	
	/**
	 * Procedure to add element 'entry' to the stack
	 * @param entry: element to be added
	 */
	public void push(T entry){
		if(stck.size()==size){
			throw new IndexOutOfBoundsException();
		}else{
			stck.add(entry);
		}
	}
	
	/**
	 * Procedure to retrieve and remove the top element of the stack
	 * @return last in first out element of the stack
	 */
	public T pop(){
		if(!this.empty()){
			return stck.removeLast();
		}
		else{
			throw new EmptyStackException();
		}
	}
	
	/**
	 * Procedure to retrieve and does not remove the top element of the stack
	 * @return top element of the stack
	 */
	public T peek(){
		if(this.empty()){
			throw new EmptyStackException();
		}
		T top = stck.getLast();
		return top;
	}
	
	/**
	 * Procedure to check whether the stack is empty or not
	 * @return true if it is empty else false
	 */
	public boolean empty(){
		if (stck.size() == 0){
			return true;
		}
		return false;
	}
	
	/**
	 * Procedure to search the position of the element in the stack
	 * @param entry: element to be searched
	 * @return position of the element when it can be popped
	 */
	public int search(T entry){
		Iterator<T> iter = stck.iterator();
		int position=stck.size();
		while(iter.hasNext()){
			if(entry == iter.next()){
				return position;
		}
			position--;
		}
		return -1;
	}
	public static void main(String[] args) throws Exception{
		StackImplArrayDeque<Integer> sample = new StackImplArrayDeque<>(4);
		sample.push(2);
		sample.push(3);
		sample.push(4);
		sample.push(5);
		sample.pop();
		sample.push(9);
		sample.pop();
		System.out.println(sample.search(2));

	}
}

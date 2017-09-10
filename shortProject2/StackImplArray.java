package cs6301.g33.shortProject2;

/**
 * @author Sai Vivek Kanaparthy
 * @author Sushma Eati
 * 
 * Stack implemented using Arrays
 */

import java.lang.reflect.Array;
import java.util.EmptyStackException;
import java.util.Iterator;
import cs6301.g33.utils.ArrayIterator;


public class StackImplArray<T> implements Iterable<T> {
	T[] stck; //array to store elements of stack
	final int size; //size of the stack is fixed
	int top; // top element of the stack
	
	StackImplArray(Class<T> t,int s){
		size=s;
		stck = (T[]) Array.newInstance(t,s);
		top =-1;
	}
	
	/**
	 * Procedure to add element 'entry' to the stack
	 * @param entry: element to be added
	 */
	public void push(T entry){
		if(top==size-1){
			throw new UnsupportedOperationException();
		}else{
			top++;
			stck[top]=entry;
		}
	}
	
	/**
	 * Procedure to retrieve and remove the top element of the stack
	 * @return last in first out element of the stack
	 */
	public T pop(){
		if(!this.empty()){
			T element = stck[top];
			top--;
			return element;
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
		T element = stck[top];
		return element;
	}
	
	/**
	 * Procedure to check whether the stack is empty or not
	 * @return true if it is empty else false
	 */
	public boolean empty(){
		if (top == -1){
			return true;
		}
		return false;
	}
	
	public Iterator<T> iterator(){
		return new ArrayIterator<T>(stck,0,top);
	}
	
	/**
	 * Procedure to search the position of the element in the stack
	 * @param entry: element to be searched
	 * @return position of the element when it can be popped
	 */
	public int search(T entry){
		Iterator<T> iter = iterator();
		int position=top+1;
		while(iter.hasNext()){
			if(entry == iter.next()){
				return position;
		}
			position--;
		}
		return -1;
	}
	
	/**
	 * Driver class
	 *
	 */
	public static void main(String[] args){
		StackImplArray<Integer> sample = new StackImplArray<>(Integer.class,4);
		sample.push(2);
		sample.push(3);
		sample.push(4);
		sample.push(5);
		sample.pop();
		sample.push(9);
		sample.pop();
		System.out.println(sample.search(4));

	}
}

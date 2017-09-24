/**
 * 
 */
package cs6301.g33.utils;


/**
 * @author Sushma
 *
 */
public class Stack<T>{

	private  T[] stackElements;
	private  int  top;
	private int maxSize;
	
	//Basic stack initialization, set top to -1 and all other elements.
	public Stack(int size,T[] elements)
	{
		this.stackElements =elements;
		this.maxSize = size;
		top =-1;
	}
	
	//Push element on to stack
	public void push(T element)
	{
		top++;
		stackElements[top] = element;
	}
	
	//Pop an element from stack
	public T pop()
	{
		
		T elem = stackElements[top];
		top--;
		return elem;
	}
	
	public void printElements()
	{
		for(int i=0;i<=top;i++)
			System.out.println(stackElements[i]);
	}
	
	//Check if stack is empty
	public boolean isEmpty(){
		return top == -1;
	}
	
	//Check if stack is full
	public boolean isFull(){
		return top == maxSize;
	}
	
	public T getTopElement(){
		return stackElements[top];
	}
}

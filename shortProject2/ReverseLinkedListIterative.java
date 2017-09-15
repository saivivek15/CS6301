/**
 * 
 */
package cs6301.g33.shortProject2;

import cs6301.g33.utils.SinglyLinkedList;
import cs6301.g33.utils.Stack;

/**
 * @author Sushma
 * @author Sai Vivek Kanaparthy
 *
 */
public class ReverseLinkedListIterative<T> extends SinglyLinkedList<T>{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Intialize size of linked list
		int n = 20;
        if(args.length > 0) {
            n = Integer.parseInt(args[0]);
        }
        //Create singly linked list 
        SinglyLinkedList<Integer> lst = new SinglyLinkedList<>();
        for(int i=1; i<=n; i++) {
            lst.add(new Integer(i));
        }
        
       //Reverse elements of the list iteratively
       lst.reverseListIterative();
       System.out.println("Reversing a Linked List Non Recursively");
       lst.printList();
       System.out.println();
       
       //Reverse elements of a list using recursion
       System.out.println("Reversing Elements of a LinkedList Recursively");
       lst.reverseListRecursive();
       lst.printList();
       System.out.println();
       
       //Print elements of a list recursively
       System.out.println("Printing Elements of a LinkedList Recursively");
       lst.printListElementsRecursive();
       Stack<Integer> stack = new Stack<Integer>(n, new Integer[n]);
       System.out.println();
       
       //Print elements of a list iteratively
       System.out.println("Printing Elements of a LinkedList Iteratively");
       lst.printListReverseIterative(stack);
      
	}
}

/**
 * 
 */
package cs6301.g33.shortProject4;

import java.util.Scanner;

import cs6301.g33.utils.BinarySearch;

/**
 * @author Sushma
 *
 */
public class BinarySearchGenerics implements Comparable<BinarySearchGenerics> {
	/**
	 * @param args
	 */
	int  element;
	
	public BinarySearchGenerics(int element) {
		super();
		this.element = element;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		//Take input from command line to get the size of array
		int n = scanner.nextInt();
		if(n == 0)
		{
			System.out.print("Enter atlease one item ");
			System.exit(-1);
		}
		//Create an array of input 
		BinarySearchGenerics[] input = new BinarySearchGenerics[n];
		
		//Create object array to store the elements.
		for(int i=0,index=0;i<n;i++,index+=2){
			input[i] = new BinarySearchGenerics(index);
		}
		
		//Create an object to store the element that has to be searched
		BinarySearchGenerics item = new BinarySearchGenerics(20);
		
		/**
		 * input: Input to the binary search function
		 * 0 : Initial Index.
		 * n-1 : Length of the array.
		 * item: Item that has to be searched in the passed input array.
		 */
		
		System.out.print(BinarySearch.binarySearch(input, 0, n-1, item));
		
		//Close the scanner.
		scanner.close();
	}
	
	//Override compareTo function to compare two objects
	@Override
	public int compareTo(BinarySearchGenerics object) {
		if(this.element < object.element)
			return -1;
		else if(this.element > object.element)
			return 1;
		return 0;
	}

}

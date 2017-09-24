/**
 * 
 */
package cs6301.g33.shortproject1;

import cs6301.g33.utils.Shuffle;
import cs6301.g33.utils.Timer;

/**
 * @author Sushma
 *
 */
public class SortElementsUsingComparable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		
		//Array size
		int max_size = 20000000;
		
		//Variables used in case of generic merge sort
		Integer[] integer_input_generic = new Integer[max_size];
		Integer[] temp_array_generic = new Integer[max_size];
		
		//Variables used in case of int[] merge sort
		int[] temp = new int[max_size];
		int[] integer_input = new int[max_size];
		
		
		//Input creation
		for (int index = 0; index < max_size; index++) {
			integer_input_generic[index] = index;
			integer_input[index] = index;
		}
		
		//Shuffle elements for generating random order
		Shuffle.shuffle(integer_input_generic);
		
		//Initialize timer
		Timer time = new Timer();
		
		
		System.out.println("******* MERGE SORT GENERICS *******");
		//Generic Merge Sort
		time.start(); //Start timer
		//Merge Sort
		GenericMergeSortUsingComparable.mergeSort(integer_input_generic, temp_array_generic, 0, max_size-1);
		//Print total time for merge sort using generics
		System.out.println(time.end());
		
		
		//Shuffle elements for generating random order
		Shuffle.shuffle(integer_input);
		
		System.out.println("******* MERGE SORT INT[] ARRAY *******");

		//Merge Sort - Integer Arrays
		time.start();//Start timer
		//Merge Sort
		MergeSortUsingIntArray.mergeSort(integer_input,temp, 0, max_size-1);
		//Print total time for merge sort using int array
		System.out.println(time.end());
		
		//Shuffle elements for generating random order
		Shuffle.shuffle(integer_input_generic);
		
		System.out.println("******* INSERTION SORT GENERICS *******");

		//Merge Sort - Integer Arrays
		time.start();//Start timer
		//Merge Sort
		GenericNSquareSort.nSquareSort(integer_input_generic, max_size-1);
		//Print total time for merge sort using int array
		System.out.println(time.end());
		
	}
	
}

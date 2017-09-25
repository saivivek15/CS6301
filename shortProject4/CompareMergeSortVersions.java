/**
 * 
 */
package cs6301.g33.shortProject4;

import cs6301.g33.shortproject1.MergeSortUsingIntArray;
import cs6301.g33.utils.LegacyMergeSort;
import cs6301.g33.utils.MergeSortCombinedWithInsertionSort;
import cs6301.g33.utils.MergeSortRemoveUnneccesaryCopies;
import cs6301.g33.utils.Shuffle;
import cs6301.g33.utils.Timer;

/**
 * @author Sushma, Sai Vivek Kanaparthy
 *
 */
public class CompareMergeSortVersions{
	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		
		//Array size
		int max_size = 2000000;
		
		//Variables used in case of int[] merge sort
		int[] temp = new int[max_size];
		int[] integer_input = new int[max_size];
		
		
		//Input creation
		for (int index = 0; index < max_size; index++) {
			integer_input[index] = index;
		}

		//Initialize timer
		Timer time = new Timer();
		
		//Shuffle elements for generating random order
		Shuffle.shuffle(integer_input);
		System.out.println("******* LEGACY MERGE SORT ********");
		
		//Merge Sort - Legacy Algorithm
		time.start();
		//MergeSort
		LegacyMergeSort.mergeSort(integer_input, 0, max_size-1);
		System.out.println(time.end());
		
		//Shuffle Elements
		Shuffle.shuffle(integer_input);
		
		System.out.println("******* MERGE SORT INT[] ARRAY *******");

		//Merge Sort - Integer Arrays
		time.start();//Start timer
		//Merge Sort
		MergeSortUsingIntArray.mergeSort(integer_input,temp, 0, max_size-1);
		//Print total time for merge sort using int array
		System.out.println(time.end());
		
		//Shuffle elements for generating random order
		Shuffle.shuffle(integer_input);
		
		System.out.println("******* MERGE SORT ALONG WITH THRESHOLD *******");

		//Merge Sort - Integer Arrays
		time.start();//Start timer
		//Merge Sort
		MergeSortCombinedWithInsertionSort.mergeSort(integer_input, temp, 0, max_size-1);
		//Print total time for merge sort using int array
		System.out.println(time.end());
		
		Shuffle.shuffle(integer_input);
		
		System.out.println("******* MERGE SORT ALONG WITH THRESHOLD AND UNNECESSARY COPIES *******");

		//Merge Sort - Integer Arrays
		time.start();//Start timer
		temp = integer_input.clone();
		//Merge Sort
		MergeSortRemoveUnneccesaryCopies.mergeSort(integer_input, temp, 0, max_size-1);
		//Print total time for merge sort using int array
		System.out.println(time.end());
	}
}

/**
 * 
 */
package cs6301.g33.shortProject5;

import cs6301.g33.utils.DualPartitionSort;
import cs6301.g33.utils.MergeSortRemoveUnneccesaryCopies;
import cs6301.g33.utils.Shuffle;
import cs6301.g33.utils.Timer;

/**
 * @author Sushma
 *
 */
public class PerformanceOfMergeSortAndQuickSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int n = 5120000;
		//Input array to the sorting algorithm
		int[] inputArray = new int[n];
		
		//Input numbers
		for(int i=0;i<n;i++)
			inputArray[i] = i;
		
		//Shuffle the input array
		Shuffle.shuffle(inputArray);
		
		Timer t = new Timer();
		
		//Call Merge Sort for sorting the elements.
		System.out.println("****** Sorting with Merge Sort ******");
		t.start();
		MergeSortRemoveUnneccesaryCopies.mergeSort(inputArray, new int[n], 0, n-1);
		System.out.println(t.end());
		
		//Shuffle the elements before sending to quick sort
		Shuffle.shuffle(inputArray);
		
		//Call Quick sort for sorting the elements.
		System.out.println("****** Sorting with QuickS Sort ******");
		t.start();
		DualPartitionSort.dualPartiotionQuicksort(inputArray, 0, n-1);
		System.out.println(t.end());
	}

}

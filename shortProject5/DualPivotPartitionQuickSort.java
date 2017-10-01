/**
 * 
 */
package cs6301.g33.shortProject5;

import cs6301.g33.utils.DualPartitionSort;
import cs6301.g33.utils.Shuffle;

/**
 * @author Sushma, Sai Vivek Kanaparthy
 *
 */
public class DualPivotPartitionQuickSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		//Size of the array
		int n = 20;
		//Input array to the sorting algorithm
		int[] inputArray = new int[n];
		
		//Input numbers
		for(int i=0;i<n;i++)
			inputArray[i] = i;
		
		//Shuffle the input array
		Shuffle.shuffle(inputArray);
		
		//Call QuickSort Dual Partition
		DualPartitionSort.dualPartiotionQuicksort(inputArray, 0, n-1);
		
	}

}

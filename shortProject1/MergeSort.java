package cs6301.g33.shortProject1;

import cs6301.g33.utils.Shuffle;
import cs6301.g33.utils.Timer;

/**
 * Date created: 08/24/2017
 * @author Chandra Sekhar Guntupalli
 * @author Sai Vivek Kanaparthy
 * @author Sushma Eati
 * @author Abhinaya Krishna Mandepudi
 * 
 */
public class MergeSort {
	
	/**
	 * 
	 * @param arr : int array to be sorted
	 * @param tmp : tmp array is used to store values during the merge operation
	 * @return 
	 */
	public static void mergeSort(int[] arr, int[] tmp) {
		mergeSort(arr, tmp, 0, arr.length - 1);
	}
	
	/**
	 * Procedure to do the sorting - divides the input into 2 halves and calls itself 
	 * for the two halves and merges the sorted halves
	 * 
	 * @param arr : int array to be sorted
	 * @param tmp : tmp array is used to store values during the merge operation
	 * @param leftStart : start index of left sub-array
	 * @param rightEnd : end index of right sub-array
	 */
	public static void mergeSort(int[] arr, int[] tmp, int leftStart, int rightEnd) {
		if (leftStart >= rightEnd) 
			return;
		int middle = (leftStart + rightEnd)/2;
		mergeSort(arr, tmp, leftStart, middle);
		mergeSort(arr, tmp, middle+1, rightEnd);
		mergeHalves(arr, tmp, leftStart, rightEnd);
	}
	
	/**
	 * Procedure for merging two sorted sub-arrays (halves)
	 * Precondition - arr[leftStart...leftEnd] and arr[rightStart...rightEnd] are sorted
	 * Look into the procedure for the definition of leftEnd and rightStart
	 * 
	 * @param arr : int array to be sorted
	 * @param tmp : temporary int array used to store values during the merge operation
	 * @param leftStart : start index of the left sub-array
	 * @param rightEnd : end index of the right sub-array
	 */
	public static void mergeHalves(int[] arr, int[] tmp, int leftStart, int rightEnd) {
		int leftEnd = (leftStart + rightEnd)/2;
		int righStart = leftEnd + 1;
		int size = rightEnd - leftStart + 1;
		
		int left = leftStart;
		int right = righStart;
		
		int index = leftStart;
		while(left <= leftEnd && right <= rightEnd) {
			if (arr[left] <= arr[right])
				tmp[index] = arr[left++];
			else
				tmp[index] = arr[right++];
			index++;
		}
		
		System.arraycopy(arr, left, tmp, index, leftEnd - left + 1);
		System.arraycopy(arr, right, tmp, index, rightEnd - right + 1);

		System.arraycopy(tmp, leftStart, arr, leftStart, size);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 16000000;
		int[] array = new int[n];
		
		// create array in the descending order 
		for(int i=n;i>0;i--) {
			array[n-i] = i;
		}
		Shuffle.shuffle(array);
		System.out.println("Analysis of Merge Sort without Generics...");
		Timer t = new Timer();
        mergeSort(array, new int[n]);
        System.out.println(t.end());
	}

}

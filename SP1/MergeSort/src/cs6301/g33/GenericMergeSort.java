/**
 * 
 */
package cs6301.g33;

/**
 * Date created: 08/24/2017
 * @author Chandra Sekhar Guntupalli
 * @author Abhinaya Krishna Mandepudi
 * @author Sai Vivek
 * @author Sushma
 *
 */
public class GenericMergeSort {

	/**
	 * @param arr : generic array
	 * @param tmp : temporary array used to store values during the merge operation
	 */
	public static <T extends Comparable<? super T>> void mergeSort(T[] arr, T[] tmp) {
		mergeSort(arr, tmp, 0, arr.length - 1);
	}
	
	/**
	 * Procedure to do the sorting - divides the input into 2 halves and calls itself 
	 * for the two halves and merges the sorted halves
	 * 
	 * @param arr : generic array to be sorted
	 * @param tmp : temporary array used to store values during the merge operation
	 * @param leftStart: start index of the left sub-array
	 * @param rightEnd : end index of the right sub-array
	 */
	public static <T extends Comparable<? super T>> void mergeSort(T[] arr, T[] tmp, 
			int leftStart, int rightEnd) {
		if (leftStart >= rightEnd)
			return;
		int middleIndex = (leftStart + rightEnd)/2;
		mergeSort(arr, tmp, leftStart, middleIndex);
		mergeSort(arr, tmp, middleIndex + 1, rightEnd);
		mergeHalves(arr, tmp, leftStart, rightEnd);
	}
	
	/**
	 * Procedure for merging two sorted sub-arrays (halves)
	 * Precondition - arr[leftStart...leftEnd] and arr[rightStart...rightEnd] are sorted
	 * Look into the procedure for the definition of leftEnd and rightStart
	 * 
	 * @param arr : generic array to be sorted
	 * @param tmp : temporary array used to store values during the merge operation
	 * @param leftStart : start index of the left sub-array
	 * @param rightEnd : end index of the right sub-array
	 */
	public static <T extends Comparable<? super T>> void mergeHalves(T[] arr, T[] tmp, 
			int leftStart, int rightEnd) {
		//end index of left sub-array
		int leftEnd = (leftStart + rightEnd)/2;
		
		//start index of right sub-array
		int rightStart = leftEnd + 1;
		
		// size of the merged array
		int size = rightEnd - leftStart + 1;
		
		//left , right - initial indexes of the respective sub-arrays
		int left = leftStart;
		int right = rightStart;
		
		//initial index of the merged array
		int index = 0;
		
		//This loop merges the two sorted sub arrays and stored in array - tmp
		while(left <= leftEnd && right <= rightEnd) {
			if (arr[left].compareTo(arr[right]) <= 0) {
				tmp[index] = arr[left++];
			} else {
				tmp[index] = arr[right++];
			}
			index++;
		}
		
		//copies remaining elements of left sub-array if any of them are left
		while(left <= leftEnd) {
			tmp[index++] = arr[left++];
		}
		
		//copies remaining elements of the right sub-array if any of them are left
		while(right <= rightEnd) {
			tmp[index++] = arr[right++];
		}
		
		//copying back the sorted temporary array into the original one
		for (index = 0; index < size; index++) {
			arr[leftStart + index] = tmp[index];
		}
	}
	
	/**
	 * Driver Function
	 * 
	 * @param args : String array that stores command line arguments
	 */	
	public static void main(String[] args) {
		String[] array = new String[3];
        array[0] = "usa";
        array[1] = "apple";
        array[2] = "india";

		for (int i = 0; i < array.length; i++)
            System.out.println(array[i]);

        GenericMergeSort.mergeSort(array, new String[3]);
        System.out.println("\nAfter Sorting\n");

        for (int i = 0; i < array.length; i++)
            System.out.println(array[i]);

	}

}


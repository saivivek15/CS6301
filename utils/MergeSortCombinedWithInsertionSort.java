/**
 * 
 */
package cs6301.g33.utils;

/**
 * @author Sushma, Sai Vivek Kanaparthy
 *
 */
public class MergeSortCombinedWithInsertionSort {
	
	final static int THRESHOLD_VALUE = 10;
	/**
	 * 
	 * @param input: Input array to be sorted
	 * @param temp_array: Temp array used in between merge function
	 * @param start_index: Start index
	 * @param end_index: End Index
	 */
	public static void mergeSort(int[] input,int[] temp_array, int start_index, int end_index)
	{
		if(input.length <= THRESHOLD_VALUE)
		{
			//If threshold value is less then call Insertion sort
			InsertionSort.insertionSort(input, input.length);
		}
		else
		{
			//Else call Merge Sort
			MergeSortUsingIntArray.mergeSort(input, temp_array, start_index, end_index);
		}
	}

}

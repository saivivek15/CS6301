/**
 * 
 */
package cs6301.g33.utils;

/**
 * @author Sushma
 *
 */
public class MergeSortCombinedWithInsertionSort {
	
	final static int THRESHOLD_VALUE = 20;
	
	public static void mergeSort(int[] input,int[] temp_array, int start_index, int end_index)
	{
		if(input.length <= THRESHOLD_VALUE)
		{
			InsertionSort.nSquareSort(input, input.length);
		}
		else
		{
			MergeSortUsingIntArray.mergeSort(input, temp_array, start_index, end_index);
		}
	}

}

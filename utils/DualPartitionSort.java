/**
 * 
 */
package cs6301.g33.utils;

/**
 * @author Sushma, Sai Vivek Kanaparthy
 *
 */

//Class implementing Dual Partition of QuickSort
public class DualPartitionSort {
	
	/**
	 * @param inputArray: Input Array to be sorted.
	 * @param index1: Index of the array to be swapped.
	 * @param index2: Index of the array to be swapped.
	 */
	
	public static void exchangeElements(int[] inputArray, int index1, int index2)
	{
		//Swap elements.
		int temp = inputArray[index1];
		inputArray[index1] = inputArray[index2];
		inputArray[index2] = temp;
	}
	
	/**
	 * 
	 * @param inputArray: Input Array to be sorted.
	 * @param start_index: Index of the first element.
	 * @param end_index: Index of the last element.
	 */
	public static void dualPartiotionQuicksort(int[] inputArray, int start_index, int end_index)
	{
		//If end index is less than start index
		if(end_index<=start_index)
			return;
		//Pivot1
		int pivot1 = inputArray[start_index];
		//Pivot2
		int pivot2 = inputArray[end_index];
		
		//If pivot1 is greater than pivot2
		if(pivot1 > pivot2)
		{
			//Swap elements
			exchangeElements(inputArray, start_index, end_index);
			//Assign new pivot1 and pivot2
			pivot1 = inputArray[start_index];
			pivot2 = inputArray[end_index];
		}
		//If pivot1 and pivot2 are equal then increment the start index
		else if(pivot1 == pivot2)
		{
			//while pivot1 and pivot2 are equal, increment the start index and assign new value to pivot1
			while(pivot1 ==pivot2 && (start_index<end_index))
			{
				start_index++;
				pivot1 = inputArray[start_index];
			}
		}
		
		//Assign i to start_index, end_index-1 to gt and start_index+1 to lt
		int i = start_index+1;
		int gt = end_index-1;
		int lt = start_index+1;
		
		while(i<=gt)
		{
			if(inputArray[i]<pivot1)
				exchangeElements(inputArray, i++, lt++);
			else if(pivot2<inputArray[i])
				exchangeElements(inputArray, i, gt--);
			else
				i++;		
		}
		//Exchange elements at start index and less than index--
		exchangeElements(inputArray, start_index, --lt);
		//Exchange elements at end_index and gt++
		exchangeElements(inputArray, end_index, ++gt);
		
		//Call Quicksort dual partition on array elements between start_index and lt-1
		dualPartiotionQuicksort(inputArray, start_index, lt-1);
		//Call Quicksort dual partition on array elements btw lt+1 and gt-1
		dualPartiotionQuicksort(inputArray, lt+1, gt-1);
		//Call Quicksort dual partition on array elements btw gt+1 and end_index
		dualPartiotionQuicksort(inputArray, gt+1, end_index);
		
	}
}

/**
 * 
 */
package cs6301.g33.shortproject1;

/**
 * @author Sushma, Sai Vivek Kanparthy
 * Sorts an input array by dividing the input array into sub arrays and then merge the sorted arrays.
 */
public class MergeSortUsingIntArray {
	/**
	 * 
	 * @param input: Integer input array read from Standard I/O or File or through Command Line
	 * @param temp_array : Temp Array to be passed to merge function.
	 * @param start_index : Start index of the input
	 * @param end_index : Index of the last element.
	 */
	public static void mergeSort(int[] input,int[] temp_array, int start_index, int end_index)
	{
		//If start index and end index are equal just return  from recursive call
			 if(start_index == end_index)
				 return;
			 //Calculate mid index and apply merge on 
			 int middle_index = (start_index+end_index)>>>1;
			 //Recursively divide the left sub array
			 mergeSort(input,temp_array,start_index, middle_index);
			 //Recursively divide the right sub array
			 mergeSort(input,temp_array,middle_index+1, end_index);
			 //Merge and sort the sub arrays
			 mergeArrays(input,temp_array,start_index, end_index, middle_index);
		 
	}
	
	/**
	 * 
	 * @param input:Integer input array read from Standard I/O or File or through Command Line
	 * @param temp_array : Temp Array to be passed to merge function.
	 * @param start_index : Start index of the input array being passed
	 * @param end_index : end index of the input array being passed
	 * @param middle_index : Middle index of the input array being passed.
	 */
	public static void mergeArrays(int[] input,int[] temp_array, int start_index,int end_index, int middle_index)
	{
		int size_of_array = (end_index - start_index)+1;
		int temp_start = start_index;
		int temp_index = 0;
		int temp_middle_index = middle_index+1;

		//Check 1st element of left array and 1st element of right array, assign smallest elem to the temp array and increase index accordingly
		while(temp_start <= middle_index && temp_middle_index <= end_index)
		{
			if(input[temp_start] <input[temp_middle_index])
			{
				temp_array[temp_index] = input[temp_start];
				temp_start++;
			}
			
			else
			{
				temp_array[temp_index] = input[temp_middle_index];
				temp_middle_index++;
			}
			temp_index++;
		}
		
		//Add remaining elements of the left array to the temp array if any
		while(temp_start<= middle_index)
		{
			temp_array[temp_index] = input[temp_start];
			temp_index++;
			temp_start++;
		}
		//Add remaining elements of the right array to the temp array if any
		while(temp_middle_index<=end_index)
		{
			temp_array[temp_index] = input[temp_middle_index];
			temp_index++;
			temp_middle_index++;
		}
		
		//Move elements from temp array to main array.
		for(int index =0 ; index <  size_of_array ; index++)
		{
			input[index+start_index] = temp_array[index];
		}
	}


}

/**
 * 
 */
package cs6301.g33.shortproject1;

import java.util.Comparator;

/**
 * @author Sushma
 *
 */
public class GenericMergeSortUsingComparator {
	
	public static<T extends Comparator<? super T>> void mergeSort(T[] input,T[] temp_array, int start_index, int end_index,Comparator<? super T> comp)
	{
		 if(start_index == end_index)
			 return;
		 
			 int middle_index = (start_index+end_index)/2;
			 mergeSort(input, temp_array,start_index, middle_index,comp);
			 mergeSort(input, temp_array,middle_index+1, end_index,comp);
			 mergeArrays(input, temp_array,start_index, end_index, middle_index,comp);
		 
	}
	
	public static<T extends Comparator<? super T>> void mergeArrays(T[] input,T[] temp_array, int start_index,int end_index, int middle_index,Comparator<? super T> comp)
	{
		int size_of_array = end_index - start_index+1;
		int temp_start = start_index;
		int temp_index = 0;
		int temp_middle_index = middle_index+1;
	
		while(temp_start <= middle_index && temp_middle_index <= end_index)
		{
			if(comp.compare(input[temp_start], input[temp_middle_index]) < 0)
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
		while(temp_start<= middle_index)
		{
			temp_array[temp_index] = input[temp_start];
			temp_index++;
			temp_start++;
		}
		while(temp_middle_index<=end_index)
		{
			temp_array[temp_index] = input[temp_middle_index];
			temp_index++;
			temp_middle_index++;
		}
		
		for(int index=0 ; index<size_of_array ; index++)
		{
			input[index+start_index] = temp_array[index];
		}
		
	}
}

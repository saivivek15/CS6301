package cs6301.g33.utils;
/*
 * 
 * @author Sushma, Sai Vivek Kanaparthy
 *
 */

public class InsertionSort {
/**
 * 	
 * @param input_array: Array to be sorted
 * @param length: Length of the array
 */
	public static void insertionSort(int[] input_array,int length){
		for(int start_index = 1; start_index <= length - 1; start_index++){
			int item  = input_array[start_index];
			int temp_index = start_index - 1;
			while(temp_index >= 0 && item< input_array[temp_index]){
				input_array[temp_index+1] = input_array[temp_index];
				temp_index -= 1;
			}
			input_array[temp_index + 1] = item;
		}
	}
}

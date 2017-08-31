package cs6301.g33;
/*
 * @author Sai Vivek Kanaparthy
 * @author Sushma Eati
 * @author Chandra Sekhar Guntupalli
 * @author Abhinaya Krishna Mandepudi
 */
public class MergeSortGenerics {
	
	/**
	 * @param arr : array to be sorted
	 * @param tmp : temporary array to store intermediate values in merge method
	 * @param start : start index of array for sorting
	 * @param end: end index of array for sorting
	 * @param mid: mid index of array where it is to be divided into two sub arrays
	 * Procedure to merge sub arrays by sorting the elements of sub arrays
	 */
	public static<T extends Comparable<? super T>> void merge(T[] arr,T[] tmp,int start, int mid, int end){
		int left=start;
		int right=mid+1;
		int k=start;
		// traverse along the two sub arrays and copy the least into the tmp array
		while(left<=mid && right<=end){
			if (arr[left].compareTo(arr[right])<=0){
				tmp[k]=arr[left];
				left++;
			}
			else{
				tmp[k]=arr[right];
				right++;
			}
			k++;
		}
		//copy the left over elements if any in the left sub array
		while(left<=mid){
			tmp[k]=arr[left];
			k++;
			left++;
		}
		//copy the left over elements if any in the right sub array
		while(right<=end){
			tmp[k]=arr[right];
			k++;
			right++;
		}
		//copy sorted elements from the temp array
		for(left = start; left <=end; left++){
			arr[left] = tmp[left];
		}
	}
	/**
	 * @param arr : array to be sorted
	 * @param tmp : temporary array to store intermediate values in merge method
	 * @param start : start index of array for sorting
	 * @param end: end index of array for sorting
	 * Procedure to run merge sort where arrays are divided into sub arrays 
	 * and merge method is applied to get sorted arrays
	 */
	public static<T extends Comparable<? super T>> void sort(T[] arr,T[] tmp, int start, int end){
		if(start < end){
		int mid = (start+end) >>> 1;
		//recursive call for left sub array
		sort(arr,tmp, start, mid);
		//recursive call for right sub array
		sort(arr,tmp, mid+1, end);
		//merge the sorted left and right sub arrays
		merge(arr,tmp,start,mid,end );
		}
	}
	/**
	 * @param arr : array to be sorted
	 * @param tmp : temporary array to store intermediate values in merge method
	 */
	public static<T extends Comparable<? super T>> void mergeSort(T[] arr, T[] tmp){
		sort(arr,tmp, 0, arr.length - 1);
	}
	/**
	 *  Procedure for n square sorting algorithm - insertion sort
	 * @param arr: array to be sorted
	 */
	public static<T extends Comparable<? super T>> void nSquareSort(T[] arr){
		int n = arr.length;
		for(int i=1;i<n;i++){
			T temp=arr[i];
			int j;
			for(j=i-1;j>=0 && arr[j].compareTo(temp) > 0;j--){
				//shift all the elements to the right
				arr[j+1]=arr[j];
			}
			// insert at the correct position
			arr[j+1]=temp;
		}
	}
	/**
	 * Driver Class
	 */
	public static<T extends Comparable<? super T>> void main(String[] args){
		int n=1000000;
		Integer[] A = new Integer[n];
		Integer[] tmp = new Integer[n];
		// create array in the descending order to get the worst case running time
			for(int i=n;i>0;i--){
					A[n-i] = i;
			}
//		for(int each=0;each<n;each++){
//			System.out.print(A[each]+" ");
//		}
		System.out.println("Analysis of Merge Sort with Generics...");
		Timer t = new Timer();
		mergeSort(A,tmp);
		System.out.println(t.end());
//		for(int each=0;each<n;each++){
//		System.out.print(tmp[each]+" ");
//	}
		System.out.println("Analysis of Insertion Sort with Generics...");
		t.start();
		nSquareSort(A);
		System.out.println(t.end());
	}
	}

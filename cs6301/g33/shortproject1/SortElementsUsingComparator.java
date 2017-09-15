/**
 * 
 */
package cs6301.g33.shortproject1;

import java.util.Comparator;

import cs6301.g33.utils.Shuffle;

/**
 * @author Sushma
 *
 */
public class SortElementsUsingComparator implements Comparator<SortElementsUsingComparator> {

	int integerElement;
	String stringElement;
	float floatElement;
	
	//Constructor
	public SortElementsUsingComparator(int integerElement, String stringElement,
			float floatElement) {
		super();
		this.integerElement = integerElement;
		this.stringElement = stringElement;
		this.floatElement = floatElement;
	}

	// Sort string elements of the class
	public class SortStringElements implements Comparator<SortElementsUsingComparator> {

		@Override
		public int compare(SortElementsUsingComparator o1, SortElementsUsingComparator o2) {
			return o1.stringElement.compareTo(o2.stringElement);
		}
	}

	// Sort Float Elements of the class
	public class SortFloatElements implements Comparator<SortElementsUsingComparator> {

		@Override
		public int compare(SortElementsUsingComparator floatObject1, SortElementsUsingComparator floatObject2) {
			if (floatObject1.floatElement < floatObject2.floatElement)
				return -1;
			else if (floatObject1.floatElement > floatObject2.floatElement)
				return 1;
			else
				return 0;
		}

	}

	//Sort Integer Elements of the class
	@Override
	public int compare(SortElementsUsingComparator integerObject1, SortElementsUsingComparator integerObject2) {
		// TODO Auto-generated method stub
		if (integerObject1.integerElement < integerObject2.integerElement)
			return -1;
		else if (integerObject1.integerElement > integerObject2.integerElement)
			return 1;
		else
			return 0;
	}

	public static void main(String[] args) {
		// Sort Elements(Sorting Technique: Merge Sort) (Type can be integers,
		// float, double, strings, characters)
		int max_size = 7;
		SortElementsUsingComparator[] sortItems = new SortElementsUsingComparator[max_size];
		SortElementsUsingComparator[] tempSortItems = new SortElementsUsingComparator[max_size];
		// Generate Input and shuffle them
		for (int index = 0; index < max_size; index++) {
			sortItems[index] = new SortElementsUsingComparator(index, null, 0);
			tempSortItems[index] = new SortElementsUsingComparator(0, null, 0);
		}
		Shuffle.shuffle(sortItems);
		// Sort array based on merge sort
		System.out.println("Before Sorting");
		for(int i=0;i<7;i++)
			System.out.println(sortItems[i].integerElement);
		GenericMergeSortUsingComparator.mergeSort(sortItems, tempSortItems, 0, max_size-1, new SortElementsUsingComparator(0, null, 0));
		System.out.println("After Sorting");
		for(int i=0;i<7;i++)
			System.out.println(sortItems[i].integerElement);
	}

}

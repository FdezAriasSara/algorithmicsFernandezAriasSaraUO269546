package algstudent.s2;


/* This program can be used to sort n elements with 
 * the best algorithm of this lab. It is the QUICKSORT */
public class QuicksortCentralElement extends Vector {
	
	public static final int SORTED_ALREADY = 3;

	public QuicksortCentralElement(int nElements) {
		super(nElements);
	}
	
	private void quickSort(int left, int right) {
		int i=left;
		int j=right-1;
		int pivot;
		if(left<right) {//if there's only one element the vector is sorted already
			int center= left+right/2;//the middle position of the vector 
			//If the number of elements is smaller than or equal to three , there's no need to sort.(they are sorted already.) 
			if((right - left) >= SORTED_ALREADY) {
				pivot=elements[center]; // the pivot will be the center element computed earlier.
				AuxMethods.swapElements(elements, center, right);
				do {
					
					while(elements[i]<=pivot && i<right)i++;
					while(elements[j] >= pivot && j> left)j--;
					//we'll evaluate the elements to the left and to the right of the pivot.
					//the pivot it's like a median:the elements to its left must be smaller than it . 
					//the elements to the right must be greater than it.
					if(i<j) {//
						AuxMethods.swapElements(elements, i, j);
					}}while(i<j);//when the two loops cross, it's time to stop.
				AuxMethods.swapElements(elements, i,right); //we set the position of the pivot
				quickSort(left, i-1);
				quickSort(i+1, right);
			}
			}
		}
	

	@Override
	public void sort() {
		quickSort(0, elements.length-1);		
	}
	
	@Override
	public String getName() {
		return "Quicksort - Central element";
	} 
} 

package algstudent.s2;

/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the SELECTION */
public class Selection extends Vector {
	public Selection(int nElements) {
		super(nElements);
	}
	/**
	 * This method works the following way:
	 * for each iteration ,it takes a sub-array from index i to index=length-1 using method minPos.
	 * This method, also looks for the minimum element in that sub array, and returns it's position. 
	 * After finding the position of the minimum element, in array [i..n-1], the element in position i is 
	 * interchanged with the smallest element of the sub-array. 
	 * IN CONCLUSION: Each iteration , the smallest element is swapped by the element in the first position of the sub-array.
	 */
	@Override
	public void sort() {
		int minpos;
		for (int i = 0; i < elements.length-1; i++) {
			minpos=AuxMethods.minPos(elements, i);
			elements=AuxMethods.swapElements(elements, i, minpos);
			
		}
	}  
	
	

	@Override
	public String getName() {
		return "Selection";
	} 
} 

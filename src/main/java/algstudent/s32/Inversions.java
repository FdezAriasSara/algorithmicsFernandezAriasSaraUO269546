package algstudent.s32;

import java.util.List;

public class Inversions {
//must have a O(nlogn)-> for that the divide and conquer scheme must be a=b^k, using divide and conquer by division.
	int[] ranking;
	

	public Inversions(List<Integer> ranking) {
		this.ranking = new int[ranking.size()];
		for (int i = 0; i < ranking.size(); i++) {
			this.ranking[i] = ranking.get(i);
		}
	}

	public String start() {

		return String.valueOf(sort(0, this.ranking.length - 1));
	}

	private long sort(int l, int r) {
		long inversions = 0;
		if (r > l) {
			int middle = (l + r) / 2;
			//two recursive calls=> a=2 . Each time the vector is divided in half its size ,
			//then b=2. The rest of the calls provide a complexity of O(n) and so k=1.
			//therefore O(n^k*logn)=(nlogn)

			inversions += sort(middle + 1, r);
			inversions += sort(l, middle);
			inversions += inversions(l, middle, middle + 1, r);
		}

		return inversions;
	}
/**
 * Method that compares the two halves of the array passed as parameter and counts the number of inversions found when comparing them.
 * @return the number of inversions counted
 */
	private long inversions(int left, int middle, int mid1, int right) {

		long inversions = 0;
		// computing the size of the auxiliar arrays,x and y .
		int sizeLeft = middle - left + 1;
		int sizeRight = right - mid1 + 1;
		// auxiliar array initialization
		int lArray[] = new int[sizeLeft];
		int rArray[] = new int[sizeRight];

		// filling the auxiliar arrays with the elements to be evaluated.
		for (int i = 0; i < sizeLeft; i++) {
			lArray[i] = ranking[left + i];
		}
		for (int j = 0; j < sizeRight; j++) {
			rArray[j] = ranking[mid1 + j];
		}

		// for the while loop
		int leftIndex = 0;
		int rightIndex = 0;
		int rankingIndex=left;
		while ((leftIndex < lArray.length) && (rightIndex <rArray.length)) { // while there are values left to explore
			if (lArray[leftIndex] <= rArray[rightIndex]) {
				ranking[rankingIndex]=lArray[leftIndex];
				// we take the next value of array x, since the current one was already stored
				// in sorted.
				rankingIndex++;
			    leftIndex++;
				// we don't increment the index for array y since the element has not been
				// stored yet.
			} else {
				ranking[rankingIndex]=rArray[rightIndex];
				inversions+=mid1-(left+ leftIndex);//The elements from mid1 to the elment where the inversion was detected will produce an inversion too
				rankingIndex++;
				rightIndex++;// we take the next value of array y, since the current one was already stored
						// in sorted.

			}

		}
		//inserting into the ranking the values that are left both from x and y.
		while (leftIndex < lArray.length) {
			
			ranking[rankingIndex] = lArray[leftIndex];
			rankingIndex++;
			leftIndex++;
		}
	    while (rightIndex < rArray.length) {
	    	ranking[rankingIndex] = rArray[rightIndex];
	    	rankingIndex++;
			rightIndex++;
	    }
	    	

		return inversions;
	}
	
	
}

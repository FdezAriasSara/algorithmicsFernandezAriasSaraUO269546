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

		return String.valueOf(sort(0, this.ranking.length - 1, this.ranking));
	}

	private long sort(int l, int r, int[] ranking) {
		long inversions = 0;
		if (r > l) {
			int middle = (l + r) / 2;
			//two recursive calls=> a=2 . Each time the vector is divided in half its size ,
			//then b=2. The rest of the calls provide a complexity of O(n) and so k=1.
			//therefore O(n^k*logn)=(nlogn)

			inversions += sort(middle + 1, r, ranking);
			inversions += sort(l, middle, ranking);
			inversions += inversions(l, middle, middle + 1, r, ranking);
		}

		return inversions;
	}
/**
 * Method that compares the two halves of the array passed as parameter and counts the number of inversions found when comparing them.
 * @return the number of inversions counted
 */
	private long inversions(int left, int middle, int mid1, int right, int[] ranking) {

		long inversions = 0;
		// computing the size of the auxiliar arrays,x and y .
		int sizeX = middle - left + 1;
		int sizeY = right - mid1 + 1;
		// auxiliar array initialization
		int x[] = new int[sizeX];
		int y[] = new int[sizeY];

		// filling the auxiliar arrays with the elements to be evaluated.
		for (int i = 0; i < sizeX; i++) {
			x[i] = ranking[left + i];
		}
		for (int j = 0; j < sizeY; j++) {
			y[j] = ranking[mid1 + j];
		}

		// for the while loop
		int xi = 0;
		int yi = 0;

		while ((xi < sizeX) && (yi < sizeY)) { // while there are values left to explore
			if (x[xi] <= y[yi]) {
				// we take the next value of array x, since the current one was already stored
				// in sorted.
				xi++;
				// we don't increment the index for array y since the element has not been
				// stored yet.
			} else {
				inversions += (middle - xi);
				yi++;// we take the next value of array y, since the current one was already stored
						// in sorted.

			}

		}

		return inversions;
	}

}

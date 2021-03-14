package algstudent.s32;

import java.util.List;

public class Inversions {
//must have a O(nlogn)-> for that the divide and conquer scheme must be a=b^k, using divide and conquer by division.
	List<Integer> ranking;

	public Inversions(List<Integer> ranking) {
		this.ranking = ranking;
	}

	public String start() {
		long inversions = 0;
		inversions = countInversions(0, ranking.size() - 1, this.ranking,0);

		return String.valueOf(inversions);
	}

	private long countInversions(int l, int r, List<Integer> ranking, int inversions ) {
		
		if (r > l) {
			int middle = (l + r) / 2;
			countInversions(l, middle, ranking,inversions);
			countInversions(middle + 1, r, ranking,inversions);// a=2. k=1
			inversions += combineAll(l, middle, middle + 1, r, ranking);
		}
		return inversions;
	}

	private long combineAll(int x1, int x2, int y1, int y2, List<Integer> ranking) {
		// counter of inversions
		long counter = 0;
		// computing the size of the auxiliar arrays,x and y .
		int sizeX = x2 - x1 + 1;
		int sizeY = y2 - y1 + 1;
		// auxiliar array initialization
		int x[] = new int[sizeX];
		int y[] = new int[sizeY];

		// filling the auxiliar arrays with the elements to be evaluated.
		for (int i = 0; i < sizeX; i++) {
			x[i] = ranking.get(x1 + i);
		}
		for (int j = 0; j < sizeY; j++) {
			x[j] = ranking.get(y1 + j);
		}
		int xi = 0;
		int yi = 0;

		while (y[yi] == Integer.MAX_VALUE && x[xi] == Integer.MAX_VALUE) { // while there are values left to explore
			if (x[xi] < y[yi]) {// if the value of xi is smaller than yi
				// since there's no need to sort, only to count the inversions, the only thing
				// that happens
				// here is that we increment the index or we set the array as visited.
				if (xi == sizeX - 1) {
					x[xi] = Integer.MAX_VALUE;// when there are no values of x left.
				} else {

					xi++;// if values of x are left
				}
			} else if (y[yi] < x[xi]) {// if the value of yi is smaller than xi-> disordered.
				counter++;// if a value of y is smaller than the value of x , an inversion is produced
				if (yi == sizeY - 1) {
					y[yi] = Integer.MAX_VALUE;// when there are no values of y left.
				} else {

					yi++;// if values of y are left
				}
			}

		}

		return counter;

	}

}

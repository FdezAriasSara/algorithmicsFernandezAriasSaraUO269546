package algstudent.s32;


import java.util.List;

public class Inversions {
//must have a O(nlogn)-> for that the divide and conquer scheme must be a=b^k, using divide and conquer by division.
	int[] ranking;
	long inversions=0;

	public Inversions(List<Integer> ranking) {
		this.ranking=new int[ranking.size()];
		for (int i = 0; i < ranking.size(); i++) {
			this.ranking[i]=ranking.get(i);
		}
	}

	public String start() {
		
		return String.valueOf(countInversions(0, this.ranking.length - 1, this.ranking));
	}

	private long countInversions(int l, int r,  int[] ranking) {

		if (r > l) {
			int middle = (l + r) / 2;
			countInversions(l, middle, ranking);
			countInversions(middle + 1, r, ranking);// a=2. k=1 (since combine all has a complexity of O(n)) and b=2 
			combineAll(l, middle, middle + 1, r);
			
		}
		return this.inversions;
	}

	private void combineAll(int x1, int x2, int y1, int y2) {
		
		
		// computing the size of the auxiliar arrays,x and y .
		int sizeX = x2 - x1 + 1;
		int sizeY = y2 - y1 + 1;
		// auxiliar array initialization
		int x[] = new int[sizeX];
		int y[] = new int[sizeY];
		//auxiliar sorted array
		
		int sorted[] = new int[sizeX+sizeY];
   
		// filling the auxiliar arrays with the elements to be evaluated.
		for (int i = 0; i < sizeX; i++) {
			x[i] = this.ranking[x1 + i];
		}
		for (int j = 0; j < sizeY; j++) {
			y[j] =this.ranking[y1 + j];
		}
		//for the while loop
		int xi = 0;
		int yi = 0;	
		int si=0;//sorted array.
		
		while (xi<sizeX && yi<sizeY) { // while there are values left to explore
			if(x[xi]<=y[yi]) {				
				sorted[si]= x[xi];				
				xi++;//we take the next value of array x, since the current one was already stored in sorted.
				si++;
				//we don't increment the index for array x since the element has not been stored yet.
			}else if(x[xi]>=y[yi]) {
				this.inversions+=1;
				sorted[si]= y[yi];
					yi++;//we take the next value of array y, since the current one was already stored in sorted.
					si++;
				}
			//adding the left elements once all the smaller ones have been added.
			else if(yi<sizeY) {
				sorted[si]=yi;
				yi++;
				si++;
				
			}
			else {
				this.inversions+=1;
				sorted[si]=xi;
				xi++;
				si++;
			}
			}
			int ri=0;
			si=0;
			while (si<sorted.length) {
				this.ranking[ri]=sorted[si];
				si++;
				ri++;
			}
			
	
	}

}

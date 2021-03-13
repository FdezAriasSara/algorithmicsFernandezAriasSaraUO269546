package algstudent.s32;

import java.util.List;

public class Inversions {
//must have a O(nlogn)-> for that the divide and conquer scheme must be a=b^k, using divide and conquer by division.
	List<Integer> ranking;
	
	public Inversions(List<Integer> ranking) {
		this.ranking=ranking;
	}

	public String start() {
		long inversions=0;
		inversions=countInversions(0,ranking.size()-1,this.ranking);
		
		return String.valueOf(inversions);
	}

	private long countInversions(int l, int r, List<Integer> ranking) {
		long inversions=0;
		if(r>l) {
			int middle=(l+r)/2; 
			countInversions(l,middle,ranking);
			countInversions(middle+1, r, ranking);//a=2. k=1
			inversions+=combineAll(l, middle, middle+1,r, ranking);
		}
		return inversions;
	}

	private long combineAll(int x1, int x2, int y1, int y2, List<Integer> ranking) {
		//counter of inversions
		long counter=0;
		//computing the size of the auxiliar arrays,x and y .
		int sizeX=x2-x1+1;
		int sizeY=y2-y1+1;
		//auxiliar array initialization
		int x[]= new int[sizeX];
		int y[]= new int[sizeY];
		
		//filling the auxiliar arrays with the elements to be evaluated.
		for (int i = 0; i < sizeX; i++) {
			x[i]=ranking.get(x1+i);
		}
		for (int j = 0; j < sizeY; j++) {
			x[j]=ranking.get(y1+j);
		}
		
		//CONDITION?
//			if(x[xi]<y[yi]) {
//				ranking.add(i,x[i]);
//				xi++;
//				//if the left number is smaller , theres no inversion.
//			}else if(y[i]<x[i]) {
//				ranking.add(i,y[i]);
//				counter++;//if a number at the right is smaller than a number at the left-> inversion
//			}
		
		}
		
		return counter;
		
	}

}

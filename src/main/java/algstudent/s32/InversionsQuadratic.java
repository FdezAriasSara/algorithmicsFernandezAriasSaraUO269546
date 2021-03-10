package algstudent.s32;

import java.util.List;

public class InversionsQuadratic {
private List<Integer> ranking;

//like brute forze.
	
	/**
	 * Evaluating all the possibilities by using nested loops. 
	 * @param ranking
	 */
	public InversionsQuadratic(List<Integer> ranking) {
		this.ranking=ranking;
		

	}

	public String start() {
		int element;
		long inversions=0;//IMPORTANT to use long else overflow might happen.
		for (int i = 0; i < ranking.size(); i++) {
			element=ranking.get(i);
			for (int j = i+1; j<ranking.size(); j++) {
			 if(element>ranking.get(j)) {
				 
				 inversions++;
			 }
				}
			}
		return String.valueOf(inversions);
	}

}

package algstudent.s32;

import java.util.List;

public class InversionsQuadratic {
//like brute forze.
	
	/**
	 * Evaluating all the possibilities by using nested loops. 
	 * @param ranking
	 */
	public InversionsQuadratic(List<Integer> ranking) {
		
		for (int i = 0; i < ranking.size(); i++) {
			for (int j =  ranking.size()-1; j>0; j++) {
			 if(ranking.get(j)<ranking.get(i)) {
				 
				 
			 }
				}
			}
		

	}

	public String start() {
		// TODO Auto-generated method stub
		return null;
	}

}

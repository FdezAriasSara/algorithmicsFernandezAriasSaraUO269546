package algstudent.s4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SegmentsPlacement {
	List<Integer> segments;
	int numberOfSegments;
	public SegmentsPlacement(List<Integer> segments) {
		this.segments=new ArrayList<Integer>(segments);
	}
	/**
	 * Applying the ostrich algorithm. The segments are placed in the same order in
	 * which they appear in the file and in the end every solution gives the same
	 * cost.
	 * 
	 * @return
	 */
	public long greedy1() {
		int[] nonSorted=copyToArray(segments);
		return computeCost(nonSorted);
	}
	/**
	 *Placing them from longest to shortest length.
	 * 
	 * @return
	 */
	public long greedy2() {
		
		int[] sorted=sortAscendantOrder();
		
		return computeCost(sorted);
	}
	/**
	* From shortest to longest length.
	 * @return
	 */
	public long greedy3() {
		int[] sorted=sortDescendantOrder();
		return computeCost(sorted);
	}
/**
 * Copies elements from an list of integers into an array in the same exact order they appear.
 * @param list
 * @return the elements in array.
 */
	private int[] copyToArray(List<Integer> list) {
		int[] toReturn=new int[list.size()];
		for (int i = 0; i < toReturn.length; i++) {
			toReturn[i]=list.get(i);
		}
		return toReturn;
	}
	/**
	 * Computes the cost in PUFOSOS of segments by the addition of their midpoints.
	 * @param arrayOfSegments
	 * @return the cost of a game.
	 */
	private long computeCost(int[] arrayOfSegments) {
		int start=0;
		long cost=0;
		int end;
		for (int si = 0; si <arrayOfSegments.length-1; si++) {
			end=start+arrayOfSegments[si ];
			cost+=(start+end)/2;
			start=end;
		}
		return cost;
	}

	private int[] sortAscendantOrder() {
		List<Integer> sorted=new ArrayList<Integer>(segments);//Copy constructor
		
		for (int i = 0; i < sorted.size(); i++) {
			if(sorted.get(i+1)<sorted.get(i)) {
				Collections.swap(sorted, i, i+i);
				
			}
		}
		return copyToArray(sorted);
	}
	private int[] sortDescendantOrder() {
	List<Integer> sorted=new ArrayList<Integer>(segments);//Copy constructor
		
		for (int i = 0; i < sorted.size(); i++) {
			if(sorted.get(i+1)>sorted.get(i)) {
				Collections.swap(sorted, i, i+i);
				
			}
		}
		return copyToArray(sorted);
	}
	

	// inspired in readRankingFromFile of session3.
	public List<Integer> readGameFromFile(String file) {
		BufferedReader fich = null;
		String line;
		List<Integer> segments = new ArrayList<Integer>();

		try {
			fich = new BufferedReader(new FileReader(file));
			line=fich.readLine();//first line tells the number of segments
			this.numberOfSegments = Integer.parseInt(line); 
			while (line != null && numberOfSegments!=0) {
				segments.add(Integer.parseInt(line));
				line = fich.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("There is no file: " + file);
		} catch (IOException e) {
			System.out.println("Error reading the file: " + file);
		} finally {
			if (fich != null)
				try {
					fich.close();
				} catch (IOException e) {
					System.out.println("Error closing the file: " + file);
					e.printStackTrace();
				}
		}

		return segments;
	}
}

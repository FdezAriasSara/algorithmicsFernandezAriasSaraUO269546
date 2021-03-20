package algstudent.s4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SegmentsPlacement {
	int[] segments;
	int numberOfSegments;
	public SegmentsPlacement(List<Integer> segments) {
		this.segments=new int[segments.size()];
		for (int i = 0; i < segments.size(); i++) {
			this.segments[0]=segments.get(i);
		}
	}
	/**
	 * Applying the ostrich algorithm. The segments are placed in the same order in
	 * which they appear in the file and in the end every solution gives the same
	 * cost.
	 * 
	 * @return
	 */
	public long greedy1() {
	
		return computeCost(segments);
	}
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
	private int[] sortAscendantOrder() {
		int[] sorted=new int[segments.length];
		
		for (int i = 0; i < sorted.length-1; i++) {
			if(segments[i+1]<segments[i]) {
				sorted[i]=segments[i+1];
				sorted[i+1]=segments[i];
			}else {
				sorted[i+1]=segments[i+1];
				sorted[i]=segments[i];
			}
		}
		return sorted;
	}
	private int[] sortDescendantOrder() {
		int[] sorted=new int[segments.length];
		
		for (int i = 0; i < sorted.length-1; i++) {
			if(segments[i+1]>segments[i]) {
				sorted[i]=segments[i+1];
				sorted[i+1]=segments[i];
			}else {
				sorted[i+1]=segments[i+1];
				sorted[i]=segments[i];
			}
		}
		return sorted;
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

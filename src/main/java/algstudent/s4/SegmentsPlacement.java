package algstudent.s4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SegmentsPlacement {
	List<Integer> segments;
	int numberOfSegments;
	/**
	 * Applying the ostrich algorithm. The segments are placed in the same order in
	 * which they appear in the file and in the end every solution gives the same
	 * cost.
	 * 
	 * @return
	 */
	public long greedy1() {
		long cost = 0;
		for (int si = 0; si <segments.size()-1; si++) {
			
		}
		
		return cost;
	}
	/**
	 *Placing them from longest to shortest length.
	 * 
	 * @return
	 */
	public long greedy2() {
		long cost = 0;
		return cost;
	}
	/**
	* From shortest to longest length.
	 * @return
	 */
	public long greedy3() {
		long cost = 0;
		return cost;
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

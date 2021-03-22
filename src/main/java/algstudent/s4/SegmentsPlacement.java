package algstudent.s4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SegmentsPlacement {
	List<Integer> segments;
	int numberOfSegments;
	
	public SegmentsPlacement(String filename) {
		
		this.segments=readGameFromFile(filename);
	}
	public static void main(String args[]) {
		long total;
		//###############################################GAME 1#################################################
		String fileName = Paths.get("").toAbsolutePath().toString() + "/src/main/java/algstudent/s4/game1.txt";
		SegmentsPlacement segPlace= new SegmentsPlacement(fileName);
		System.out.println("###############################################GAME 1#################################################");
		System.out.println("The solution using the ostrich algorithim  (greedy 1) is :");             
        total=segPlace.greedy1();                				
        System.out.println("Total cost of greedy1 :"+total);    
        
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("The solution using the greedy 2 is :");             
        total=segPlace.greedy2();                				
        System.out.println("Total cost of greedy2 :"+total);    
        
        System.out.println("--------------------------------------------------------------------------------------------------");
        
        System.out.println("The solution using the algorithim  greedy3 is :");             
        total=segPlace.greedy3();                				
        System.out.println("Total cost of greedy3 :"+total);    
        System.out.println("###############################################GAME 2#################################################");
		//###############################################GAME 2#################################################
         fileName = Paths.get("").toAbsolutePath().toString() + "/src/main/java/algstudent/s4/game2.txt";
		 segPlace= new SegmentsPlacement(fileName);
		System.out.println("The solution using the algorithim  (greedy 1) is :");             
        total=segPlace.greedy1();                				
        System.out.println("Total cost of greedy1 :"+total);    
        
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("The solution using the greedy 2 is :");             
        total=segPlace.greedy2();                				
        System.out.println("Total cost of greedy2 :"+total);    
        
        System.out.println("--------------------------------------------------------------------------------------------------");
        
        System.out.println("The solution using the algorithim  greedy3 is :");             
        total=segPlace.greedy3();                				
        System.out.println("Total cost of greedy3 :"+total);    
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
		printSolution(nonSorted);
		return computeCost(nonSorted);
	}
	private void printSolution(int[] elements) {
		
			
			
		int start=0;
		int end;
		long midpoint;
		for (int i = 0; i < elements.length; i++) {
			end=start+elements[i];
			midpoint=(start+end)/2;
			System.out.println(String.format("(%d,%d) midpoint:%d",start,end,midpoint));
			start=end;
		}
			
		}
	
	/**
	 *Placing them from longest to shortest length.
	 * 
	 * @return
	 */
	public long greedy2() {
		
		int[] sorted=sortDescendantOrder();
		printSolution(sorted);
		return computeCost(sorted);
	}
	/**
	* From shortest to longest length.
	 * @return
	 */
	public long greedy3() {
		int[] sorted=sortAscendantOrder();
		printSolution(sorted);
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

		 for (int i= 0 ; i < sorted.size(); i++) { 
	            for (int j = i + 1; j < sorted.size(); j++) { 
	                if (sorted.get(i) > sorted.get(j)) 
	                {
	                	Collections.swap(sorted, i, j);

	                }
	            }
	        
		}System.out.println(sorted);
		return copyToArray(sorted);
		
	}
	private int[] sortDescendantOrder() {
	List<Integer> sorted=new ArrayList<Integer>(segments);//Copy constructor
	
		 for (int i= 0 ; i < sorted.size(); i++) { 
	            for (int j = i + 1; j < sorted.size(); j++) { 
	                if (sorted.get(i) < sorted.get(j)) 
	                {
	                	Collections.swap(sorted, i, j);

	                }
	            }
	        
		}System.out.println(sorted);
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

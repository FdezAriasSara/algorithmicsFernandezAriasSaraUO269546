package algstudent.s7.support;

import java.io.BufferedReader;
import java.io.FileReader;

import algstudent.s7.util.SongsProblem.BlockGenerator;
import algstudent.s7.util.SongsProblem.BlocksOfSongs;

public class SongsProblemTest {
	private boolean executeFromFile(String file) {
		boolean result=false;
		BufferedReader br;
		try {
			br=new BufferedReader(new FileReader(file));
			String blength=br.readLine();
			 int numOfSongs=Integer.parseInt(blength);//the first line contains the length the blocks should have.
			
			BlocksOfSongs blocks=new BlocksOfSongs();
			
			//now we retrieve all songs from the list.
			for (int i = 0; i < numOfSongs; i++) {
				String[] data= br.readLine().split("\t");
				blocks.addSongFromFile(data);
			}
			BlockGenerator generator= new BlockGenerator(blocks);
			//Apply branch and bound
			generator.branchAndBound(generator.getRootNode());
			generator.printSolutionTrace();
			
			
			//if there's no best solution the best node is null and so result=false.
			result=generator.getBestNode() !=null?true: false;
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return result;
	}

}

package algstudent.s7.support;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;

import org.junit.Test;

import algstudent.s7.support.SongsProblem.BlockGenerator;
import algstudent.s7.support.SongsProblem.BlocksOfSongs;

public class SongsProblemTest {
	@Test
	public void testFile01() {
		String filename = Paths.get("").toAbsolutePath().toString() + "/src/main/java/algstudent/s7/Lista01.txt";
		boolean result=executeFromFile(filename,"20:0");
		assertEquals(true,result);
	}
	@Test
	public void testFile02() {
		String filename = Paths.get("").toAbsolutePath().toString() + "/src/main/java/algstudent/s7/Lista02.txt";
		boolean result=executeFromFile(filename,"20:0");
		assertEquals(true,result);
	}
	@Test
	public void testFile03() {
		String filename = Paths.get("").toAbsolutePath().toString() + "/src/main/java/algstudent/s7/Lista03.txt";
		boolean result=executeFromFile(filename,"20:0");
		assertEquals(true,result);

	}

	private boolean executeFromFile(String file,String blength) {
		boolean result = false;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String numberOfSongs = br.readLine();
			int numOfSongs = Integer.parseInt(numberOfSongs);// the first line contains the number of songs in the list.
			
			BlocksOfSongs blocks = new BlocksOfSongs(numOfSongs,blength);

			// now we retrieve all songs from the list.
			for (int i = 0; i < numOfSongs; i++) {
				String[] data = br.readLine().split("\t");
				blocks.addSongFromFile(data);
				
			}	
	
			
			BlockGenerator generator = new BlockGenerator(blocks);
	

			// Apply branch and bound
			generator.branchAndBound(generator.getRootNode());

			generator.printSolutionTrace();
		
			// if there's no best solution the best node is null and so result=false.
			result = generator.getBestNode() != null ? true : false;

		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return result;
	}

}

package algstudent.s6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BestList {
	public static final int EMPTY = 0;
	private float blockTime;// time the block has as a constraint.

	private List<Song> songsFromFile;// songs retrieved from the file.
	private int songsToEvaluate;
	private List<Song> blockA;// definitive solution for block a.
	private List<Song> blockB;// definitive solution for block b

	private List<Song> currentSolutionA = new ArrayList<Song>();// we create a block song by song.
	private List<Song> currentSolutionB = new ArrayList<Song>();// we create a block song by song.


	private int counter;// counter of calls to backtracking method(nodes of the tree)
	private static String filepath = Paths.get("").toAbsolutePath().toString()
			+ "/src/main/java/algstudent/s6/List01.txt";

	public static void main(String[] args) {

		float time = parseToSeconds(args[0]);
		BestList bl = new BestList(time, filepath);
		bl.backtracking(0);
		bl.printSolution();

	}

	public BestList(float time, String filename) {
		this.blockTime = time;
		this.blockA = new ArrayList<Song>();
		this.blockB = new ArrayList<Song>();
		readFromFile(filename);

	}

	private static float parseToSeconds(String string) {
		String[] time = string.split(":");// since time is given in min:sec format.
		return 60 * Integer.valueOf(time[0]) + Integer.valueOf(time[1]);// Working with seconds as recommended
	}

	private static String parseToMinutesAndSeconds(float sec) {

		int totl = (int) Math.floor(sec/60);
		int secs = Math.round(sec % 60);
		
		return totl+":"+String.format("%02d", secs);
	}

	private boolean isValid(Song song, int level, List<Song> block) {
		
		// Is the song small enough to fit into the block?
		if (level == EMPTY) {
			return song.getDuration() <= blockTime;// if the list is empty , we'll just have to check if the length is
													// smaller than the total length
		} else {// if the list isn't empty , the sum of all previous songs durations + current
				// song cannot be greater than the block's fixed duration.
			float total = song.getDuration();
			for (int i = 0; i < block.size(); i++) {
				total += block.get(i).getDuration();
			}

			return total <= blockTime;// if the sum of all durations including the current one is smaller than the
										// time of the block , the song we are working with is valid.
		}

	}

//This method will have a complexity of O(3^n)
	private void backtracking(int level) {
		
		Song song = null;
		if (level == songsToEvaluate - 1) {// When level is equal to the number of songs, we've reached a leaf.			
			counter++;
			
			if(validSolution()) {//if the solution can be considered
			
				keepBestSolution(currentSolutionA, currentSolutionB);//check if its better or worse.
			}
				
		} else {		
				song = songsFromFile.get(level);
				
					// option1 : the song is not included in ANY block
					

						
						backtracking(level + 1);
						counter++;

					// option2: the song is included in block a.
					if (isValid(song, level, currentSolutionA)) {
					
						currentSolutionA.add(song);
						counter++;
						backtracking(level + 1);
					
						currentSolutionA.remove(song);
						// If remove is not used, once the execution goes "out" the element would still
						// be there therefore solutions would be mixed.-> the recursive calls will make you work
						// with the same list in total different instances-> similar to resource sharing in threads

					
					}
					// option3: the song is included in block b.
					if (isValid(song, level, currentSolutionB)) {
				
						currentSolutionB.add(song);
						counter++;
						backtracking(level + 1);
						
						currentSolutionB.remove(song);
					
				}

			}
		}
	
/**
 * Method used to check that the current solution's block's don't share songs.
 * @return true if the solution is valid (the blocks have different songs) , false otherwise.
 */
	private boolean validSolution() {
		for (Song song : currentSolutionB) {
			for (Song songA : currentSolutionA) {
				
				if(song.equals(songA)) {
					
					return false;
				}
			}
		}
			return true;
		
	
}

	/**
	 * We look for the greatest block possible (greatest time used) with the best
	 * score (greatest score).
	 * This method follows the following logic:
	 * @param currentSolutionA2 part of the solution in the  leaf reached in this call
	 * @param currentSolutionB2 part of the solution in the  leaf reached in this call
	 * BlockA , and blockB store the previous solution that was evaluated as best. 
	 * (the first time a solution is reached, it will be stored no matter if it's optimal or not, since it will be better than nothing(score>0)
	 *
	 *It's important to take into account that both currentSolutionA and currentSolutionB are part of the same SOLUTION , as well
	 *as blockA and Blockb. That means that they will be evaluated as a whole respectively.
	 *
	 *
	 */
	private void keepBestSolution(List<Song> currentSolutionA, List<Song> currentSolutionB) {
	
		float prevScoreA = getTotalScore(this.blockA);
		float prevScoreB = getTotalScore(this.blockB);
		float sumaBlock = prevScoreA + prevScoreB;
		float currentScoreA = getTotalScore(currentSolutionA);
		float currentScoreB = getTotalScore(currentSolutionB);
		float sumaCurrent = currentScoreA + currentScoreB;
		if (sumaBlock < sumaCurrent) {
			
			this.blockA = new ArrayList<Song>(currentSolutionA);//VERY important to use copy constructor instead of blockA=currentSolutionA-> else it will create an empty list.
			this.blockB = new ArrayList<Song>(currentSolutionB);
		}
		

			
		
	}
/**
 * 
 * @param block,list that contains a series of songs.
 * @return the total score of the songs of the block passed as parameter.
 */
	private float getTotalScore(List<Song> block) {
		float score = 0;
		for (Song song : block) {
			score += song.getScore();
		}
		return score;
	}

	// is recommended to work with seconds , then translate it with dividing by 60
//is also recommended to use Collections api .
//The goal is for the block to have the best songs(highest score ones) that fit in the conditions a block needs to fulfill.
	private void readFromFile(String fileName) {
		songsFromFile = new ArrayList<Song>();
		BufferedReader reader = null;
		String[] line;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			if (reader.ready()) {
				songsToEvaluate = Integer.valueOf(reader.readLine());
			}
			while (reader.ready()) {

				line = reader.readLine().split("\t");
				songsFromFile.add(new Song(line[0], parseToSeconds(line[1]), Integer.valueOf(line[2])));
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public int getCounter() {
		return counter;
	}

	public void printListInfo(List<Song> songsFrom) {
		for (Song song : songsFrom) {
			System.out.println(String.format("id:%s seconds:%s score:%d", song.getCode(),
					parseToMinutesAndSeconds(song.getDuration()), song.getScore()));
		}

	}
	


	public void printTotals() {

		System.out.println(String.format("Length of the blocks:%s", parseToMinutesAndSeconds(blockTime)));
		System.out.println(String.format("Total score:%.1f", getTotalScore(blockA) + getTotalScore(blockB)));
		System.out.println(String.format("Total count:%d", getCounter()));
	}

	public void printSolution() {

		System.out.println("List of songs:");
		printListInfo(songsFromFile);
		System.out.println();
		printTotals();
		System.out.println();
		System.out.println("Best block A:");
		printListInfo(blockA);
		System.out.println();
		System.out.println("Best block B:");
		printListInfo(blockB);
		System.out.println();
	}

}

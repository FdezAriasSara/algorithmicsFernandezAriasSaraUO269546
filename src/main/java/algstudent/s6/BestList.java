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

	private boolean over;// to see if it should stop
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
			over = true;
			if(validSolution()) {
		
				keepBestSolution(currentSolutionA, currentSolutionB);
			}
				
		} else {		
				song = songsFromFile.get(level);
				//if (!over) {// while this solution is not completed.
					// option1 : the song is not included in ANY block
					//if (!isValid(song, level, currentSolutionA) && !isValid(song, level, currentSolutionB)) {
			
						backtracking(level + 1);

					//}

					// option2: the song is included in block a.
					if (isValid(song, level, currentSolutionA)) {
					
						currentSolutionA.add(song);
						backtracking(level + 1);
						currentSolutionA.remove(song);
					
					}
					// option3: the song is included in block b.
					if (isValid(song, level, currentSolutionB)) {
				
						currentSolutionB.add(song);
						backtracking(level + 1);
						currentSolutionB.remove(song);
					
				}

			}
		}
	

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
	 * score (greatest score)
	 * 
	 * @param currentSolutionA2
	 * @param currentSolutionB2
	 */
	private void keepBestSolution(List<Song> currentSolutionA, List<Song> currentSolutionB) {
		// if current calculated block A is a better solution that the previous , block
		// a will be = to the new.
		float prevScoreA = getTotalScore(this.blockA);
		float prevScoreB = getTotalScore(this.blockB);
		float sumaBlock = prevScoreA + prevScoreB;
		float currentScoreA = getTotalScore(currentSolutionA);
		float currentScoreB = getTotalScore(currentSolutionB);
		float sumaCurrent = currentScoreA + currentScoreB;
		if (sumaBlock < sumaCurrent) {
			
			this.blockA = new ArrayList<Song>(currentSolutionA);
			this.blockB = new ArrayList<Song>(currentSolutionB);
		}
		// if current calculated block B is a better solution that the previous , block
		// b will be = to the new.
		

			
		
	}

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

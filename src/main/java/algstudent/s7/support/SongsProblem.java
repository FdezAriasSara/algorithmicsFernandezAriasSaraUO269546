package algstudent.s7.support;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import algstudent.s6.Song;
import algstudent.s7.util.BranchAndBound;
import algstudent.s7.util.Node;

public class SongsProblem {

	// Since these classes are internal , they need to be static in order to be
	// instantiated.
	public static class BlockGenerator extends BranchAndBound {// to solve a reduced version of branch and bound

		public BlockGenerator(BlocksOfSongs start) {// in this problem it would be a BlocksOfSongs object with two empty
													// blocks
			rootNode = start;

		}

	}

	public static class BlocksOfSongs extends Node {// to represent a node in the graph
		public static final int EMPTY = 0;
		private List<Song> blockA;
		private List<Song> blockB;
		private int blockLength;
		private List<Song> allSongs;
		private int numberOfSongs;

		public BlocksOfSongs(int numOfSongs, String blength) {

			this.blockA = new ArrayList<Song>();
			this.blockB = new ArrayList<Song>();
			this.allSongs = new ArrayList<Song>();
			this.numberOfSongs = numOfSongs;
			this.blockLength = parseToSeconds(blength);

		}

		// for child nodes.
		public BlocksOfSongs(List<Song> blockA, List<Song> blockB, List<Song> allSongs, int level, int numberOfSongs,
				int blength, UUID parentID) {

			this.blockA = new ArrayList<Song>(blockA);
			this.blockB = new ArrayList<Song>(blockB);
			this.allSongs = new ArrayList<Song>(allSongs);
			this.depth = level;
			this.parentID = parentID;
			this.numberOfSongs = numberOfSongs;
			this.blockLength = blength;

		}

		/**
		 * "Heuristics are criteria, methods or principles for deciding which among
		 * several alternative courses of action promises to be the most effective in
		 * order to achieve some goal Heuristic function at a node n is an estimate of
		 * the optimum cost from the current node n to a Goal and it is denoted by h(n)
		 * ." Using that logic
		 */
		@Override
		public void calculateHeuristicValue() {
			int counter = 0;
			if (prune()) {// when we prune a node,we want it to be removed / ignored

				this.heuristicValue = Integer.MAX_VALUE;// if we want this value to be ignored,we put a higher value,
														// representing that it's cost to the goal is not optimal
			} else {
				// we add 1 for each child node that separates the current one from the solution.
				for (int i =getDepth(); i <numberOfSongs; i++) {//there will be a level per song.
					counter++;
				}
				
					// in case the node has no children and it's not pruned, heuristic value =0.
					// Then , it's the solution.
				}
			
			this.heuristicValue = counter;
		}

		/**
		 * To get the child nodes of the current one. The children point to the parent
		 * through the inherited parentID field.
		 */
		@Override
		public ArrayList<Node> expand() {
			ArrayList<Node> result = new ArrayList<Node>();
			ArrayList<Song> auxA = new ArrayList<Song>(blockA);
			ArrayList<Song> auxB = new ArrayList<Song>(blockB);
			if (getDepth() < numberOfSongs - 1) {

				// branch and bound involves breadth first search, then, width will be explored
				// first instead of depth.
				int nextLevel = getDepth() + 1;// + 1;
				Song song = allSongs.get(nextLevel);
				// System.out.println(song.toString());
				auxA.add(song);// node: songs is added to block A
				result.add(
						new BlocksOfSongs(auxA, blockB, allSongs, nextLevel, numberOfSongs, blockLength, this.getID()));
				auxB.add(song);// node: songs is added to block A
				result.add(
						new BlocksOfSongs(blockA, auxB, allSongs, nextLevel, numberOfSongs, blockLength, this.getID()));
				// node: songs is added to NONE
				result.add(new BlocksOfSongs(blockA, blockB, allSongs, nextLevel, numberOfSongs, blockLength,
						this.getID()));
				// in this problem ,each node will have 3 children.

				// if no children the returned list is empty.
			}
			return result;
		}

		/**
		 * Check if the current solution is valid. If not valid, it should be pruned,
		 * since no solution starting from an incorrect point can be correct.
		 * 
		 * @return
		 */
		private boolean prune() {

			if (isValidSolution())
				return false;

			return true;
		}

		private boolean isValidSolution() {
			if (getBlockDuration(blockA) > blockLength || getBlockDuration(blockB) > blockLength)
				return false;
			else if (blockA.size() > numberOfSongs || blockB.size() > numberOfSongs) {
				return false;
			}

			return true;

		}

		@Override
		public String toString() {
			String str = "";
			str += "BLOCK A : \n";
			for (Song song : blockA) {
				str += String.format("id:%s seconds:%s score:%d \n", song.getCode(),
						parseToMinutesAndSeconds(song.getDuration()), song.getScore());
			}
			str += "BLOCK B : \n";
			for (Song song : blockB) {
				str += String.format("id:%s seconds:%s score:%d \n", song.getCode(),
						parseToMinutesAndSeconds(song.getDuration()), song.getScore());
			}
			return str;
		}

		private String parseToMinutesAndSeconds(float sec) {

			int totl = (int) Math.floor(sec / 60);
			int secs = Math.round(sec % 60);

			return totl + ":" + String.format("%02d", secs);
		}

		@Override
		public boolean isSolution() {			
			calculateHeuristicValue();
			return (heuristicValue == 0) ? true : false;// heuristic value=total score of the blocks .If hv=0 it's a
														// solution.
		}

		public void addSongFromFile(String[] data) {
			allSongs.add(new Song(data[0], parseToSeconds(data[1]), Integer.valueOf(data[2])));
		}

		private int parseToSeconds(String string) {
			String[] time = string.split(":");// since time is given in min:sec format.
			return 60 * Integer.valueOf(time[0]) + Integer.valueOf(time[1]);// Working with seconds as recommended
		}

		private float getBlockDuration(List<Song> block) {
			float duration = 0;
			for (Song song : block) {
				duration += song.getDuration();

			}
			return duration;
		}

		protected List<Song> getBlockA() {
			return blockA;
		}

		protected List<Song> getBlockB() {
			return blockB;
		}

		protected List<Song> getAllSongs() {
			return allSongs;
		}
	}
}

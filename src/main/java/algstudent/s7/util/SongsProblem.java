package algstudent.s7.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import algstudent.s6.Song;

public class SongsProblem {

	// Since these classes are internal , they need to be static in order to be
	// instantiated.
	public static class BlockGenerator extends BranchAndBound {// to solve a reduced version of branch and bound

		private List<Song> finalblockA;
		private List<Song> finalblockB;

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
		private UUID parentID;

		private int level;//depth,.
		//for the root node.
		public BlocksOfSongs() {

			this.blockA = new ArrayList<Song>();
			this.blockB = new ArrayList<Song>();
			this.allSongs = new ArrayList<Song>();
			level = 0;
		}
		//for child nodes.
		public BlocksOfSongs(List<Song> blockA, List<Song> blockB, List<Song> allSongs, int level, UUID parentID) {

			this.blockA = new ArrayList<Song>(blockA);
			this.blockB = new ArrayList<Song>(blockB);
			this.allSongs = new ArrayList<Song>(allSongs);
			this.level = level;
			this.parentID = parentID;
		}

		@Override
		public void calculateHeuristicValue() {
			
			if (prune()) {// when we prune a node,we want it to be removed / ignored
				heuristicValue = 0;
			} else {
		}
			heuristicValue = (int) (getTotalScore(blockA)+getTotalScore(blockB));

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

			// branch and bound involves breadth first search, then, width will be explored
			// first instead of depth.
			Song song = allSongs.get(level);
			auxA.add(song);// node: songs is added to block A
			result.add(new BlocksOfSongs(auxA, blockB, allSongs, level + 1, this.getID()));
			auxB.add(song);// node: songs is added to block A
			result.add(new BlocksOfSongs(blockA, auxB, allSongs, level + 1, this.getID()));
			// node: songs is added to NONE
			result.add(new BlocksOfSongs(blockA, blockB, allSongs, level + 1, this.getID()));
			//in this problem ,each node will have 3 children.
			return result;
		}
		/**
		 * Check if the current solution is valid.
		 * If not valid, it should be pruned, since no solution starting from an incorrect point can be correct.
		 * @return
		 */
		private boolean prune() {		
			
			if(isValidSolution())
				return false;
			
			return true;
		}
		//how do i control that the solutions are random ? 
		// how do i control that the solution reached is not always the same ? how do i control that the number of songs is respected?
		private boolean isValidSolution() {
			if(getBlockDuration(blockA)>blockLength || getBlockDuration(blockB)>blockLength)
				return false;
			else if(blockA.size()==numberOfSongs|| blockB.size()==numberOfSongs){
				return false;
			}
				
			return true;
		
	
	
		}
		@Override
		public boolean isSolution() {

			return (heuristicValue != 0) ? true : false;// heuristic value=total score of the blocks . If 0, it should
														// be pruned.
		}

		public void addSongFromFile(String[] data) {
			allSongs.add(new Song(data[0], parseToSeconds(data[1]), Integer.valueOf(data[2])));
		}

		private float parseToSeconds(String string) {
			String[] time = string.split(":");// since time is given in min:sec format.
			return 60 * Integer.valueOf(time[0]) + Integer.valueOf(time[1]);// Working with seconds as recommended
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
		private float getBlockDuration(List<Song> block) {
			float duration=0;
			for (Song song :block) {
				duration+=song.getDuration();
				
			}
			return duration;
		}
	}
}

package algstudent.s7.util;

import java.util.ArrayList;
import java.util.List;

import algstudent.s6.Song;

public class SongsProblem {


	//Since these classes are internal , they need to be static in order to be instantiated.
	public static class BlockGenerator extends BranchAndBound{//to solve a reduced version of branch and bound

		private List<Song> finalblockA;
		private List<Song> finalblockB;
		public BlockGenerator(BlocksOfSongs start) {//in this problem it would be a BlocksOfSongs object with two empty blocks
			rootNode=start;
		
		}
		
	}
	public static class BlocksOfSongs extends Node{//to represent a node in the graph
		public static final int EMPTY = 0;
		private List<Song> blockA;
		private List<Song> blockB;
		private int blockLength;
		private List<Song> allSongs;
		private int numberOfSongs;
	
		private int level;
		public  BlocksOfSongs() {
		
			this.blockA=new ArrayList<Song>();
			this.blockB=new ArrayList<Song>();
			this.allSongs=new ArrayList<Song>();
		}

		@Override
		public void calculateHeuristicValue() {
			int counter=0;
			if(prune()) {//when we prune a node,we want it to be removed / ignored
				heuristicValue=0;
			}else {
				
				
			}
			heuristicValue=counter;
			
		}
		/**
		 * To get the child nodes of the current one.
		 * The children point to the parent through the inherited parentID field.
		 */
		@Override
		public ArrayList<Node> expand() {
			ArrayList<Node> result =new ArrayList<Node>();
			
			BlocksOfSongs temp;
			
			return result;
		}
		private boolean prune() {
			boolean valid=false;
			for (int i=level;i<numberOfSongs;i++) {//we will obtain the solutions with the rest of the songs starting from the current one.
				Song song=allSongs.get(i);
				if(!fitsIntoBlock(song, level, blockA) ) {	
					if( fitsIntoBlock(song, level, blockB))
						valid= true;//?
				
				}else {
					
					if(fitsIntoBlock(song, level, blockA) )
						valid= true;//?
					
				}
				if(!valid)
					return true;//we want to prune so prune= yes because the song doesn't fit anywhere
			}
			return false;
		}
		@Override
		public boolean isSolution() {
			
			return (heuristicValue!=0)? true : false;//heuristic value=total score of the blocks . If 0, it should be pruned.
		}
		public void addSongFromFile(String[] data) {
			allSongs.add(new Song(data[0], parseToSeconds(data[1]), Integer.valueOf(data[2])));
		}
		private float parseToSeconds(String string) {
			String[] time = string.split(":");// since time is given in min:sec format.
			return 60 * Integer.valueOf(time[0]) + Integer.valueOf(time[1]);// Working with seconds as recommended
		}
		private boolean fitsIntoBlock(Song song, int level, List<Song> block) {
			
			// Is the song small enough to fit into the block?
			if (level == EMPTY) {
				return song.getDuration() <= blockLength;// if the list is empty , we'll just have to check if the length is
														// smaller than the total length
			} else {// if the list isn't empty , the sum of all previous songs durations + current
					// song cannot be greater than the block's fixed duration.
				float total = song.getDuration();
				for (int i = 0; i < block.size(); i++) {
					total += block.get(i).getDuration();
				}

				return total <= blockLength;// if the sum of all durations including the current one is smaller than the
											// time of the block , the song we are working with is valid.
			}

		}
	}
}



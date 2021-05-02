package algstudent.s7.util;

import java.util.ArrayList;
import java.util.List;

import algstudent.s6.Song;

public class SongsProblem {
	//Since these classes are internal , they need to be static in order to be instantiated.
	public static class BlockGenerator extends BranchAndBound{//to solve a reduced version of branch and bound
		public BlockGenerator(BlocksOfSongs start) {//in this problem it would be a BlocksOfSongs object with two empty blocks
			rootNode=start;
		}
		
	}
	public static class BlocksOfSongs extends Node{//to represent a node in the graph
		private List<Song> blockA;
		private List<Song> blockB;
		private int blockLength;
		private List<Song> allSongs;
		public  BlocksOfSongs() {
		
			this.blockA=new ArrayList<Song>();
			this.blockB=new ArrayList<Song>();
			this.allSongs=new ArrayList<Song>();
		}

		@Override
		public void calculateHeuristicValue() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public ArrayList<Node> expand() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isSolution() {
			// TODO Auto-generated method stub
			return false;
		}
		public void addSongFromFile(String[] data) {
			allSongs.add(new Song(data[0], parseToSeconds(data[1]), Integer.valueOf(data[2])));
		}
		private float parseToSeconds(String string) {
			String[] time = string.split(":");// since time is given in min:sec format.
			return 60 * Integer.valueOf(time[0]) + Integer.valueOf(time[1]);// Working with seconds as recommended
		}
		
	}

}

package algstudent.s7.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import algstudent.s6.Song;

public class SongsProblem {
	class BlockGenerator extends BranchAndBound{//to solve a reduced version of branch and bound
		public BlockGenerator(BlocksOfSongs start) {//in this problem it would be a BlocksOfSongs object with two empty blocks
			rootNode=start;
		}
		
	}
	class BlocksOfSongs extends Node{//to represent a node in the graph
		private List<Song> blockA;
		private List<Song> blockB;
		private int blockLength;
		private List<Song> allSongs;
		public  BlocksOfSongs() {
			super();
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
		private boolean executeFromFile(String file) {
			boolean result=false;
			BufferedReader br;
			try {
				br=new BufferedReader(new FileReader(file));
				String blength=br.readLine();
				this.blockLength=Integer.parseInt(blength);//the first line contains the length the blocks should have.
				
				BlocksOfSongs blocks=new BlocksOfSongs();
				
				//now we retrieve all songs from the list.
				for (int i = 0; i < blockLength; i++) {
					String[] data= br.readLine().split("\t");
					allSongs.add(new Song(data[0], parseToSeconds(data[1]), Integer.valueOf(data[2])));
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
		private float parseToSeconds(String string) {
			String[] time = string.split(":");// since time is given in min:sec format.
			return 60 * Integer.valueOf(time[0]) + Integer.valueOf(time[1]);// Working with seconds as recommended
		}

		private String parseToMinutesAndSeconds(float sec) {

			int totl = (int) Math.floor(sec/60);
			int secs = Math.round(sec % 60);
			
			return totl+":"+String.format("%02d", secs);
		}

		
	}

}

package algstudent.s7.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algstudent.s6.Song;

public class RandomSongGenerator {
	private List<Song> songs=new ArrayList<Song>();
	//will generate files with random songs where lengths vary from 30 s to 5 min
	public RandomSongGenerator(int numberOfSongs) {
		for (int i = 0; i < numberOfSongs; i++) {
			songs.add(new Song(generateRdCode(), generateRdDuration(),generateRdScore()));
		}
	}
	private int generateRdScore() {
		// TODO Auto-generated method stub
		return 0;
	}
	private float generateRdDuration() {
		Random rd=new Random();
		return rd.nextInt(300)+30;//songs length can go from 30 seconds to 300(5 minutes)
		
	}
	private String generateRdCode() {
	    byte[] array = new byte[6];
		new Random().nextBytes(array);	  
		return new String(array, Charset.forName("UTF-8"));

	}
	
	public void generateSongFile(String filename) {
		try {
			BufferedWriter bw= new BufferedWriter(new FileWriter("/algorithmicsFernandezAriasSaraUO269546/src/main/java/algstudent/s7/"+filename+".txt"));
			bw.write(songs.size()+"\n");//the first line is the number of songs in the list
			
			for (Song song : songs) {
				bw.write(song.toString()+"\n");
			}
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


		


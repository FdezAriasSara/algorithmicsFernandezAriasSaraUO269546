package algstudent.s6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class BestList {
	
private int blockLength;
private List<Song> songs;
public static void main(String[] args) {
}
//is recomended to work with seconds , then translate it with dividing by 60
//is also recomended to use Collections api .
private void readFromFile(String fileName) {
	songs = new ArrayList<Song>();
	BufferedReader reader = null;
	String[] line;
	try {
		reader = new BufferedReader(new FileReader(fileName)); 	
		if(reader.ready()) {
			blockLength=Integer.valueOf(reader.readLine());//the first line of the file is the duration of the songs.
		}
		while (reader.ready()) { 
			
			line=reader.readLine().split();
		}
		
	} catch (Exception e) {
		System.err.println(e.getMessage());
	} 
}
}

package algstudent.s6;

public class Song {
	public Song(String code, int duration, int score) {

		setCode(code);//identifier of the song.
		setDuration(duration);
		setScore(score);
	}


	private String code;
	private int score;
	private int duration;
	
	public String getCode() {
		return code;
	}
	
	public int getDuration() {
		return duration;
	}

	public int getScore() {
		return score;
	}
	private void setCode(String code) {
		this.code = code;
	}
	
	private void setDuration(int duration) {
		this.duration = duration;
	}
	private void setScore(int score) {
	this.score=score;
		
	}

}

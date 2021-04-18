package algstudent.s6;

public class Song {
	public Song(String code, float duration, int score) {

		setCode(code);//identifier of the song.
		setDuration(duration);
		setScore(score);
	}


	private String code;
	private int score;
	private float duration;
	
	public String getCode() {
		return code;
	}
	
	public float getDuration() {
		return duration;
	}

	public int getScore() {
		return score;
	}
	private void setCode(String code) {
		this.code = code;
	}
	
	private void setDuration(float duration) {
		this.duration = duration;
	}
	private void setScore(int score) {
	this.score=score;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + Float.floatToIntBits(duration);
		result = prime * result + score;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Song)) {
			return false;
		}
		Song other = (Song) obj;
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		if (Float.floatToIntBits(duration) != Float.floatToIntBits(other.duration)) {
			return false;
		}
		if (score != other.score) {
			return false;
		}
		return true;
	}

}

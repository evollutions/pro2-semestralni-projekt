package cz.uhk.fim.pro2.game;

public class Score {
	private final int points;
	private final String date;
	private final String nickname;
	
	public Score(int points, String date, String nickname) {
		this.points = points;
		this.date = date;
		this.nickname = nickname;
	}
	
	public int getPoints() {
		return points;
	}
	
	public String toString() {
		return points + ";" + date + ";" + nickname;
	}
	
	public String toScreenString() {
		return points + " pts, on " + date + " by " + nickname;
	}
}

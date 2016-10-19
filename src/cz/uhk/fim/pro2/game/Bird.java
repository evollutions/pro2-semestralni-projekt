package cz.uhk.fim.pro2.game;

public class Bird {
	private int liveCount;
	
	private float speed;
	private String name;
	
	public float getSpeed() {
		return speed;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public int getLiveCount() {
		return liveCount;
	}
	
	public void setLiveCount(int liveCount) {
		this.liveCount = liveCount;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}

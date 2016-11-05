package cz.uhk.fim.pro2.game.model;

public class Bird {
	private int positionX, positionY;
	private float speed;
	private int lives;
	private String name;
	
	public Bird(String name, int positionX, int positionY) {
		this.name = name;
		this.positionX = positionX;
		this.positionY = positionY;
		
		lives = 3;
		speed = 0f;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	
	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	
	public void goUp() {
		
	}
	
	public void catchHeart() {
		
	}
	
	public void die() {
		
	}
	
	public void addLive() {
		
	}
	
	public void removeLive() {
		
	}
}

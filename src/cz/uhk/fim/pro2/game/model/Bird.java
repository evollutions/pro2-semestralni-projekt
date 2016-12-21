package cz.uhk.fim.pro2.game.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import cz.uhk.fim.pro2.game.gui.GameCanvas;
import cz.uhk.fim.pro2.game.gui.MainFrame;

public class Bird {
	private static final int GRAVITY = 300;
	private static final int JUMP = 600;
	
	public static final int DEFAULT_LIVES = 3;
	public static final int DEFAULT_SCORE = 0;
	
	private int positionX, positionY;
	private int lives, score, speed;
	
	//private BufferedImage imgBird;

	public Bird(int positionX, int positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
		lives = DEFAULT_LIVES;
		score = DEFAULT_SCORE;
		speed = 0;
	}

	public void paint(Graphics g, BufferedImage img) {
		g.setColor(Color.BLUE);
		Rectangle rect = getRect();
		g.drawImage(img, (int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight(), null);
	}

	public void update(float deltaTime) {
		positionY -= speed * deltaTime;
		positionY += GRAVITY * deltaTime;
		speed -= speed * deltaTime;
	}

	public void goUp() {
		speed = JUMP;
	}
	
	public boolean isCollidingWith(Tube tube) {
		return (getRect().intersects(tube.getRectTop()) || getRect().intersects(tube.getRectBottom())); 
	}
	
	public boolean isCollidingWith(Heart heart) {
		return (getRect().intersects(heart.getRect()));
	}
	
	public boolean isOutOfBounds() {
		Rectangle rect = getRect();	
		return (rect.getMaxY() < GameCanvas.UP_BOUND || rect.getMaxY() > MainFrame.HEIGHT - GameCanvas.DOWN_BOUND);
	}

	public void die() {

	}
	
	public boolean isAlive() {
		return lives > 0;
	}

	public void addLive() {
		lives++;
	}

	public void removeLive() {
		lives--;
	}
	
	public Rectangle getRect() {
		return new Rectangle((int) positionX - 25, (int) positionY - 20, 50, 40);
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
		
	public int getLives() {
		return lives;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
}

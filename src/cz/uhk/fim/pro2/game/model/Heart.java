package cz.uhk.fim.pro2.game.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Heart {
	private float positionX, positionY;

	public Heart(float positionX, float positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public void paint(Graphics g, BufferedImage img) {
		g.setColor(Color.RED);
		Rectangle rect = getRect();
		g.drawImage(img, (int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight(), null);
	}

	public Rectangle getRect() {
		return new Rectangle((int) positionX - 25, (int) positionY - 25, 50, 50);
	}

	public void update(float deltaTime) {
		positionX -= World.SPEED * deltaTime;
	}

	public static float getRandomY() {
		return (new Random().nextFloat() * 300f) + 200f;
	}
	
	public float getPositionX() {
		return positionX;
	}

	public void setPositionX(float positionX) {
		this.positionX = positionX;
	}

	public float getPositionY() {
		return positionY;
	}

	public void setPositionY(float positionY) {
		this.positionY = positionY;
	}
}

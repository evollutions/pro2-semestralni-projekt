package cz.uhk.fim.pro2.game.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import cz.uhk.fim.pro2.game.gui.MainFrame;

public class Tube {
	private static final int GAP = 200;
	private static final int WIDTH = 50;

	private float positionX;
	private float height;
	private Color color;
	private boolean state;

	public Tube(float positionX, float height, Color color) {
		this.positionX = positionX;
		this.height = height;
		this.color = color;
		state = false;
	}

	public void paint(Graphics g, BufferedImage img) {
		g.setColor(Color.GREEN);

		Rectangle rectTop = getRectTop();
		Rectangle rectBottom = getRectBottom();

		g.drawImage(img, (int) rectTop.getX(), (int) rectTop.getY(), (int) rectTop.getWidth(), (int) rectTop.getHeight(), null);
		g.drawImage(img, (int) rectBottom.getX(), (int) rectBottom.getY(), (int) rectBottom.getWidth(), (int) rectBottom.getHeight(), null);
	}

	public void update(float deltaTime) {
		positionX -= World.SPEED * deltaTime;
	}

	public float getCenter() {
		return height - GAP / 2;
	}
	
	public int getMinX() {
		return (int) (getPositionX() - WIDTH / 2);
	}
	
	public int getMaxX() {
		return (int) (getPositionX() + WIDTH / 2);
	}
	
	public Rectangle getRectTop() {
		return new Rectangle((int) positionX - 25, (int) height, 50, MainFrame.HEIGHT - (int) height);
	}

	public Rectangle getRectBottom() {
		return new Rectangle((int) positionX - 25, 0, 50, (int) height - GAP);
	}

	public float getPositionX() {
		return positionX;
	}
	
	public void setPositionX(float positionX) {
		this.positionX = positionX;
	}

	public float getHeight() {
		return height;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}

	
	public static float getRandomHeight() {
		return (new Random().nextFloat() * 600f) + 100f;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public boolean wasCounted() {
		return state;
	}
	
	public void setCounted(boolean state) {
		this.state = state;
	}
}

package cz.uhk.fim.pro2.game.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import cz.uhk.fim.pro2.game.gui.MainFrame;

public class Tube {
	private static final int GAP = 200;

	private float positionX;
	private float height;
	private Color color;

	public Tube(float positionX, float height, Color color) {
		this.positionX = positionX;
		this.height = height;
		this.color = color;
	}

	public void paint(Graphics g) {
		g.setColor(Color.GREEN);

		Rectangle rectTop = getRectTop();
		Rectangle rectBottom = getRectBottom();

		g.fillRect((int) rectTop.getX(), (int) rectTop.getY(), (int) rectTop.getWidth(), (int) rectTop.height);
		g.fillRect((int) rectBottom.getX(), (int) rectBottom.getY(), (int) rectBottom.getWidth(), (int) rectBottom.getHeight());
	}

	public void update(float deltaTime) {
		positionX -= World.SPEED * deltaTime;
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

	public float getHeight() {
		return height;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}

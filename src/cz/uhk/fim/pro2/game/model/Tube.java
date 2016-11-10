package cz.uhk.fim.pro2.game.model;

import java.awt.Color;
import java.awt.Graphics;

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
	
	public void paint(Graphics g){
		g.setColor(Color.GREEN);	
		g.fillRect((int)positionX - 25, (int)height, 50, MainFrame.HEIGHT - (int)height);	
		g.fillRect((int)positionX - 25, 0, 50, (int)height - GAP);
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

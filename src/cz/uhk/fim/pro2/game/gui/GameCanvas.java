package cz.uhk.fim.pro2.game.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import cz.uhk.fim.pro2.game.model.Heart;
import cz.uhk.fim.pro2.game.model.Tube;
import cz.uhk.fim.pro2.game.model.World;

public class GameCanvas extends Canvas {
	public static final int UP_BOUND = 50;
	public static final int DOWN_BOUND = 25;
	
	private World world;

	public GameCanvas(World world) {
		this.world = world;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
		
		world.getBird().paint(g);

		for (Heart heart : world.getHearts()) {
			heart.paint(g);
		}

		for (Tube tube : world.getTubes()) {
			tube.paint(g);
		}
		
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, MainFrame.WIDTH, UP_BOUND);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, MainFrame.HEIGHT - DOWN_BOUND - 30, MainFrame.WIDTH, MainFrame.HEIGHT);
	}
}

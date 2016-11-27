package cz.uhk.fim.pro2.game.gui;

import java.awt.Canvas;
import java.awt.Graphics;

import cz.uhk.fim.pro2.game.model.Heart;
import cz.uhk.fim.pro2.game.model.Tube;
import cz.uhk.fim.pro2.game.model.World;

public class GameCanvas extends Canvas {
	private World world;

	public GameCanvas(World world) {
		this.world = world;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		world.getBird().paint(g);

		for (Heart heart : world.getHearts()) {
			heart.paint(g);
		}

		for (Tube tube : world.getTubes()) {
			tube.paint(g);
		}
	}
}

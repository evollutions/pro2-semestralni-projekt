package cz.uhk.fim.pro2.game.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import cz.uhk.fim.pro2.game.model.World;

public class GameCanvas extends Canvas {
	private World world;

	public GameCanvas(World world) {
		this.world = world;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		g.setColor(Color.red);
		g.drawRect(world.getBird().getPositionX(), world.getBird().getPositionY(), 100, 100);
	}
}

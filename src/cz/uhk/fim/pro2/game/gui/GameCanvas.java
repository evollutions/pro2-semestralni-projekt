package cz.uhk.fim.pro2.game.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import cz.uhk.fim.pro2.game.model.Heart;
import cz.uhk.fim.pro2.game.model.Tube;
import cz.uhk.fim.pro2.game.model.World;

public class GameCanvas extends Canvas {
	public static final int UP_BOUND = 50;
	public static final int DOWN_BOUND = 100;
	
	private World world;
	
	private BufferedImage imgBird, imgHeart, imgBackground, imgTube, imgTop, imgBottom;

	public GameCanvas(World world) {
		this.world = world;
		
		try {
			imgBird = ImageIO.read(new File("assets/bird.png"));
			imgHeart = ImageIO.read(new File("assets/heart.png"));
			imgTube = ImageIO.read(new File("assets/tube.png"));
			
			imgBackground = ImageIO.read(new File("assets/background.png"));
			imgTop = ImageIO.read(new File("assets/top.png"));
			imgBottom = ImageIO.read(new File("assets/bottom.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
		
		g.drawImage(imgBackground, 0, UP_BOUND, MainFrame.WIDTH, MainFrame.HEIGHT - (DOWN_BOUND * 2), null);
		
		world.getBird().paint(g, imgBird);

		for (Heart heart : world.getHearts()) {
			heart.paint(g, imgHeart);
		}

		for (Tube tube : world.getTubes()) {
			tube.paint(g, imgTube);
		}
		
		g.drawImage(imgTop, 0, 0, MainFrame.WIDTH, UP_BOUND, null);
		g.drawImage(imgBottom, 0, MainFrame.HEIGHT - DOWN_BOUND - 60, MainFrame.WIDTH, DOWN_BOUND + 30, null);
	}
}

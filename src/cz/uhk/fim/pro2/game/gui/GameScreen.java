package cz.uhk.fim.pro2.game.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import cz.uhk.fim.pro2.game.model.Bird;
import cz.uhk.fim.pro2.game.model.Heart;
import cz.uhk.fim.pro2.game.model.Tube;
import cz.uhk.fim.pro2.game.model.World;

public class GameScreen extends Screen {
	private JButton jButtonBack, jButtonPause;
	
	public GameScreen(MainFrame mainFrame) {
		super(mainFrame);
		
		jButtonBack = new JButton("BACK");
		jButtonPause = new JButton("PAUSE");
		
		jButtonBack.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setScreen(new HomeScreen(mainFrame));
			}
		});
		
		jButtonPause.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		
		add(jButtonBack);
		add(jButtonPause);
		
		Bird bird = new Bird("Franta", 240, 400);
		World world = new World(bird);
		world.addTube(new Tube(400f, 400f, Color.green));
		world.addTube(new Tube(600f, 300f, Color.green));
		world.addTube(new Tube(800f, 500f, Color.green));
		world.addHeart(new Heart(500f,  450f));
		
		System.out.println(world.toString());
		
		GameCanvas gameCanvas = new GameCanvas(world);
		add(gameCanvas);
		gameCanvas.setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
	}
}

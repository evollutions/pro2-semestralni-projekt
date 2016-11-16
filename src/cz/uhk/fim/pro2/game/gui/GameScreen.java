package cz.uhk.fim.pro2.game.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.Timer;

import cz.uhk.fim.pro2.game.interfaces.WorldListener;
import cz.uhk.fim.pro2.game.model.Bird;
import cz.uhk.fim.pro2.game.model.Heart;
import cz.uhk.fim.pro2.game.model.Tube;
import cz.uhk.fim.pro2.game.model.World;

public class GameScreen extends Screen implements WorldListener {
	private long lastTimeMs;
	private Timer timer;

	public GameScreen(MainFrame mainFrame) {
		super(mainFrame);
		
		JButton jButtonBack = new JButton("BACK");
		JButton jButtonPause = new JButton("PAUSE");
		
		jButtonBack.setLocation(10, 10);
		jButtonBack.setSize(80, 30);
		jButtonPause.setLocation(90, 10);
		jButtonPause.setSize(80, 30);
		
		jButtonBack.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setScreen(new HomeScreen(mainFrame));
			}
		});
		
		jButtonPause.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if (timer.isRunning()) {
					timer.stop();
				} else {
					lastTimeMs = System.currentTimeMillis();
					timer.restart();
				}
			}
		});
	
		add(jButtonBack);
		add(jButtonPause);
		
		Bird bird = new Bird("Franta", 240, 400);
		World world = new World(bird, this);
		world.addTube(new Tube(400f, 400f, Color.green));
		world.addTube(new Tube(600f, 300f, Color.green));
		world.addTube(new Tube(800f, 500f, Color.green));
		world.addHeart(new Heart(500f,  450f));
		
		GameCanvas gameCanvas = new GameCanvas(world);
		gameCanvas.setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
		
		gameCanvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				bird.goUp();
			}
		});
		
		add(gameCanvas);
		
		timer = new Timer(20, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				long currentTimeMs = System.currentTimeMillis();
				
				float deltaTime = (currentTimeMs - lastTimeMs) / 1000f;
				world.update(deltaTime);
				gameCanvas.repaint();
				
				lastTimeMs = currentTimeMs;
			}
		});
		
		lastTimeMs = System.currentTimeMillis();
		timer.start();
	}
	
	@Override
	public void collidedWithTube(Tube tube) {
		System.out.println("Colliding with tube");
	}

	@Override
	public void collidedWithHeart(Heart heart) {
		System.out.println("Colliding with heart");	
	}

	@Override
	public void isOutOfBounds() {
		System.out.println("Out of bounds");	
	}
}

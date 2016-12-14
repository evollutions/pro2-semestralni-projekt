package cz.uhk.fim.pro2.game.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Timer;

import cz.uhk.fim.pro2.game.interfaces.WorldListener;
import cz.uhk.fim.pro2.game.model.Bird;
import cz.uhk.fim.pro2.game.model.Heart;
import cz.uhk.fim.pro2.game.model.Tube;
import cz.uhk.fim.pro2.game.model.World;

public class GameScreen extends Screen implements WorldListener {
	private long lastTimeMs;
	private Timer timer;
	
	private JButton btnBack, btnPause;
	private JLabel lblLives, lblScore;
	
	private Bird bird;
	
	public GameScreen(MainFrame mainFrame) {
		super(mainFrame);
		
		btnBack = new JButton("BACK");
		btnPause = new JButton("PAUSE");
		
		lblLives = new JLabel("Score: " + Bird.DEFAULT_LIVES);
		lblScore = new JLabel("Lives: " + Bird.DEFAULT_SCORE);
		
		btnBack.setBounds(10, 10, 80, 30);
		btnPause.setBounds(90, 10, 80, 30);
		
		lblLives.setBounds(10, 50, 100, 30);
		lblScore.setBounds(10, 80, 100, 30);
		
		btnBack.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setScreen(new HomeScreen(mainFrame));
			}
		});
		
		btnPause.addActionListener(new ActionListener() {		
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
	
		bird = new Bird("Franta", 240, 400);
		World world = new World(bird, this);
		/*world.addTube(new Tube(400f, 400f, Color.green));
		world.addTube(new Tube(600f, 300f, Color.green));
		world.addTube(new Tube(800f, 500f, Color.green));
		world.addHeart(new Heart(500f,  450f));*/
		world.generateRandom();
		
		GameCanvas gameCanvas = new GameCanvas(world);
		gameCanvas.setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
		
		gameCanvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				bird.goUp();
			}
		});
			
		timer = new Timer(20, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				long currentTimeMs = System.currentTimeMillis();
				
				float deltaTime = (currentTimeMs - lastTimeMs) / 1000f;
				world.update(deltaTime);
				
				lblLives.setText("Lives: " + bird.getLives());
				lblScore.setText("Score: " + bird.getScore());
				
				if (!bird.isAlive()) {
					timer.stop();
					mainFrame.setScreen(new FinishScreen(mainFrame, world));
				}
				gameCanvas.repaint();
				
				lastTimeMs = currentTimeMs;
			}
		});
		
		lastTimeMs = System.currentTimeMillis();
		timer.start();
		
		add(btnBack);
		add(btnPause);
		add(lblLives);
		add(lblScore);
		add(gameCanvas);
	}
	
	@Override
	public void collidedWithTube(Tube tube) {
		bird.removeLive();
		bird.setScore(bird.getScore() - 1);
		bird.setPositionY((int) tube.getCenter());
	}

	@Override
	public void collidedWithHeart(Heart heart) {
		heart.setPositionX(-100f);
		bird.addLive();
	}

	@Override
	public void isOutOfBounds() {
		if (bird.getPositionY() > MainFrame.HEIGHT) {
			bird.setPositionY(0);
		} else if (bird.getPositionY() < 0) {
			bird.setPositionY(MainFrame.HEIGHT);
		}
	}
}

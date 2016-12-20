package cz.uhk.fim.pro2.game.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
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
	
	public static final int UP_BOUND = 50;
	public static final int DOWN_BOUND = 100;
	
	public GameScreen(MainFrame mainFrame) {
		super(mainFrame);
		
		bird = new Bird("Franta", 240, 400);
		World world = new World(bird, this);
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
				repaint(); // Repaint ovladacich prvku
				
				lastTimeMs = currentTimeMs;
			}
		});
		
		lastTimeMs = System.currentTimeMillis();
		timer.start();
		
		btnBack = new JButton("BACK");
		btnPause = new JButton("PAUSE");
		
		lblLives = new JLabel("Lives: " + Bird.DEFAULT_LIVES, SwingConstants.CENTER);
		lblScore = new JLabel("Score: " + Bird.DEFAULT_SCORE, SwingConstants.CENTER);
		
		lblLives.setOpaque(true);
		lblScore.setOpaque(true);

		btnBack.setBounds(10, 10, 80, 60);
		btnPause.setBounds(100, 10, 80, 60);
	
		lblLives.setBounds(270, 10, 90, 60);
		lblScore.setBounds(370, 10, 90, 60);
		
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

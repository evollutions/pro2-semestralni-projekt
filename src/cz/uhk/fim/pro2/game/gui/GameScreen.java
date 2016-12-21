package cz.uhk.fim.pro2.game.gui;

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
	private JLabel lblLives, lblScore, lblStart;
	
	private Bird bird;
	
	public GameScreen(MainFrame mainFrame) {
		super(mainFrame);
		
		bird = new Bird(240, 400);
		World world = new World(bird, this);
		world.generateRandom();
		
		GameCanvas gameCanvas = new GameCanvas(world);
		gameCanvas.setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);	
		gameCanvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				
				if (!timer.isRunning()) {
					timer.start();
					lastTimeMs = System.currentTimeMillis();
					lblStart.setVisible(false);
				} else {
					bird.goUp();
				}
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
				repaint();
				
				lastTimeMs = currentTimeMs;
			}
		});
				
		btnBack = new JButton("BACK");
		btnBack.setBounds(10, 10, 80, 60);
		
		btnPause = new JButton("PAUSE");
		btnPause.setBounds(100, 10, 80, 60);
		
		lblLives = new JLabel("Lives: " + Bird.DEFAULT_LIVES, SwingConstants.CENTER);	
		lblLives.setOpaque(true);
		lblLives.setBounds(270, 10, 90, 60);
		
		lblScore = new JLabel("Score: " + Bird.DEFAULT_SCORE, SwingConstants.CENTER);
		lblScore.setOpaque(true);
		lblScore.setBounds(370, 10, 90, 60);
		
		lblStart = new JLabel("PRESS MOUSE TO START", SwingConstants.CENTER);
		lblStart.setOpaque(true);
		lblStart.setBounds(100, 250, 300, 80);
	
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
		add(lblStart);
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

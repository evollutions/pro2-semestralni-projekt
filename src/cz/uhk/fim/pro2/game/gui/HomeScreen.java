package cz.uhk.fim.pro2.game.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;

public class HomeScreen extends Screen {
	private JButton jButtonPlay, jButtonScore, jButtonSound;

	public HomeScreen(MainFrame mainFrame) {
		super(mainFrame);

		jButtonPlay = new JButton("PLAY");
		jButtonScore = new JButton("SCORE");
		jButtonSound = new JButton("SOUND");
		
		jButtonPlay.setBounds(100, 400, 280, 50);
		jButtonScore.setBounds(100, 460, 280, 50);
		jButtonSound.setBounds(100, 520, 280, 50);
		
		JLabel jLabel = new JLabel("Stay Flappy");
		jLabel.setFont(new Font("Arial", Font.BOLD, 40));
		jLabel.setBounds(120, 10, 240, 100);

		jButtonPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					mainFrame.setScreen(new GameScreen(mainFrame));
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});

		jButtonScore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setScreen(new ScoreScreen(mainFrame));
			}
		});

		jButtonSound.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});

		add(jButtonPlay);
		add(jButtonScore);
		add(jButtonSound);
		add(jLabel);
	}
}

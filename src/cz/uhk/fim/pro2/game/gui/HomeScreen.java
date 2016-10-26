package cz.uhk.fim.pro2.game.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class HomeScreen extends Screen {
	private JButton jButtonPlay, jButtonScore, jButtonSound;

	public HomeScreen(MainFrame mainFrame) {
		super(mainFrame);

		jButtonPlay = new JButton("PLAY");
		jButtonScore = new JButton("SCORE");
		jButtonSound = new JButton("MUTE");

		jButtonPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setScreen(new GameScreen(mainFrame));
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
	}
}

package cz.uhk.fim.pro2.game.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import cz.uhk.fim.pro2.game.ScoreManager;
import cz.uhk.fim.pro2.game.model.World;

public class FinishScreen extends Screen {
	private JLabel jLblScore;
	private JButton jBtnPlay, jBtnScore;
	
	public FinishScreen(MainFrame mainFrame, World world) {
		super(mainFrame);
		
		jLblScore = new JLabel();
		jLblScore.setFont(new Font("Arial", Font.BOLD, 40));
		jLblScore.setBounds(120, 10, 240, 100);
		jLblScore.setText(world.getBird().getScore() + "!");
		
		jBtnPlay = new JButton("PLAY");
		jBtnPlay.setBounds(100, 400, 280, 50);
		
		jBtnScore = new JButton("SCORE");
		jBtnScore.setBounds(100, 500, 280, 50);
		
		ScoreManager.putScore(world.getBird().getScore());
		
		jBtnPlay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setScreen(new GameScreen(mainFrame));
			}
		});
		
		jBtnScore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setScreen(new ScoreScreen(mainFrame));	
			}
		});
		
		add(jLblScore);
		add(jBtnPlay);
		add(jBtnScore);
	}
}
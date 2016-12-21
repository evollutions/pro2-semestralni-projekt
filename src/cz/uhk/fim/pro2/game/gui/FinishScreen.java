package cz.uhk.fim.pro2.game.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cz.uhk.fim.pro2.game.Score;
import cz.uhk.fim.pro2.game.ScoreManager;
import cz.uhk.fim.pro2.game.model.World;

public class FinishScreen extends Screen {
	private JLabel jLblScore;
	private JTextField jTxtScore;
	private JButton jBtnPlay, jBtnScore, jBtnSubmitScore;
	
	public FinishScreen(MainFrame mainFrame, World world) {
		super(mainFrame);
		
		jLblScore = new JLabel();
		jLblScore.setFont(new Font("Arial", Font.BOLD, 40));
		jLblScore.setBounds(120, 10, 240, 100);
		jLblScore.setText(world.getBird().getScore() + "!");
		
		jBtnPlay = new JButton("PLAY");
		jBtnPlay.setBounds(100, 300, 280, 50);
		
		jBtnScore = new JButton("SCORE");
		jBtnScore.setBounds(100, 400, 280, 50);
		
		jTxtScore = new JTextField("Nickname");
		jTxtScore.setBounds(100, 500, 280, 50);
		
		jBtnSubmitScore = new JButton("SUBMIT SCORE");
		jBtnSubmitScore.setBounds(100, 600, 280, 50);
				
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
		
		jBtnSubmitScore.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				String nickname;
				if (jTxtScore.getText().isEmpty()) {
					nickname = "Unknown";
				} else {
					nickname = jTxtScore.getText();
				}	
				ScoreManager.putScore(new Score(world.getBird().getScore(), new SimpleDateFormat("dd.MM.yyyy").format(new Date()), nickname));
				mainFrame.setScreen(new ScoreScreen(mainFrame));
			}
		});
		
		add(jLblScore);
		add(jBtnPlay);
		add(jBtnScore);
		add(jTxtScore);
		add(jBtnSubmitScore);
	}
}
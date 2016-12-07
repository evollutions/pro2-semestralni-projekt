package cz.uhk.fim.pro2.game.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import cz.uhk.fim.pro2.game.ScoreManager;

public class ScoreScreen extends Screen {
	private JButton jButtonBack;
	
	public ScoreScreen(MainFrame mainFrame) {
		super(mainFrame);
		
		jButtonBack = new JButton("BACK");
		jButtonBack.setBounds(20, 20, 80, 80);
		
		jButtonBack.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setScreen(new HomeScreen(mainFrame));
			}
		});
		
		for (int i = 0; i < ScoreManager.getSize(); i++) {
			ScoreItem scoreItem = new ScoreItem(i + 1, ScoreManager.get(i));
			scoreItem.setBounds(50, 200 + i * 50, 300, 50);
			add(scoreItem);
		}
		
		add(jButtonBack);
	}
}

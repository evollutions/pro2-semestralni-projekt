package cz.uhk.fim.pro2.game.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ScoreScreen extends Screen {
	private JButton jButtonBack;
	
	public ScoreScreen(MainFrame mainFrame) {
		super(mainFrame);
		
		jButtonBack = new JButton("BACK");
		
		jButtonBack.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setScreen(new HomeScreen(mainFrame));
			}
		});
		
		add(jButtonBack);
	}
}

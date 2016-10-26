package cz.uhk.fim.pro2.game.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class GameScreen extends Screen {
	private JButton jButtonBack, jButtonPause;
	
	public GameScreen(MainFrame mainFrame) {
		super(mainFrame);
		
		jButtonBack = new JButton("BACK");
		jButtonPause = new JButton("PAUSE");
		
		jButtonBack.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setScreen(new HomeScreen(mainFrame));
			}
		});
		
		jButtonPause.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		
		add(jButtonBack);
		add(jButtonPause);
	}

}

package cz.uhk.fim.pro2.game.gui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreItem extends JPanel {
	private JLabel jLblIndex, jLblScore;
	
	public ScoreItem(int index, int score) {
		setLayout(null);
		
		jLblIndex = new JLabel(index + ".");
		jLblIndex.setFont(new Font("Arial", Font.BOLD, 40));
		jLblIndex.setBounds(0, 0, 100, 50);
		
		jLblScore = new JLabel(String.valueOf(score));
		jLblScore.setFont(new Font("Arial", Font.BOLD, 40));
		jLblScore.setBounds(120, 0, 200, 50);
		
		add(jLblIndex);
		add(jLblScore);
	}
}

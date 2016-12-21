package cz.uhk.fim.pro2.game.gui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import cz.uhk.fim.pro2.game.Score;

public class ScoreItem extends JPanel {
	private JLabel jLblIndex, jLblScore;
	
	public ScoreItem(int index, Score score) {
		setLayout(null);
		
		jLblIndex = new JLabel(index + ".");
		jLblIndex.setFont(new Font("Arial", Font.BOLD, 24));
		jLblIndex.setBounds(0, 0, 100, 50);
		
		jLblScore = new JLabel(score.toScreenString());
		jLblScore.setFont(new Font("Arial", Font.BOLD, 18));
		jLblScore.setBounds(30, 0, 400, 50);
		
		add(jLblIndex);
		add(jLblScore);
	}
}

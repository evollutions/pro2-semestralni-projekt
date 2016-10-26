package cz.uhk.fim.pro2.game.gui;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	
	protected Screen actualScreen;
	
	public MainFrame() {
		setSize(480, 800);
		setTitle("Flappy Bird");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	public void setScreen(Screen screen) {
		/*if (actualScreen != null) {
			remove(actualScreen);
		}
		add(screen);
		actualScreen = screen;
		revalidate();*/
		
		getContentPane().removeAll();
		add(screen);
		revalidate();
	}
}

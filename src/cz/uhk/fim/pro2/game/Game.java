package cz.uhk.fim.pro2.game;

import cz.uhk.fim.pro2.game.gui.MainFrame;
import cz.uhk.fim.pro2.game.gui.Screen;

import java.io.File;
import java.io.IOException;

import cz.uhk.fim.pro2.game.gui.HomeScreen;

public class Game {
	final static String SCORE_FILE = "scores.csv";
	
	public static void main(String[] args) {
		File file = new File(SCORE_FILE);
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		MainFrame mainFrame = new MainFrame();
	
		Screen homeScreen = new HomeScreen(mainFrame);
		mainFrame.setScreen(homeScreen);
	}
}
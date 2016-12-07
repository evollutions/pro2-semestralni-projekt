package cz.uhk.fim.pro2.game;

import java.util.ArrayList;
import java.util.List;

public class ScoreManager {
	private List<Integer> scoreList;
	
	private ScoreManager() {
		scoreList = new ArrayList<>();
	}
	
	public void addScore(int score) {
		scoreList.add(score);
	}
	
	public int getAtIndex(int index) {
		return scoreList.get(index);
	}
	
	public List<Integer> getAll() {
		return scoreList;
	}
	
	private static ScoreManager instance;
	
	public static ScoreManager getInstance() {
		if (instance == null) {
			instance = new ScoreManager();
		}
		return instance;
	}

	public static void putScore(int score) {
		getInstance().addScore(score);
	}
	
	public static int getSize() {
		return getInstance().getAll().size();
	}
	
	public static int get(int index) {
		return getInstance().getAll().get(index);
	}
}
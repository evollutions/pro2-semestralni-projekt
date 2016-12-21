package cz.uhk.fim.pro2.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScoreManager {
	
	private ScoreManager() {
	}
	
	public void addScore(Score score) {
		try {
			FileWriter fileWriter = new FileWriter(Game.SCORE_FILE, true);	
			fileWriter.append(score.toString());
			fileWriter.append("\n");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("Chyba pri zapisovani");
		}
	}
	
	public List<Score> getAll() {
		List<Score> scoreList = new ArrayList<>();
		
		try {
			FileReader fileReader = new FileReader(Game.SCORE_FILE);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String[] values = line.split(";");		
				scoreList.add(new Score(Integer.valueOf(values[0]), values[1], values[2]));
			}		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Chyba pri nacitani");
		}
		sortScore(scoreList);
		return scoreList;
	}
	
	private void sortScore(List<Score> scoreList) {
		Score temp;
		
	    for (int i = 0; i < scoreList.size(); i++) {
	        for (int j = 1; j < (scoreList.size() - i); j++) {

	            if (scoreList.get(j - 1).getPoints() < scoreList.get(j).getPoints()) {
	                temp = scoreList.get(j - 1);
	                scoreList.set(j - 1, scoreList.get(j));
	                scoreList.set(j, temp);
	            }
	        }
	    }
	}
	
	private static ScoreManager instance;
	
	public static ScoreManager getInstance() {
		if (instance == null) {
			instance = new ScoreManager();
		}
		return instance;
	}

	public static void putScore(Score score) {
		getInstance().addScore(score);
	}
	
	public static int getSize() {
		return getInstance().getAll().size();
	}
	
	public static Score get(int index) {
		return getInstance().getAll().get(index);
	}
}
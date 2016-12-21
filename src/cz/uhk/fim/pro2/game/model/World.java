package cz.uhk.fim.pro2.game.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import cz.uhk.fim.pro2.game.interfaces.WorldListener;

public class World {
	public static final int SPEED = 100;
	public static int CURRENT_SPEED;
	public static final int SPACE_BETWEEN_TUBES = 300;
	public static final int SPACE_BETWEEN_HEARTS = 450;

	private Bird bird;
	private List<Tube> tubes;
	private List<Heart> hearts;
	private WorldListener worldListener;
	private boolean isGenerated;

	public World(Bird bird, WorldListener worldListener) {
		this.bird = bird;
		this.worldListener = worldListener;
		tubes = new ArrayList<>();
		hearts = new ArrayList<>();
		isGenerated = false;
	}

	public void update(float deltaTime) {
		if (bird.getScore() > 0) {
			CURRENT_SPEED = SPEED + (bird.getScore() * 4);
		} else {
			CURRENT_SPEED = SPEED;
		}
		
		if (isGenerated) {
			regenerate();
		}
		
		bird.update(deltaTime);

		if (bird.isOutOfBounds()) {
			worldListener.isOutOfBounds();
		}
		
		for (Tube tube : tubes) {
			tube.update(deltaTime);
			
			if (bird.isCollidingWith(tube)) {
				worldListener.collidedWithTube(tube);
				tube.setCounted(true);
			} else {
				if (bird.getPositionX() > tube.getMinX() && bird.getPositionX() < tube.getMaxX()) {
					if (!tube.wasCounted() && bird.getPositionX() > tube.getPositionX()) {
						bird.setScore(bird.getScore() + 1);
						tube.setCounted(true);
					}
				}
			}
		}

		for (Heart heart : hearts) {
			heart.update(deltaTime);
			
			if (bird.isCollidingWith(heart)) {
				worldListener.collidedWithHeart(heart);
			}
		}
	}
	
	public void generateRandom() {
		for (int i = 0; i < 3; i++) {
			addTube(new Tube(SPACE_BETWEEN_TUBES + i * SPACE_BETWEEN_TUBES, Tube.getRandomHeight(), Color.GREEN));
		}
		
		for (int i = 0; i < 1; i++) {
			addHeart(new Heart(SPACE_BETWEEN_HEARTS, Heart.getRandomY()));
		}
		isGenerated = true;
	}
	
	private void regenerate() {
		for (Tube tube : tubes) {
			if (tube.getPositionX() < -100) {
				tube.setPositionX(tube.getPositionX() + tubes.size() * SPACE_BETWEEN_TUBES);
				tube.setHeight(Tube.getRandomHeight());
				tube.setCounted(false);
			}
		}
		
		for (Heart heart : hearts) {
			if (heart.getPositionX() < -100) {
				heart.setPositionX(heart.getPositionX() + (hearts.size() + 1) * SPACE_BETWEEN_HEARTS);
				heart.setPositionY(Heart.getRandomY());
			}
		}
	}

	public Bird getBird() {
		return bird;
	}

	public void addTube(Tube tube) {
		tubes.add(tube);
	}

	public List<Tube> getTubes() {
		return tubes;
	}

	public void addHeart(Heart heart) {
		hearts.add(heart);
	}

	public List<Heart> getHearts() {
		return hearts;
	}

	@Override
	public String toString() {
		return "Bird: on position: " + bird.getPositionX() + ":" + bird.getPositionY()
				+ "\nThere are " + tubes.size() + " tubes and " + hearts.size() + " hearts in the world.";
	}
}

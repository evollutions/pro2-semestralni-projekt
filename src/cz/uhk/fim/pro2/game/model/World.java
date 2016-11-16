package cz.uhk.fim.pro2.game.model;

import java.util.ArrayList;
import java.util.List;

import cz.uhk.fim.pro2.game.interfaces.WorldListener;

public class World {
	public static final int SPEED = 100;

	private Bird bird;
	private WorldListener worldListener;
	private List<Tube> tubes;
	private List<Heart> hearts;

	public World(Bird bird, WorldListener worldListener) {
		this.bird = bird;
		this.worldListener = worldListener;
		tubes = new ArrayList<>();
		hearts = new ArrayList<>();
	}

	public void update(float deltaTime) {
		bird.update(deltaTime);

		if (bird.isOutOfBounds()) {
			worldListener.isOutOfBounds();
		}
		
		for (Tube tube : tubes) {
			tube.update(deltaTime);
			
			if (bird.isCollidingWith(tube)) {
				worldListener.collidedWithTube(tube);
			}
		}

		for (Heart heart : hearts) {
			heart.update(deltaTime);
			
			if (bird.isCollidingWith(heart)) {
				worldListener.collidedWithHeart(heart);
			}
		}

		for (int i = tubes.size() - 1; i >= 0; i--) {
			if (tubes.get(i).getPositionX() < 0) {
				tubes.remove(i);
			}
		}

		for (int i = hearts.size() - 1; i >= 0; i--) {
			if (hearts.get(i).getPositionX() < 0) {
				hearts.remove(i);
			} else if (bird.isCollidingWith(hearts.get(i))) {
				hearts.remove(i);
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
		return "Bird:" + bird.getName() + " on position: " + bird.getPositionX() + ":" + bird.getPositionY()
				+ "\nThere are " + tubes.size() + " tubes and " + hearts.size() + " hearts in the world.";
	}
}

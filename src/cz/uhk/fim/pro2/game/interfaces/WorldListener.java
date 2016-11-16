package cz.uhk.fim.pro2.game.interfaces;

import cz.uhk.fim.pro2.game.model.Heart;
import cz.uhk.fim.pro2.game.model.Tube;

public interface WorldListener {
	void collidedWithTube(Tube tube);
	void collidedWithHeart(Heart heart);
	void isOutOfBounds();
}

package block;

import game.GameMap;
import lemming.Lemming;
import view.Type;

import component.Coordinate;

public class TP extends AbsIndestructible {

	public TP(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, PRIORITY_BLOCK, Type.TP, gameMap);
	}

	@Override
	public void collision(Lemming lemming) {
		
	}

}

package block;

import game.GameMap;
import lemming.Lemming;
import component.Coordinate;
import view.Type;

public class SimpleD extends AbsDestructible {

	public SimpleD(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, PRIORITY_BLOCK, Type.SIMPLE_DESTRUCTIBLE, gameMap);
	}

	@Override
	public void collision(Lemming lemming) {
		//lemming.getDirection().invert();
	}
	

}

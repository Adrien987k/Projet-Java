package block;

import lemming.Lemming;
import game.GameMap;
import view.Type;
import component.Coordinate;

public class Again extends AbsDestructible {

	public Again(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, PRIORITY_BLOCK, Type.AGAIN, gameMap);
		
	}

	@Override
	public void collision(Lemming lemming) {
		// TODO Auto-generated method stub
		
	}

}

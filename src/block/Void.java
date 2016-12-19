package block;

import game.GameMap;
import lemming.Lemming;
import view.Type;

import component.Coordinate;

public class Void extends AbsBlock {

	public Void(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, PRIORITY_BLOCK, Type.VOID, gameMap);
	}

	@Override
	public void collision(Lemming lemming) {
		// TODO Auto-generated method stub
		
	}

}

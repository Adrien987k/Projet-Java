package block;

import game.GameMap;
import view.Type;

import component.Coordinate;

public class Void extends AbsBlock {

	public Void(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, PRIORITY_BLOCK, Type.VOID, gameMap);
	}
	
	@Override
	public boolean isVoid(){
		return true;
	}
	
	@Override
	public boolean isInverting(){
		return false;
	}

}

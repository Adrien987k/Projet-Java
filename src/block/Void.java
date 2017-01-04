package block;

import component.Coordinate;
import game.GameMap;
import lemming.Priority;
import view.Type;

public class Void extends AbsBlock {

	public Void(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, Priority.PRIORITY_BLOCK, Type.VOID, gameMap);
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

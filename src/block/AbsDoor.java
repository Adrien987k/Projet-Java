package block;

import component.Coordinate;
import game.GameMap;
import lemming.Priority;
import view.Type;

public abstract class AbsDoor extends AbsBlock {

	public AbsDoor(Coordinate coordinate, Priority priority, Type type, GameMap gameMap) {
		super(coordinate, priority, type, gameMap);
	}
	
	@Override
	public boolean isInverting(){
		return false;
	}

}

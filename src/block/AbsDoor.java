package block;

import game.GameMap;
import view.Type;

import component.Coordinate;

public abstract class AbsDoor extends AbsBlock {

	public AbsDoor(Coordinate coordinate, int priority, Type type, GameMap gameMap) {
		super(coordinate,priority,type,gameMap);
	}
	
	@Override
	public boolean isInverting(){
		return false;
	}

}

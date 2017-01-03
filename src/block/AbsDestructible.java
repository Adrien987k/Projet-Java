package block;

import component.Coordinate;
import game.GameMap;
import lemming.Priority;
import view.Type;

public abstract class AbsDestructible extends AbsObstacle {

	public AbsDestructible(Coordinate coordinate, Priority priority, Type type, GameMap gameMap) {
		super(coordinate, priority, type, gameMap);
	}
	
	@Override
	public boolean isDestructible(){
		return true;
	}
	
	@Override
	public boolean canBeMined(){
		return true;
	}

}

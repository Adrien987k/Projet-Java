package block;

import game.GameMap;
import view.Type;

import component.Coordinate;

public abstract class AbsDestructible extends AbsObstacle {

	public AbsDestructible(Coordinate coordinate, int priority, Type type, GameMap gameMap) {
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

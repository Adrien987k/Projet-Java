package block;

import game.GameMap;
import view.Type;

import component.Coordinate;

public abstract class AbsObstacle extends AbsBlock {
	
	public AbsObstacle(Coordinate coordinate, int priority, Type type, GameMap gameMap) {
		super(coordinate, priority, type, gameMap);
	}
	
	@Override
	public boolean isInverting(){
		return true;
	}

}

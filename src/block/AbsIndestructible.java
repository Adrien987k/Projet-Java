package block;

import component.Coordinate;
import game.GameMap;
import lemming.Priority;
import view.Type;

public abstract class AbsIndestructible extends AbsObstacle {

	public AbsIndestructible(Coordinate coordinate, Priority priority, Type type, GameMap gameMap) {
		super(coordinate, priority, type, gameMap);
	}
	
	@Override
	public void destroy(){
		
	}

}

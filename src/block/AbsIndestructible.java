package block;

import game.GameMap;
import view.Type;

import component.Coordinate;

public abstract class AbsIndestructible extends AbsObstacle {

	public AbsIndestructible(Coordinate coordinate, int priority, Type type, GameMap gameMap) {
		super(coordinate,priority,type, gameMap);
	}

}

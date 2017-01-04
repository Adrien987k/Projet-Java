package block;

import component.Coordinate;
import game.GameMap;
import lemming.Priority;
import view.Type;

public class SimpleD extends AbsDestructible {

	public SimpleD(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, Priority.PRIORITY_BLOCK, Type.SIMPLE_DESTRUCTIBLE, gameMap);
	}	

}

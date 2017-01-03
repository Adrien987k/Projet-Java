package block;

import component.Coordinate;
import game.GameMap;
import lemming.Priority;
import view.Type;

public class SimpleI extends AbsIndestructible {

	public SimpleI(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, Priority.PRIORITY_BLOCK, Type.SIMPLE_INDESTRUCTIBLE, gameMap);
	}

}

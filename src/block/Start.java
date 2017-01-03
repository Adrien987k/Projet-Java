package block;

import component.Coordinate;
import game.GameMap;
import lemming.Priority;
import view.Type;

public class Start extends AbsDoor {

	public Start(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, Priority.PRIORITY_BLOCK, Type.START, gameMap);
	}

}

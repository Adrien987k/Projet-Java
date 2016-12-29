package block;

import game.GameMap;
import view.Type;
import component.Coordinate;

public class SimpleI extends AbsIndestructible {

	public SimpleI(Coordinate coordinate, GameMap gameMap) {
		super(coordinate,PRIORITY_BLOCK,Type.SIMPLE_INDESTRUCTIBLE, gameMap);
	}

}

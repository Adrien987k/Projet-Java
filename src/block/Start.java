package block;

import game.GameMap;
import view.Type;

import component.Coordinate;

public class Start extends AbsDoor {

	public Start(Coordinate coordinate, GameMap gameMap) {
		super(coordinate,PRIORITY_BLOCK,Type.START, gameMap);
	}

}

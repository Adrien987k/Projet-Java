package block;

import game.GameMap;
import lemming.Lemming;
import view.Type;

import component.Coordinate;

public class Start extends AbsDoor {

	public Start(Coordinate coordinate, GameMap gameMap) {
		super(coordinate,PRIORITY_BLOCK,Type.START, gameMap);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void collision(Lemming lemming) {
		// TODO Auto-generated method stub
		
	}
	

}

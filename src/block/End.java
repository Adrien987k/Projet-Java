package block;

import lemming.Lemming;
import game.GameMap;
import view.Type;
import component.Coordinate;

public class End extends AbsDoor {

	public End(Coordinate coordinate, GameMap gameMap) {
		super(coordinate,PRIORITY_BLOCK,Type.END, gameMap);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void collision(Lemming lemming) {
		
	}
	
	public void killLemming(Lemming lemming){
		lemming.setFree();
		lemming.destroy();
	}

}

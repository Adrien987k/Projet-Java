package block;

import lemming.Lemming;
import game.GameMap;
import view.Type;
import component.Coordinate;

public class End extends AbsDoor {

	public End(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, PRIORITY_BLOCK, Type.END, gameMap);
	}

	@Override
	public boolean collision(Lemming lemming) {
		killLemming(lemming);
		return true;
	}
	
	@Override
	public boolean canBeSkipped(){
		return false;
	}
	
	@Override
	public void killLemming(Lemming lemming){
		lemming.setFree();
		lemming.destroy();
	}

}

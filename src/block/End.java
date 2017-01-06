package block;

import component.Coordinate;
import game.GameMap;
import lemming.Lemming;
import lemming.Priority;
import view.Type;

public class End extends AbsDoor {

	public End(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, Priority.PRIORITY_BLOCK, Type.END, gameMap);
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

package block;

import component.Coordinate;
import game.GameMap;
import lemming.Lemming;
import lemming.Priority;
import view.Type;

public class Lava extends AbsIndestructible {

	public Lava(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, Priority.PRIORITY_BLOCK, Type.LAVA, gameMap);
		isLethal = true;
	}
	
	@Override
	public boolean isInverting(){
		return false;
	}
	
	@Override
	public void killLemming(Lemming lemming){
		lemming.destroy();
	}

}

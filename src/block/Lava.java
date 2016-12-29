package block;

import lemming.Lemming;
import game.GameMap;
import view.Type;
import component.Coordinate;

public class Lava extends AbsIndestructible {

	public Lava(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, PRIORITY_BLOCK, Type.LAVA, gameMap);
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

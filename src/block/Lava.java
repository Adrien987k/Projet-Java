package block;

import component.Coordinate;
import game.GameMap;
import lemming.Lemming;
import lemming.Priority;
import view.Type;

/**
 * A Lava block kill all lemming that fall in it
 * 
 * @author Adrien
 *
 */
public class Lava extends AbsIndestructible {
	
	/**
	 * 
	 * @param coordinate   Coordinate on the map
	 * @param gameMap      The map it belong to
	 */
	public Lava(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, Priority.PRIORITY_BLOCK, Type.LAVA, gameMap);
		isLethal = true;
	}
	
	/**
	 * Return false because it is not an solid obstacle
	 */
	@Override
	public boolean isInverting(){
		return false;
	}
	
	/**
	 * Obviously it destroy the lemming
	 */
	@Override
	public void killLemming(Lemming lemming){
		lemming.destroy();
	}

}

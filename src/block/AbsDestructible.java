package block;

import component.Coordinate;
import game.GameMap;
import lemming.Priority;
import view.Type;

/**
 * Higher class of the destructible blocks hierarchy
 * 
 * @author Adrien
 *
 */
public abstract class AbsDestructible extends AbsObstacle {
	
	/**
	 * 
	 * @param coordinate   Coordinate on the map
	 * @param priority 	   Display priority
	 * @param type         Type of component
	 * @param gameMap      The map it belong to 
	 */
	public AbsDestructible(Coordinate coordinate, Priority priority, Type type, GameMap gameMap) {
		super(coordinate, priority, type, gameMap);
	}
	
	/**
	 * Obviously return true 
	 */
	@Override
	public boolean isDestructible(){
		return true;
	}
	
	/**
	 * Return true because if it is destructible it can be mined
	 */
	@Override
	public boolean canBeMined(){
		return true;
	}

}

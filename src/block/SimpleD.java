package block;

import component.Coordinate;
import game.GameMap;
import lemming.Priority;
import view.Type;

/**
 * A simple destructible obstacle
 * 
 * @author Adrien
 *
 */
public class SimpleD extends AbsDestructible {
	
	/**
	 * 
	 * @param coordinate   Coordinate on the map
	 * @param priority 	   Display priority
	 * @param type         Type of component
	 * @param gameMap      The map it belong to 
	 */
	public SimpleD(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, Priority.PRIORITY_BLOCK, Type.SIMPLE_DESTRUCTIBLE, gameMap);
	}	

}

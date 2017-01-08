package block;

import component.Coordinate;
import game.GameMap;
import lemming.Priority;
import view.Type;

/**
 * A simple indestructible obstacle
 * 
 * @author Adrien
 *
 */
public class SimpleI extends AbsIndestructible {
	
	/**
	 * 
	 * @param coordinate   Coordinate on the map
	 * @param priority 	   Display priority
	 * @param type         Type of component
	 * @param gameMap      The map it belong to 
	 */
	public SimpleI(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, Priority.PRIORITY_BLOCK, Type.SIMPLE_INDESTRUCTIBLE, gameMap);
	}

}

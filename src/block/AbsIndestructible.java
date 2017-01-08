package block;

import component.Coordinate;
import game.GameMap;
import lemming.Priority;
import view.Type;

/**
 * Higher class of the indestructible blocks hierarchy
 * 
 * @author Adrien
 *
 */
public abstract class AbsIndestructible extends AbsObstacle {

	/**
	 * 
	 * @param coordinate   Coordinate on the map
	 * @param priority 	   Display priority
	 * @param type         Type of component
	 * @param gameMap      The map it belong to 
	 */
	public AbsIndestructible(Coordinate coordinate, Priority priority, Type type, GameMap gameMap) {
		super(coordinate, priority, type, gameMap);
	}
	
	/**
	 * Do nothing because it cannot be destroy
	 */
	@Override
	public void destroy(){
		
	}

}

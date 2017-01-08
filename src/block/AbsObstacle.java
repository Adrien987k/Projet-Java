package block;

import component.Coordinate;
import game.GameMap;
import lemming.Priority;
import view.Type;

/**
 * Higher class of the obstacle blocks hierarchy
 * 
 * @author Adrien
 *
 */
public abstract class AbsObstacle extends AbsBlock {
	
	/**
	 * 
	 * @param coordinate   Coordinate on the map
	 * @param priority 	   Display priority
	 * @param type         Type of component
	 * @param gameMap      The map it belong to 
	 */
	public AbsObstacle(Coordinate coordinate, Priority priority, Type type, GameMap gameMap) {
		super(coordinate, priority, type, gameMap);
	}
	
	/**
	 * Return true it is an obstacle so moving component turn back when they encounter it 
	 */
	@Override
	public boolean isInverting(){
		return true;
	}

}

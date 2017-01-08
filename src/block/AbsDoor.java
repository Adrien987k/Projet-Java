package block;

import component.Coordinate;
import game.GameMap;
import lemming.Priority;
import view.Type;

/**
 * Abstract class for doors
 * 
 * @author Adrien
 *
 */
public abstract class AbsDoor extends AbsBlock {
	
	/**
	 * 
	 * @param coordinate   Coordinate on the map
	 * @param priority 	   Display priority
	 * @param type         Type of component
	 * @param gameMap      The map it belong to 
	 */
	public AbsDoor(Coordinate coordinate, Priority priority, Type type, GameMap gameMap) {
		super(coordinate, priority, type, gameMap);
	}
	
	/**
	 * Return false because a moving component have to enter it
	 */
	@Override
	public boolean isInverting(){
		return false;
	}

}

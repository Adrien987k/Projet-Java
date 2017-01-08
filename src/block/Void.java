package block;

import component.Coordinate;
import game.GameMap;
import lemming.Priority;
import view.Type;

/**
 * This block represent the void in the map
 * 
 * @author Adrien
 *
 */
public class Void extends AbsBlock {
	
	/**
	 * 
	 * @param coordinate   Coordinate on the map
	 * @param gameMap      The map it belong to 
	 */
	public Void(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, Priority.PRIORITY_BLOCK, Type.VOID, gameMap);
	}
	
	/**
	 * Obviously return true
	 */
	@Override
	public boolean isVoid(){
		return true;
	}
	
	/**
	 * Obviously return false
	 */
	@Override
	public boolean isInverting(){
		return false;
	}

}

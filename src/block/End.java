package block;

import component.Coordinate;
import game.GameMap;
import lemming.Lemming;
import lemming.Priority;
import view.Type;

/**
 * This block is the end of the level
 * 
 * @author Adrien
 *
 */
public class End extends AbsDoor {
	
	/**
	 * 
	 * @param coordinate   Coordinate on the map
	 * @param gameMap      The map it belong to 
	 */
	public End(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, Priority.PRIORITY_BLOCK, Type.END, gameMap);
	}
	
	/**
	 * When a lemming collides with it it kill him
	 */
	@Override
	public boolean collision(Lemming lemming) {
		killLemming(lemming);
		return true;
	}
	
	/**
	 * Return false because a lemming have to enter it
	 */
	@Override
	public boolean canBeSkipped(){
		return false;
	}
	
	/**
	 * Before destroying the lemming it set him free
	 */
	@Override
	public void killLemming(Lemming lemming){
		lemming.setFree();
		lemming.destroy();
	}

}

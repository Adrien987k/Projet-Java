package block;

import component.Coordinate;
import game.GameMap;
import lemming.Priority;
import view.Type;

/**
 * This block reappears when it is destroy
 * 
 * @author Adrien
 *
 */
public class Again extends AbsDestructible {
	
	/**
	 * Number of generation of this block
	 */
	public static final int NB_GENERATION = 3;
	
	/**
	 * Remaining number of generation of this block
	 */
	private int nbBlockToGenerate = NB_GENERATION;
	
	
	/**
	 * 
	 * @param coordinate   Coordinate on the map
	 * @param gameMap      The map it belong to 
	 */
	public Again(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, Priority.PRIORITY_BLOCK, Type.AGAIN, gameMap);
		
	}
	
	/**
	 * If there is no generation left this this block disappear
	 */
	@Override
	public void destroy(){
		if(nbBlockToGenerate > 0){
			getGameMap().add(getCoordinate(), this);
			nbBlockToGenerate--;
		} else {
			super.destroy();
		}
	}

}

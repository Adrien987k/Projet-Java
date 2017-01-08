package block;

import java.util.List;

import component.Component;
import component.Coordinate;
import game.GameMap;
import lemming.Priority;
import view.Type;

/**
 * This block explode when it is mined
 * 
 * @author Adrien
 *
 */
public class Bomb extends AbsDestructible {
	
	/**
	 * Size of the explosion's range
	 */
	public static final int BOMB_EXPLOSION_RANGE = 2;
	
	/**
	 * Indicate if the block has just been destroy is order to prevent recursive calls in method boom
	 */
	private boolean hasBeenDestroy = false;
	
	/**
	 * 
	 * @param coordinate   Coordinate on the map
	 * @param gameMap      The map it belong to 
	 */
	public Bomb(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, Priority.PRIORITY_BLOCK, Type.BOMB, gameMap);
	}
	
	/**
	 * Destroy this only if it has not been destroy by an other component or by himself with a recursive call
	 */
	@Override
	public void destroy(){
		super.destroy();
		if(!hasBeenDestroy) {
			hasBeenDestroy = true;
			boom();
		}
	}
	
	/**
	 * Destroy all component in the explosion's range
	 */
	public void boom(){
		List<Component> area;
		int posX = getCoordinate().getX();
		int posY = getCoordinate().getY();
		GameMap gameMap = getGameMap();
		for(int i = -BOMB_EXPLOSION_RANGE; i <= BOMB_EXPLOSION_RANGE; i++){
			for(int j = -BOMB_EXPLOSION_RANGE; j <= BOMB_EXPLOSION_RANGE; j++){
				area = gameMap.getArea(new Coordinate(posX + i, posY + j));
				for(Component component : area){
					if(component.isDestructible()) component.destroy();
				}
			}
		}
	}
	
}

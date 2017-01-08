package block;

import component.Component;
import component.Coordinate;
import game.GameMap;
import lemming.Lemming;
import lemming.Priority;
import view.Type;

/**
 * Higher class of the Block hierarchy
 * 
 * @author Adrien
 *
 */
public abstract class AbsBlock extends Component {
	
	/**
	 * 
	 * @param coordinate   Coordinate on the map
	 * @param priority 	   Display priority
	 * @param type         Type of component
	 * @param gameMap      The map it belong to 
	 */
	public AbsBlock(Coordinate coordinate, Priority priority, Type type, GameMap gameMap) {
		super(coordinate, priority, type, gameMap);
	}
	
	/**
	 * kill is override with no code because a block cannot be killed
	 */
	@Override
	public void kill() {
		
	}
	
	/**
	 * step is override with no code because by default a block does not move
	 */
	@Override
	public void step() {
		
	}
	
	/**
	 * Replace this block with a void block
	 */
	@Override
	public void destroy(){
		GameMap gameMap = getGameMap();
		gameMap.change(this, gameMap.getFactory().make(Type.VOID, getCoordinate(), gameMap));
	}
	
	/**
	 * Return false because a block does not have by default interaction with other component
	 */
	@Override
	public boolean collision(Lemming lemming){
		return false;
	}
	
	/**
	 * Return false because by default a block is solid
	 */
	@Override
	public boolean isVoid(){
		return false;
	}
	
	/**
	 * 
	 */
	@Override
	public void killLemming(Lemming lemming){
		
	}
	
	/**
	 * Return false because by default a block is not destructible
	 */
	@Override
	public boolean isDestructible(){
		return false;
	}
	
	/**
	 * Return false because by default a block cannot be mined
	 */
	@Override
	public boolean canBeMined(){
		return false;
	}
	
	/**
	 * Return false because by default a block cannot be skipped
	 */
	@Override
	public boolean canBeSkipped(){
		return true;
	}
	
}

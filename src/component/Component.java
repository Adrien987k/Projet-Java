package component;

import States.State;
import game.GameMap;
import lemming.Lemming;
import lemming.Priority;
import view.Type;

/**
 * Higher class of the component hierarchy
 * A component is an entity that can be placed on the map
 * 
 * @author Adrien
 *
 */
public abstract class Component {
	
	/**
	 * The map it belong to
	 */
	private GameMap gameMap;
	
	/**
	 * Coordinate on the map
	 */
	private Coordinate coordinate;
	
	/**
	 * Display priority
	 */
	private Priority priority;
	
	/**
	 * Type of component
	 */
	private Type type;
	
	/**
	 * Indicate if this component can kill other component
	 */
	protected boolean isLethal = false;
	
	/**
	 * 
	 * @param coordinate   Coordinate on the map
	 * @param priority 	   Display priority
	 * @param type         Type of component
	 * @param gameMap      The map it belong to 
	 */
	public Component(Coordinate coordinate, Priority priority, Type type, GameMap gameMap) {
		this.coordinate = coordinate;
		this.priority = priority;
		this.type = type;
		this.gameMap = gameMap;
	}
	
	/*GETTERS AND SETTERS*/
	
	public Coordinate getCoordinate(){
		return coordinate;
	}
	
	public void setCoordinate(Coordinate coordinate){
		this.coordinate = coordinate;
	}
	
	public Priority getPriority(){
		return priority;
	}
	
	public void setPriority(Priority priority){
		this.priority = priority;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public GameMap getGameMap(){
		return gameMap;
	}
	
	public void setGameMap(GameMap gameMap){
		this.gameMap = gameMap;
	}
	
	public boolean isKilling(){
		return isLethal;
	}	
	
	/*END OF GETTERS AND SETTERS*/
	
	/**
	 * The code to execute for this component at each step of the game
	 */
	public abstract void step();
	
	/**
	 * The code to execute when this component is destroy
	 */
	public abstract void destroy();
	
	/**
	 * The code to execute when this component collide with a lemming
	 * 
	 * @param lemming   the lemming it collide with
	 * @return          return true if a collision occurred
	 */
	public abstract boolean collision(Lemming lemming);
	
	/**
	 * The code to execute when this component is kills
	 */
	public abstract void kill();
	
	/**
	 * The code to execute when this component have to kill a lemming
	 * 
	 * @param lemming   the lemming to kill
	 */
	public abstract void killLemming(Lemming lemming);
	
	/**
	 * Change the state of this component
	 * 
	 * @param state     the new state
	 * @return          return true if the state has been change
	 */
	public abstract boolean changeStateIf(State state);
	
	/**
	 * Return true if this component cannot be cross
	 */
	public abstract boolean isVoid();
	
	/**
	 * Return true if this component invert the direction of moving other component when they get through it
	 */
	public abstract boolean isInverting();
	
	/**
	 * Return true if this component can be destroy
	 */
	public abstract boolean isDestructible();
	
	/**
	 * Return true if this component can be mined
	 */
	public abstract boolean canBeMined();
	
	/**
	 * Return true if this component cannot be avoid
	 */
	public abstract boolean canBeSkipped();
	
}

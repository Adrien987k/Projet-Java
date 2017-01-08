package factory;

import game.GameMap;
import view.Type;

import component.Component;
import component.Coordinate;

/**
 * The interface for all the factory that create component
 * 
 * @author Adrien
 *
 */
public interface IFactory {
	
	/**
	 * Create and return a new component
	 * 
	 * @param t         The type of component to create
	 * @param coord     The initial coordinate the component
	 * @param gameMap   The map it belong to
	 * @return
	 */
	public Component make(Type t, Coordinate coord, GameMap gameMap);
	
}

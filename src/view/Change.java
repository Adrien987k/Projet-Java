package view;

import java.util.List;
import java.util.Map;

import component.Component;
import component.Coordinate;

/**
 * Change is a structure of data wich contains various informations used by Observer/Observable.
 * Each observable creates a change with the right constructor and set only revelant data.
 * Observers will only read those data and ignore the rest.
 * @author Arnaud
 *
 */

public class Change {
	private Coordinate coordinate;
	private ActionType actionType;
	private List<Type> changeType;
	private Component componentNext;
	private Component component;
	private int nbDeadLemmings;
	private int nbFreeLemmings;
	private int nbRemainingLemmings;
	private boolean running;
	private Map<String,Integer> levelParameters;
	
	/**
	 * Used to notify when a block is destroyed and usually replace by void.
	 * @param coordinate the case concerned
	 * @param component block to remove
	 * @param componentNext block to add
	 */
	public Change(Coordinate coordinate, Component component, Component componentNext){
		this.coordinate = coordinate;
		this.component = component;
		this.componentNext = componentNext;
	}
	/**
	 * Used to notify when a lemming has moved
	 * @param coordinate new lemming coordinates
	 * @param component lemming
	 */
	public Change(Coordinate coordinate, Component component){
		this.coordinate = coordinate;
		this.component = component;
		this.componentNext = component;
	}
	/**
	 * Used to notify the GUI what and where to display new changes
	 * @param coordinate
	 * @param changeType
	 */
	public Change(Coordinate coordinate, List<Type> changeType) {
		this.coordinate = coordinate;
		this.changeType = changeType;
	}
	
	/**
	 * Used to update the last action selected 
	 * @param actionType
	 */
	public Change(ActionType actionType) {
		this.actionType = actionType;
	}
	/**
	 * Use to notify where a change in the memory append.
	 * @param coordinate
	 */
	public Change(Coordinate coordinate){
		this.coordinate = coordinate;
	}
	/**
	 * Used to update informations about remaining, dead and free lemmings.
	 * @param nbDeadLemmings
	 * @param nbFreeLemmings
	 * @param nbRemainingLemmings
	 */
	public Change(int nbDeadLemmings, int nbFreeLemmings, int nbRemainingLemmings) {
		this.nbDeadLemmings = nbDeadLemmings;
		this.nbFreeLemmings = nbFreeLemmings;
		this.nbRemainingLemmings = nbRemainingLemmings;
	}
	/**
	 * Used to update informations about lemmings and the level, such as the amount of uses of diffrent actions.
	 * @param nbDeadLemmings
	 * @param nbFreeLemmings
	 * @param nbRemainingLemmings
	 * @param levelparameters
	 */
	public Change(int nbDeadLemmings, int nbFreeLemmings, int nbRemainingLemmings, Map<String,Integer> levelparameters) {
		this(nbDeadLemmings,nbFreeLemmings,nbRemainingLemmings);
		this.levelParameters = levelparameters;
	}
	/**
	 * Used to update informations about lemmings and the level, such as the amount of uses of diffrent actions, and to notify if the game is over.
	 * @param nbDeadLemmings
	 * @param nbFreeLemmings
	 * @param nbRemainingLemmings
	 * @param running
	 * @param levelparameters
	 */
	public Change(int nbDeadLemmings, int nbFreeLemmings, int nbRemainingLemmings, boolean running, Map<String,Integer> levelparameters) {
		this(nbDeadLemmings,nbFreeLemmings,nbRemainingLemmings,levelparameters);
		this.running = running;
	}
	
	/* GETTERS AND SETTERS*/
	public Component getComponent(){
		return component;
	}
	
	public Component getComponentNext(){
		return componentNext;
	}
	
	public ActionType getActionType() {
		return actionType;
	}
	
	public List<Type> getChangeType(){
		return changeType;
	}
	
	public Coordinate getCoordinate(){
		return coordinate;
	}
	
	public Map<String, Integer> getLevelParameters() {
		return levelParameters;
	}
	
	public void setLevelParameters(Map<String, Integer> levelParameters) {
		this.levelParameters = levelParameters;
	}
	
	public int getNbDeadLemmings() {
		return nbDeadLemmings;
	}
	
	public void setNbDeadLemmings(int nbDeadLemmings) {
		this.nbDeadLemmings = nbDeadLemmings;
	}

	public int getNbFreeLemmings() {
		return nbFreeLemmings;
	}
	
	public void setNbFreeLemmings(int nbFreeLemmings) {
		this.nbFreeLemmings = nbFreeLemmings;
	}

	public int getNbRemainingLemmings() {
		return nbRemainingLemmings;
	}
	
	public void setNbRemainingLemmings(int nbRemainingLemmings) {
		this.nbRemainingLemmings = nbRemainingLemmings;
	}
	
	public boolean getRunning() {
		return running;
	}
	/* END OF GETTERS AND SETTERS*/
}

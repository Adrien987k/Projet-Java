package view;

import java.util.List;
import java.util.Map;

import component.Component;
import component.Coordinate;

/**
 * Change is a structure of data wich contains various informations used by Observer/Observable.
 * Each observable creates a change with his 
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
	
	public Change(Coordinate coordinate, Component component, Component componentNext){
		this.coordinate = coordinate;
		this.component = component;
		this.componentNext = componentNext;
	}
	public Change(Coordinate coordinate, Component component){
		this.coordinate = coordinate;
		this.component = component;
		this.componentNext = component;
	}
	public Change(Coordinate coordinate, List<Type> changeType) {
		this.coordinate = coordinate;
		this.changeType = changeType;
	}
	public Change(ActionType actionType) {
		this.actionType = actionType;
	}
	public Change(Coordinate coordinate){
		this.coordinate = coordinate;
	}
	public Change(int nbDeadLemmings, int nbFreeLemmings, int nbRemainingLemmings) {
		this.nbDeadLemmings = nbDeadLemmings;
		this.nbFreeLemmings = nbFreeLemmings;
		this.nbRemainingLemmings = nbRemainingLemmings;
	}
	public Change(int nbDeadLemmings, int nbFreeLemmings, int nbRemainingLemmings, Map<String,Integer> levelparameters) {
		this(nbDeadLemmings,nbFreeLemmings,nbRemainingLemmings);
		this.levelParameters = levelparameters;
	}
	public Change(int nbDeadLemmings, int nbFreeLemmings, int nbRemainingLemmings, boolean running, Map<String,Integer> levelparameters) {
		this(nbDeadLemmings,nbFreeLemmings,nbRemainingLemmings,levelparameters);
		this.running = running;
	}
	
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
}

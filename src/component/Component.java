package component;

import States.State;
import game.GameMap;
import lemming.Lemming;
import lemming.Priority;
import view.Type;

public abstract class Component {
	
	private GameMap gameMap;
	
	private Coordinate coordinate;
	private Priority priority;
	private Type type;
	
	protected boolean isLethal = false;
	
	public Component() {
		coordinate = new Coordinate();
	}
	
	public Component(Coordinate coordinate, Priority priority, Type type, GameMap gameMap) {
		this.coordinate = coordinate;
		this.priority = priority;
		this.type = type;
		this.gameMap = gameMap;
	}
	
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
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
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
	
	
	public boolean changeStateIf(State state) {
		return false;
	}
	
	public abstract void step();
	public abstract void destroy();
	public abstract boolean collision(Lemming lemming);
	public abstract boolean isVoid();
	public abstract boolean isInverting();
	public abstract void kill();
	public abstract void killLemming(Lemming lemming);
	public abstract boolean isDestructible();
	public abstract boolean canBeMined();
	public abstract boolean canBeSkipped();	
	
	
}

package component;

import game.GameMap;
import lemming.Lemming;
import view.ChangeMemory;
import view.Type;

public abstract class Component {
	
	public static final int PRIORITY_BLOCK = 0;
	public static final int PRIORITY_LEMMING_WEAK = 1;
	public static final int PRIORITY_LEMMING_MEDIUM = 2;
	public static final int PRIORITY_LEMMING_HIGH = 3;
	
	private GameMap gameMap;
	
	private Coordinate coordinate;
	private int priority;
	private Type type;
	
	protected boolean isLethal = false;
	
	public Component() {
		coordinate = new Coordinate();
	}
	
	public Component(Coordinate coordinate, int priority, Type type, GameMap gameMap) {
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
	
	public int getPriority(){
		return priority;
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
	
	//TODO Si c'est un lemming juste le retirer
	public void destroy(){
		gameMap.change(this, gameMap.getFactory().make(Type.VOID, getCoordinate(), gameMap));
	}
	
	public abstract void step();
	public abstract boolean isVoid();
	public abstract boolean isInverting();
	public abstract void killLemming(Lemming lemming);
	public abstract boolean isDestructible();
	public abstract boolean canBeMined();
	
}

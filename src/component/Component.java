package component;

import view.Type;

public abstract class Component {
	
	public static final int PRIORITY_BLOCK = 0;
	public static final int PRIORITY_LEMMING_WEAK = 1;
	public static final int PRIORITY_LEMMING_MEDIUM = 2;
	public static final int PRIORITY_LEMMING_HIGH = 3;
	
	private Coordinate coordinate;
	private int priority;
	private Type type;
	
	public Component() {
		coordinate = new Coordinate();
	}
	
	public Component(Coordinate coordinate, int priority, Type type) {
		this.coordinate = coordinate;
		this.priority = priority;
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
	
	public abstract void step();
}

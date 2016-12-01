package component;

public abstract class Component {
	
	public static final int PRIORITY_BLOCK = 0;
	public static final int PRIORITY_LEMMING_WEAK = 1;
	public static final int PRIORITY_LEMMING_MEDIUM = 2;
	public static final int PRIORITY_LEMMING_HIGH = 3;
	
	protected Coordinate coordinate;
	protected int priority;
	
	public Component() {
		coordinate = new Coordinate();
	}
	
	public Component(Coordinate coordinate, int priority) {
		this.coordinate = coordinate;
		this.priority = priority;
	}
	
	public Coordinate getCoordinate(){
		return coordinate;
	}
	
	public void setCoordinate(Coordinate coordinate){
		this.coordinate = coordinate;
	}
}

package component;

import lemming.Direction;

public class Coordinate {
	
	private int x;
	private int y;
	
	public Coordinate() {
		this.x = 0;
		this.y = 0;
	}
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public Coordinate checkDirection(Direction direction){
		return new Coordinate(x + direction.getX(), y + direction.getY());
	}
	
	public boolean equals(Coordinate c) {
		return (c.x == x)&&(c.y == y);
	}
	
}

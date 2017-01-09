package component;

import lemming.Direction;

/**
 * This class represent a 2D coordinate on the map
 * 
 * @author Adrien
 *
 */
public class Coordinate {
	
	/**
	 * The horizontal coordinate
	 */
	private int x;
	
	/**
	 * The vertical coordinate
	 */
	private int y;
	
	/**
	 * @param x   The horizontal coordinate
	 * @param y   The vertical coordinate
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/*GETTERS AND SETTERS*/
	
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
	
	/*END OF GETTERS AND SETTERS*/
	
	/**
	 * Add the specified direction to a copy of this and return the result
	 * 
	 * @param direction   The direction to add
	 * @return            The new coordinate
	 */
	public Coordinate checkDirection(Direction direction){
		return new Coordinate(x + direction.getX(), y + direction.getY());
	}
	
	/**
	 * Return true if the specify coordinate is equal to this
	 */
	public boolean equals(Coordinate c) {
		return (c.x == x) && (c.y == y);
	}
	
}

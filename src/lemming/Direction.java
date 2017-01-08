package lemming;

/**
 * This class is use to represent the direction of a moving component
 * 
 * @author Adrien
 *
 */
public class Direction {
	
	/*All static method that return a new Direction */
	
	public static Direction UP(){ return new Direction(-1, 0); }
	public static Direction DOWN(){ return new Direction(1, 0); }
	public static Direction LEFT(){ return new Direction(0, -1); }
	public static Direction RIGHT(){ return new Direction(0, 1); }
	
	public static Direction UP_RIGHT(){ return new Direction(-1, 1); }
	public static Direction UP_LEFT(){ return new Direction(-1, -1); }
	public static Direction DOWN_RIGHT(){ return new Direction(1, 1); }
	public static Direction DOWN_LEFT(){ return new Direction(1, -1); }
	
	/**
	 * The x coordinate of the direction
	 */
	private int x;
	
	/**
	 * The y coordinate of the direction
	 */
	private int y;
	
	/**
	 * 
	 * @param x   The x coordinate of the direction
	 * @param y   The y coordinate of the direction
	 */
	public Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/*GETTERS AND SETTERS*/
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	/*AND OF GETTERS AND SETTERS*/
	
	/**
	 * Invert this direction
	 */
	public void invert() {
		x = -x;
		y = -y;
	}
	
	/**
	 * Create and return a new Direction resulting of the addition of this plus an other specified Direction 
	 */
	public Direction checkAdd(Direction direction){
		return new Direction(x + direction.x, y + direction.y);
	}
	
}

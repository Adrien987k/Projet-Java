package lemming;

public class Direction {
	
	public static Direction UP(){ return new Direction(-1, 0); }
	public static Direction DOWN(){ return new Direction(1, 0); }
	public static Direction LEFT(){ return new Direction(0, -1); }
	public static Direction RIGHT(){ return new Direction(0, 1); }
	
	public static Direction UP_RIGHT(){ return new Direction(-1, 1); }
	public static Direction UP_LEFT(){ return new Direction(-1, -1); }
	public static Direction DOWN_RIGHT(){ return new Direction(1, 1); }
	public static Direction DOWN_LEFT(){ return new Direction(1, -1); }
	
	/*public static final Direction UP = new Direction(-1, 0);
	public static final Direction DOWN = new Direction(1, 0);
	public static final Direction LEFT = new Direction(0, -1);
	public static final Direction RIGHT = new Direction(0, 1);
	
	public static final Direction UP_RIGHT = new Direction(-1, 1);
	public static final Direction UP_LEFT = new Direction(-1, -1);
	public static final Direction DOWN_RIGHT = new Direction(1, 1);
	public static final Direction DOWN_LEFT = new Direction(1, -1);*/

	private int x;
	private int y;

	public Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

	public void invert() {
		x = -x;
		y = -y;
	}
	
	public Direction checkAdd(Direction direction){
		return new Direction(x + direction.x, y + direction.y);
	}
}

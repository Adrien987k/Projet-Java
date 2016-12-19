package lemming;

public enum Direction {
	UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);

	public int x;
	public int y;

	Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void invert() {
		x = -x;
		y = -y;
	}
}

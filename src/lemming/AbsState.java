package lemming;

import view.Type;

public abstract class AbsState implements IActionPanel {
	
	protected Lemming lemming;
	
	public AbsState(Lemming lemming) {
		this.lemming = lemming;
	}
	
	public abstract void step();
	public abstract void move();
	public abstract void construct();
	public abstract void destroy();
	public abstract Type getTypeByState();
	
	public boolean isInverting(){
		return false;
	}
	
}

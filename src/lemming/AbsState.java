package lemming;

import view.Type;

public abstract class AbsState {
	
	protected Lemming lemming;
	
	public AbsState(Lemming lemming) {
		this.lemming = lemming;
	}
	
	public abstract void step(Lemming lemming);
	public abstract void move(Lemming lemming);
	public abstract void construct(Lemming lemming);
	public abstract void destroy(Lemming lemming);
	public abstract Type getTypeByState();
	
}

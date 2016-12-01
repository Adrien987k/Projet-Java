package lemming;

public abstract class AbsState {
	
	public AbsState() {
		
	}
	
	public abstract void step(Lemming lemming);
	public abstract void move(Lemming lemming);
	public abstract void construct(Lemming lemming);
	public abstract void destroy(Lemming lemming);

}

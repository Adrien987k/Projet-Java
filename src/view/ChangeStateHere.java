package view;

import States.State;
import component.Coordinate;

public class ChangeStateHere extends AbsMemoryChange {
	private State state;
	
	public ChangeStateHere(Coordinate next, State state) {
		super(next);
		this.state = state;
	}
	
	public State getState() {
		return state;
	}

}

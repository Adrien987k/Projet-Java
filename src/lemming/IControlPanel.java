package lemming;

import States.State;

public interface IControlPanel {
	public int kill(int how);
	public void changeState(State state);
}

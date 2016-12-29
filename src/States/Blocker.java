package States;

import lemming.AbsState;
import lemming.Lemming;
import view.Type;

public class Blocker extends AbsState {

	public Blocker(Lemming lemming) {
		super(lemming);
	}

	@Override
	public void step() {
		fall();
	}
	
	@Override
	public void move() {
		
	}

	@Override
	public Type getTypeByState() {
		return Type.BLOCKER;
	}
	
	@Override
	public boolean isInverting(){
		return true;
	}

}

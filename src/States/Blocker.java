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
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void construct() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Type getTypeByState() {
		return Type.BLOCKER;
	}
	
	public boolean isInverting(){
		return true;
	}

}

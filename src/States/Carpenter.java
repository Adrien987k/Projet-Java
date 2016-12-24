package States;

import lemming.AbsState;
import lemming.Lemming;
import view.Type;

public class Carpenter extends AbsState {
	
	public Carpenter(Lemming lemming) {
		super(lemming);
	}

	@Override
	public void step() {
		
	}

	@Override
	public void move() {
		
	}

	@Override
	public void construct() {
		
	}

	@Override
	public void destroy() {
		
	}
	
	@Override
	public Type getTypeByState() {
		return Type.CARPENTER;
	}
}

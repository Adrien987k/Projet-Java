package States;

import lemming.AbsState;
import lemming.Lemming;
import view.Type;

public class Walker extends AbsState {
	
	public Walker(Lemming lemming) {
		super(lemming);
	}

	@Override
	public void step() {
		boolean hasMoved;
		hasMoved = fall();
		if(!hasMoved) walk();
	}

	@Override
	public void construct() {
		
	}

	@Override
	public void destroy() {
		
	}
	
	@Override
	public Type getTypeByState() {
		return Type.WALKER;
	}

}

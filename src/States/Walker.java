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
		boolean hasMoved = collision();
		if(!hasMoved) hasMoved = fall();
		if(!hasMoved) walk();
	}
	
	@Override
	public Type getTypeByState() {
		return Type.WALKER;
	}

}

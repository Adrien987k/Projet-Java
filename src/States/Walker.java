package States;

import lemming.AbsState;
import lemming.Lemming;
import lemming.Priority;
import view.Type;

public class Walker extends AbsState {
	
	public Walker(Lemming lemming) {
		super(lemming);
	}

	@Override
	public void step() {
		boolean hasMoved = collision();
		if(!hasMoved) hasMoved |= fall();
		if(!hasMoved) walk();
	}
	
	@Override
	public Type getTypeByState() {
		return Type.WALKER;
	}
	
	@Override
	public Priority getPriority(){
		return Priority.PRIORITY_LEMMING_WEAK;
	}

}

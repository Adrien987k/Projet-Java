package States;

import lemming.AbsState;
import lemming.Direction;
import lemming.Lemming;
import lemming.Priority;
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
	public void move(Direction realDirection) {
		
	}

	@Override
	public Type getTypeByState() {
		return Type.BLOCKER;
	}
	
	@Override
	public Priority getPriority(){
		return Priority.PRIORITY_LEMMING_HIGH;
	}
	
	@Override
	public boolean isInverting(){
		return true;
	}

}

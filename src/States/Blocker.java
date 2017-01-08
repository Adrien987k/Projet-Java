package States;

import lemming.AbsState;
import lemming.Lemming;
import lemming.Priority;
import view.Type;

/**
 * The Blocker invert the direction of other lemming
 * 
 * @author Adrien
 *
 */
public class Blocker extends AbsState {
	
	/**
	 * 
	 * @param lemming   The lemming it belong to
	 */
	public Blocker(Lemming lemming) {
		super(lemming);
	}
	
	/**
	 * At each step a blocker can collide or fall or do nothing
	 */
	@Override
	public void step() {
		boolean hasMoved = collision();
		if(!hasMoved) fall();
	}

	@Override
	public Type getTypeByState() {
		return Type.BLOCKER;
	}
	
	@Override
	public Priority getPriority(){
		return Priority.PRIORITY_LEMMING_HIGH;
	}
	
	/**
	 * Return true because a blocker invert the direction of other lemming
	 */
	@Override
	public boolean isInverting(){
		return true;
	}

}

package States;

import lemming.AbsState;
import lemming.Lemming;
import lemming.Priority;
import view.Type;

/**
 * The walker is the default state
 * 
 * @author Adrien
 *
 */
public class Walker extends AbsState {
	
	/**
	 * 
	 * @param lemming   The lemming it belong to
	 */
	public Walker(Lemming lemming) {
		super(lemming);
	}

	/**
	 * A Walker can collide or fall or walk
	 */
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

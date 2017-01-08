package States;

import java.util.List;

import component.Component;

import lemming.AbsState;
import lemming.Direction;
import lemming.Lemming;
import lemming.Priority;
import view.Type;

/**
 * A Parachutist cannot die after falling
 * He fall slowly
 * 
 * @author Adrien
 *
 */
public class Parachutist extends AbsState {
	
	/**
	 * Indicate if the lemming has fell at the last step
	 */
	private boolean hasAlreadyFell = false;
	
	/**
	 * 
	 * @param lemming   The lemming it belong to
	 */
	public Parachutist(Lemming lemming) {
		super(lemming);
	}

	/**
	 * A Parachutist can collide or fall or walk
	 */
	@Override
	public void step() {
		boolean hasMoved = collision();
		if(!hasMoved) hasMoved |= fall();
		if(!hasMoved) walk();
	}
	
	/**
	 * The method fall is override because a parachutist cannot die after falling
	 * and fall only one step on two
	 */
	@Override
	public boolean fall(){
		List<Component> down = lemming.checkSide(Direction.DOWN());
		boolean canFall = true;
		boolean dieIfFalling = false;
		for(Component component : down){
			if(!component.isVoid() && !component.isKilling()){
				canFall = false;
			}
			if(component.isKilling()) dieIfFalling = true;
			if(!component.canBeSkipped()){
				canFall = true;
				continue;
			}
		}
		if(canFall){
			if(!hasAlreadyFell){
				if(dieIfFalling){
					lemming.destroy();
					return true;
				}
				lemming.incFalling();
				hasAlreadyFell = true;
				move(Direction.DOWN());
			} else {
				hasAlreadyFell = false;
			}
			return true;
		}
		return false;
	}
	
	@Override
	public Type getTypeByState() {
		return Type.PARACHUTIST;
	}
	
	@Override
	public Priority getPriority(){
		return Priority.PRIORITY_LEMMING_MEDIUM;
	}
	
}

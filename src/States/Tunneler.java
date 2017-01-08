package States;

import java.util.List;

import component.Component;
import lemming.AbsState;
import lemming.Lemming;
import lemming.Priority;
import view.Type;

/**
 * The Tunneler can dig horizontally
 * 
 * @author Adrien
 *
 */
public class Tunneler extends AbsState {
	
	/**
	 * Indicate if the tunneler has already dug 
	 */
	private boolean hasDug = false;
	
	/**
	 * 
	 * @param lemming   The lemming it belong to
	 */
	public Tunneler(Lemming lemming) {
		super(lemming);
	}

	/**
	 * A tunneler can collide or fall or dig or walk
	 * If he has already dug and he cannot walk anymore he became a walker
	 */
	@Override
	public void step() {
		boolean hasWalked = false;
		boolean hasMoved = collision();
		if(!hasMoved) hasMoved |= fall();
		if(!hasMoved) hasMoved |= dig();
		if(!hasMoved) hasWalked =  walk();
		if(hasWalked && hasDug) lemming.changeState(State.WALKER);
	}
	
	/**
	 * Check if the lemming can dig in front of him
	 * If yes he remove the block in front of him
	 * 
	 * @return Return if the lemming has dug or not
	 */
	public boolean dig(){
		List<Component> foward = lemming.checkSide(lemming.getDesiredDirection());
		Component componentToMine = null;
		for(Component component : foward){
			if(component.canBeMined()){
				componentToMine = component;
			}
		}
		if(componentToMine != null){
			componentToMine.destroy();
			hasDug = true;
			return true;
		}
		return false;
	}
	
	@Override
	public Type getTypeByState() {
		return Type.TUNNELER;
	}
	
	@Override
	public Priority getPriority(){
		return Priority.PRIORITY_LEMMING_MEDIUM;
	}
	
}	

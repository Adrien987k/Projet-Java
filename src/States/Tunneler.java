package States;

import java.util.List;

import component.Component;
import lemming.AbsState;
import lemming.Lemming;
import lemming.Priority;
import view.Type;

public class Tunneler extends AbsState {
	
	private boolean hasDug = false;
	
	public Tunneler(Lemming lemming) {
		super(lemming);
	}

	@Override
	public void step() {
		boolean hasWalked = false;
		boolean hasMoved = collision();
		if(!hasMoved) hasMoved |= fall();
		if(!hasMoved) hasMoved |= dig();
		if(!hasMoved) hasWalked =  walk();
		if(hasWalked && hasDug) lemming.changeState(State.WALKER);
	}
	
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

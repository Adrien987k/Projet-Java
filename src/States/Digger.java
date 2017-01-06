package States;

import java.util.List;

import lemming.AbsState;
import lemming.Direction;
import lemming.Lemming;
import lemming.Priority;
import view.Type;
import component.Component;

public class Digger extends AbsState {
	
	public static final int NB_BLOCK_TO_DIG = 5;
	
	private int nbBlockToDig = NB_BLOCK_TO_DIG;
	private boolean hasDug = false;
	
	public Digger(Lemming lemming) {
		super(lemming);
	}

	@Override
	public void step() {
		boolean hasWalked = false;
		boolean hasMoved = collision();
		if(!hasMoved) hasMoved |= fall();
		if(!hasMoved) hasMoved |= dig();
		if(!hasMoved){
			hasWalked = walk();
		}
		if(nbBlockToDig == 0 || (hasWalked && hasDug)){
			lemming.changeState(State.WALKER);
		}
	}
	
	public boolean dig(){
		List<Component> down = lemming.checkSide(Direction.DOWN());
		Component componentToMine = null;
		for(Component component : down){
			if(component.canBeMined()){
				componentToMine = component;
			}
		}
		if(componentToMine != null){
			componentToMine.destroy();
			nbBlockToDig--;
			hasDug = true;
			return true;
		}
		return false;
	}
	
	@Override
	public Type getTypeByState() {
		return Type.DIGGER;
	}
	
	@Override
	public Priority getPriority(){
		return Priority.PRIORITY_LEMMING_HIGH;
	}
	
}

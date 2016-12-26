package States;

import java.util.List;

import component.Component;

import lemming.AbsState;
import lemming.Direction;
import lemming.Lemming;
import view.Type;

public class Parachutist extends AbsState {
	
	private boolean hasAlreadyFell = false;
	
	public Parachutist(Lemming lemming) {
		super(lemming);
	}

	@Override
	public void step() {
		boolean hasMoved = false;
		hasMoved = fall();
		if(!hasMoved) walk();
	}
	
	@Override
	public boolean fall(){
		List<Component> down = lemming.checkSide(Direction.DOWN);
		boolean canFall = true;
		boolean dieIfFalling = true;
		for(Component component : down){
			if(!component.isVoid() && !component.isKilling()){
				canFall = false;
			}
			if(component.isKilling()) dieIfFalling = true; 
		}
		if(canFall){
			if(!hasAlreadyFell){
				if(dieIfFalling){
					lemming.destroy();
					return true;
				}
				lemming.setRealDirection(Direction.DOWN);
				lemming.decFalling();
				hasAlreadyFell = true;
				move();
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
}

package States;

import java.util.List;

import component.Component;

import lemming.AbsState;
import lemming.Direction;
import lemming.Lemming;
import lemming.Priority;
import view.Type;

public class Climber extends AbsState	{
	
	public Climber(Lemming lemming) {
		super(lemming);
	}

	@Override
	public void step() {
		boolean hasWalked = true;
		boolean hasMoved = collision();
		if(!hasMoved) hasMoved |= fall();
		if(!hasMoved){
			hasWalked = walk();
		}
		if(!hasWalked){
			lemming.changeState(State.PARACHUTIST);
		}
	}
	
	@Override
	public boolean fall(){
		List<Component> down = lemming.checkSide(Direction.DOWN());
		List<Component> foward = lemming.checkSide(lemming.getDesiredDirection());
		boolean fowardIsVoid = true;
		for(Component fcomponent : foward){
			if(!fcomponent.isVoid()) fowardIsVoid = false;
		}
		boolean canFall = true;
		boolean dieIfFalling = false;
		for(Component component : down){
			if(!component.isVoid() && !component.isKilling()){
				canFall = false;
			}
			if(component.isKilling()) dieIfFalling = true; 
		}
		if(canFall && !dieIfFalling && fowardIsVoid){
			lemming.decFalling();
			move(Direction.DOWN());
			return true;
		}
		if(lemming.getFalling() <= 0 || dieIfFalling){
			lemming.destroy();
			return true;
		}
		lemming.resetFalling();
		return false;
	}
	
	@Override
	public boolean walk(){
		if(walkDiag()) return true;
		List<Component> foward = lemming.checkSide(lemming.getDesiredDirection());
		List<Component> top = lemming.checkSide(Direction.UP());
		boolean canGoUp = false;
		boolean canGoFoward = true;
		for(Component fcomponent : foward){
			if(fcomponent.isKilling()){
				fcomponent.killLemming(lemming);
				return true;
			}
			if(!fcomponent.isVoid()){
				canGoUp = true;
				canGoFoward = false;
			}
			if(!fcomponent.canBeSkipped()) {
				canGoUp = false;
				continue;
			}
		}
		for(Component tcomponent : top){
			if(!tcomponent.isVoid()) canGoUp = false;
		}
		if(canGoUp){
			move(Direction.UP());
			return true;
		}
		if(canGoFoward) {
			move(lemming.getDesiredDirection());
			return true;
		}
		return false;
	}
	
	@Override
	public Type getTypeByState() {
		return Type.CLIMBER;
	}
	
	@Override
	public Priority getPriority(){
		return Priority.PRIORITY_LEMMING_HIGH;
	}
	
}

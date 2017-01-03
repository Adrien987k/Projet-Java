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
		boolean hasMoved = collision();
		if(!hasMoved) hasMoved |= fall();
		if(!hasMoved) walk();
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
		boolean canGoUp = false;
		for(Component fcomponent : foward){
			if(fcomponent.isKilling()){
				fcomponent.killLemming(lemming);
				return true;
			}
			if(!fcomponent.isVoid()) canGoUp = true;
			if(!fcomponent.canBeSkipped()) {
				canGoUp = false;
				continue;
			}
		}
		if(canGoUp){
			lemming.setRealDirection(Direction.UP());
			move(Direction.UP());
			return true;
		}
		move(lemming.getDesiredDirection());
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

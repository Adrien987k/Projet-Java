package States;

import java.util.List;

import component.Component;

import lemming.AbsState;
import lemming.Direction;
import lemming.Lemming;
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
	public boolean walk(){
		List<Component> foward = lemming.checkSide(lemming.getDesiredDirection());
		List<Component> diagonal = lemming.checkSide(lemming.getDesiredDirection().checkAdd(Direction.UP()));
		boolean diagIsVoid = true;
		boolean fowardIsNotVoid = false;
		for(Component dcomponent : diagonal){
			if(!dcomponent.isVoid()) diagIsVoid = false;
		}
		for(Component fcomponent : foward){
			if(!fcomponent.isVoid()) fowardIsNotVoid = true;
			if(!fcomponent.canBeSkipped()) diagIsVoid = false;
		}
		if(diagIsVoid && fowardIsNotVoid){
			lemming.setRealDirection(lemming.getDesiredDirection().checkAdd(Direction.UP()));
			move();
			return true;
		}
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
			move();
			return true;
		}
		lemming.setRealDirection(lemming.getDesiredDirection());
		move();
		return false;
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
			lemming.setRealDirection(Direction.DOWN());
			lemming.decFalling();
			move();
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
	public Type getTypeByState() {
		return Type.CLIMBER;
	}
}

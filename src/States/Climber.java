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
		boolean hasMoved = fall();
		if(!hasMoved) walk();
	}
	
	@Override
	public boolean walk(){
		List<Component> foward = lemming.checkSide(lemming.getDesiredDirection());
		boolean canGoUp = true;
		for(Component fcomponent : foward){
			if(fcomponent.isKilling()){
				fcomponent.killLemming(lemming);
				return true;
			}
			if(!fcomponent.isVoid()){
				canGoUp = false;
			}
		}
		if(canGoUp){
			lemming.setRealDirection(Direction.UP);
			move();
			return true;
		}
		lemming.setRealDirection(lemming.getDesiredDirection());
		move();
		return false;
	}
	
	@Override
	public boolean fall(){
		List<Component> down = lemming.checkSide(Direction.DOWN);
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
			if(!component.isKilling()) dieIfFalling = true; 
		}
		if(canFall && !dieIfFalling && !fowardIsVoid){
			lemming.setRealDirection(Direction.DOWN);
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

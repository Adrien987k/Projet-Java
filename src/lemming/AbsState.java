package lemming;

import java.util.List;

import component.Component;
import view.Type;

public abstract class AbsState {
	
	protected Lemming lemming;
	
	public AbsState(Lemming lemming) {
		this.lemming = lemming;
	}
	
	public boolean collision(){
		List<Component> here = lemming.getGameMap().getArea(lemming.getCoordinate());
		boolean hasMoved = false;
		for(Component hcomponent : here){
			hasMoved |= hcomponent.collision(lemming);
		}
		return hasMoved;
	}
	
	public boolean fall(){
		List<Component> down = lemming.checkSide(Direction.DOWN);
		boolean canFall = true;
		boolean dieIfFalling = false;
		for(Component component : down){
			if(!component.isVoid() && !component.isKilling()){
				canFall = false;
			}
			if(component.isKilling()) dieIfFalling = true; 
		}
		if(canFall && !dieIfFalling){
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
	
	public boolean walk(){
		List<Component> foward = lemming.checkSide(lemming.getDesiredDirection());
		List<Component> diagonal = lemming.checkSide(lemming.getDesiredDirection().checkAdd(Direction.UP));
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
			lemming.setRealDirection(lemming.getDesiredDirection().checkAdd(Direction.UP));
			move();
			return true;
		}
		for(Component fcomponent : foward){
			if(fcomponent.isKilling()){
				fcomponent.killLemming(lemming);
				return true;
			} else if(fcomponent.isInverting()){
				lemming.getDesiredDirection().invert();
				lemming.setRealDirection(lemming.getDesiredDirection());
				move();
				return true;
			}
		}
		lemming.setRealDirection(lemming.getDesiredDirection());
		move();
		return false;
	}
	
	public void move() {
		lemming.getGameMap().move(lemming.getCoordinate().checkDirection(lemming.getRealDirection()), lemming);
	}
	
	public abstract void step();
	public abstract Type getTypeByState();
	
	public boolean isInverting(){
		return false;
	}
	
}

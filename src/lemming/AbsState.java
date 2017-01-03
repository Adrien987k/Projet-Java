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
		List<Component> down = lemming.checkSide(Direction.DOWN());
		boolean canFall = true;
		boolean dieIfFalling = false;
		for(Component component : down){
			if(!component.isVoid() && !component.isKilling()){
				canFall = false;
			}
			if(component.isKilling()) dieIfFalling = true; 
		}
		if(canFall && !dieIfFalling){
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
	
	public boolean walkDiag(){
		List<Component> foward = lemming.checkSide(lemming.getDesiredDirection());
		List<Component> diagonal = lemming.checkSide(lemming.getDesiredDirection().checkAdd(Direction.UP()));
		List<Component> top = lemming.checkSide(Direction.UP());
		boolean diagIsVoid = true;
		boolean fowardIsNotVoid = false;
		boolean topIsVoid = true;
		for(Component dcomponent : diagonal){
			if(!dcomponent.isVoid()) diagIsVoid = false;
		}
		for(Component fcomponent : foward){
			if(!fcomponent.isVoid()) fowardIsNotVoid = true;
			if(!fcomponent.canBeSkipped()) diagIsVoid = false;
		}
		for(Component tcomponent : top){
			if(!tcomponent.isVoid()) topIsVoid = false;
		}
		if(diagIsVoid && fowardIsNotVoid && topIsVoid){
			move(lemming.getDesiredDirection().checkAdd(Direction.UP()));
			return true;
		}
		return false;
	}
	
	public boolean walk(){
		if(walkDiag()) return true;
		List<Component> foward = lemming.checkSide(lemming.getDesiredDirection());
		for(Component fcomponent : foward){
			if(fcomponent.isKilling()){
				fcomponent.killLemming(lemming);
				return true;
			} else if(fcomponent.isInverting()){
				lemming.invertDirection();
				move(lemming.getDesiredDirection());
				return true;
			}
		}
		move(lemming.getDesiredDirection());
		return false;
	}
	
	public void move(Direction realDirection) {
		lemming.setRealDirection(realDirection);
		lemming.getGameMap().move(lemming.getCoordinate().checkDirection(lemming.getRealDirection()), lemming);
	}
	
	public abstract void step();
	public abstract Type getTypeByState();
	public abstract Priority getPriority();
	
	public boolean isInverting(){
		return false;
	}
	
}

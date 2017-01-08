package States;

import java.util.List;

import component.Component;

import lemming.AbsState;
import lemming.Direction;
import lemming.Lemming;
import lemming.Priority;
import view.Type;

/**
 * A climber can climb the walls
 * 
 * @author Adrien
 *
 */
public class Climber extends AbsState	{
	
	/**
	 * 
	 * @param lemming   The lemming it belong to
	 */
	public Climber(Lemming lemming) {
		super(lemming);
	}
	
	/**
	 * A Cliber can collide or fall or walk
	 * If he cannot climb again and he stuck while climbing he became a parachutist 
	 */
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
	
	/**
	 * The method fall is override because a climber cannot fall when there is a solid block in front of him
	 * Therefore the fall method is slightly different
	 */
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
	
	/**
	 * The method walk is override because the climber can walk on the wall
	 * Therefore the behavior of this method is very different
	 */
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

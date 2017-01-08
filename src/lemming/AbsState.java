package lemming;

import java.util.List;

import component.Component;
import view.Type;

/**
 * Abstract class for all the states 
 * 
 * @author Adrien
 *
 */
public abstract class AbsState {
	
	/**
	 * The lemming it belong to
	 */
	protected Lemming lemming;
	
	/**
	 * 
	 * @param lemming   The lemming it belong to
	 */
	public AbsState(Lemming lemming) {
		this.lemming = lemming;
	}
	
	/**
	 * Check if the lemming collide with an other component and execute the collision
	 * 
	 * @return Return true if a collision occured
	 */
	public boolean collision(){
		List<Component> here = lemming.getGameMap().getArea(lemming.getCoordinate());
		boolean hasCollide = false;
		/*If there is at least one collision, hasCollide is set to true*/
		for(Component hcomponent : here){
			hasCollide |= hcomponent.collision(lemming);
		}
		return hasCollide;
	}
	
	/**
	 * Check if the lemming can fall and if yes the lemming fall
	 * 
	 * @return Return true if the lemming has fell
	 */
	public boolean fall(){
		List<Component> down = lemming.checkSide(Direction.DOWN());
		boolean canFall = true; /*Indicate if the lemming can fall*/
		boolean dieIfFalling = false; /*Indicate if the lemming die if he fall*/
		for(Component component : down){
			if(!component.isVoid() && !component.isKilling()){
				canFall = false;
			}
			if(component.isKilling()) dieIfFalling = true;
			if(!component.canBeSkipped()) {
				canFall = true;
				continue;
			}
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
	
	/**
	 * Check if the lemming can go in diagonal and if yes do
	 * 
	 * @return Return true if the lemming has moved in diagonal
	 */
	public boolean walkDiag(){
		List<Component> foward = lemming.checkSide(lemming.getDesiredDirection());
		List<Component> diagonal = lemming.checkSide(lemming.getDesiredDirection().checkAdd(Direction.UP()));
		List<Component> top = lemming.checkSide(Direction.UP());
		boolean diagIsVoid = true; /*Indicate if the lemming can go in diagonal*/
		boolean fowardIsNotVoid = false; /*Indicate if the lemming can go foward*/
		boolean topIsVoid = true; /* Indicate if there is an obstacle above the lemming*/
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
	
	/**
	 * Check if the lemming can go in the direction he want
	 * If yes he move to this direction
	 * If no he move where he can or he die depending on the blocks around him
	 * 
	 * @return
	 */
	public boolean walk(){
		if(walkDiag()) return true;
		List<Component> foward = lemming.checkSide(lemming.getDesiredDirection());
		for(Component fcomponent : foward){
			if(fcomponent.isKilling()){
				fcomponent.killLemming(lemming);
				return true;
			} else if(fcomponent.isInverting()){
				lemming.invertDirection();
				List<Component> fowardInvert = lemming.checkSide(lemming.getDesiredDirection());
				boolean canGoInvert = true;
				for(Component fiComponent : fowardInvert){
					if(!fiComponent.isVoid()) canGoInvert = false;
				}
				if(canGoInvert){					
					move(lemming.getDesiredDirection());
					return true;
				} else {
					return false;
				}
			}
		}
		move(lemming.getDesiredDirection());
		return false;
	}
	
	/**
	 * Move the lemming to the direction specified in parameter
	 * If there is nothing in front of him he is destroy because that mean he his out of the map
	 */
	public void move(Direction realDirection) {
		lemming.setRealDirection(realDirection);
		List<Component> foward = lemming.checkSide(lemming.getRealDirection());
		if(foward.isEmpty()) {
			lemming.destroy();
		}
		lemming.getGameMap().move(lemming.getCoordinate().checkDirection(lemming.getRealDirection()), lemming);
	}
	
	/**
	 * The code to execute at each step of the game
	 */
	public abstract void step();
	
	/**
	 * Each implementation of AbsState must coincide to a Type
	 * @return The corresponding type
	 */
	public abstract Type getTypeByState();
	
	/**
	 * Each implementation of AbsState must have a display priority
	 * @return The display priority of this state
	 */
	public abstract Priority getPriority();
	
	/**
	 * Return false because by default a lemming does not invert the direction of other lemming
	 */
	public boolean isInverting(){
		return false;
	}
	
}

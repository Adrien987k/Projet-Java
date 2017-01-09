package lemming;

import java.util.List;

import view.Type;
import States.State;
import component.Component;
import component.Coordinate;
import factory.Factory;
import game.GameMap;

/**
 * This class represent a lemming in memory
 * 
 * @author Adrien
 *
 */
public class Lemming extends Component {
	
	/**
	 * The number of time a lemming can fall and survive his fall
	 */
	public static final int DEFAULT_FALLING = 5;
	
	/**
	 * The current state of the lemming
	 */
	private AbsState state;
	
	/**
	 * The direction the lemming want to go
	 */
	private Direction desiredDirection;
	
	/**
	 * The direction the lemming will actually go
	 */
	private Direction realDirection;
	
	/**
	 * The remaining number of time a lemming can fall without dying when he will encounter the ground
	 */
	private int falling = DEFAULT_FALLING;
	
	/**
	 * Indicate if the lemming has taken out by a door
	 */
	private boolean free = false;
	
	/**
	 * Indicate if the lemming has juste invert his position at the last step
	 */
	private boolean hasJustInvert = false;
	
	/**
	 * 
	 * @param coordinate   Coordinate on the map
	 * @param gameMap      The map it belong to
	 */
	public Lemming(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, Priority.PRIORITY_LEMMING_WEAK, Type.WALKER, gameMap);
		state = Factory.makeState(State.WALKER, this);
		desiredDirection = Direction.RIGHT();
	}
	
	/*GETTERS AND SETTERS*/
	
	public AbsState getState(){
		return state;
	}
	
	public Direction getDesiredDirection() {
		return desiredDirection;
	}
	
	public void setDesiredDirection(Direction desiredDirection) {
		this.desiredDirection = desiredDirection;
	}
	
	public Direction getRealDirection() {
		return realDirection;
	}
	
	public void setRealDirection(Direction realDirection) {
		this.realDirection = realDirection;
	}
	
	public int getFalling(){
		return falling;
	}
	
	public void decFalling(){
		falling--;
	}
	
	public void incFalling(){
		if(falling < DEFAULT_FALLING) falling++;
	}
	
	public void resetFalling(){
		falling = DEFAULT_FALLING;
	}
	
	public boolean isFree(){
		return free;
	}
	
	public void setFree(){
		free = true;
	}
	
	public boolean getHasJustInvert(){
		return hasJustInvert;
	}
	
	/*END GETTERS AND SETTERS*/
	
	/**
	 * Change the state of the lemming by the specified state
	 * Inform the graphical interface of the change in order to change instantly the texture of the lemming
	 *
	 * @return Return true because the change always occur
	 */
	 @Override
	 public boolean changeStateIf(State state) {
		 changeState(state);
		 getGameMap().getGame().getView().getGamePanel().draw(state.getType(), getCoordinate());
		 return true;
	 }
	
	/**
	 * Change the state of the lemming by the specified state
	 */
	public void changeState(State state) {
		this.state = Factory.makeState(state, this);
		setType(this.state.getTypeByState());
		setPriority(this.state.getPriority());
	}
	
	/**
	 * Invert the direction the lemming want to go
	 */
	public void invertDirection(){
		getDesiredDirection().invert();
		hasJustInvert = true;
	}
	
	/**
	 * If the lemming is out of the map destroy it
	 * Then call the method step of the current state
	 */
	@Override
	public void step() {
		hasJustInvert = false;
		if(getGameMap().isOut(getCoordinate())){
			destroy();
		}
		state.step();
	}
	
	/**
	 * Return the list of the component which are in the specified direction starting from the coordinate of the lemming
	 */
	public List<Component> checkSide(Direction direction){
		return getGameMap().getArea(getCoordinate().checkDirection(direction));
	}
	
	/**
	 * Return the list of the component which are in at the same coordinate of the lemming 
	 */
	public List<Component> checkSide(){
		return getGameMap().getArea(getCoordinate());
	}
	
	/**
	 * Return false because there is no collision between two lemming
	 */
	@Override
	public boolean collision(Lemming lemming){
		return false;
	}
	
	/**
	 * Return true because a lemming can be cross
	 */
	@Override
	public boolean isVoid(){
		return true;
	}
	
	/**
	 * Return true if the current state of the lemming is inverting
	 */
	@Override
	public boolean isInverting(){
		return state.isInverting();
	}
	
	/**
	 * Do nothong because a lemming cannot kill an other lemming
	 */
	@Override
	public void killLemming(Lemming lemming){
		
	}
	
	/**
	 * Return true because a lemming can be destroy
	 */
	@Override
	public boolean isDestructible(){
		return true;
	}
	
	/**
	 * Return false because a lemming cannot be mined
	 */
	@Override
	public boolean canBeMined(){
		return false;
	}
	
	/**
	 * Return true because a lemming can be skipped
	 */
	@Override
	public boolean canBeSkipped(){
		return true;
	}
	
	/**
	 * Remonve the lemming of the game map
	 */
	@Override
	public void destroy(){
		getGameMap().remove(this);
		if(free){
			getGameMap().incNbFreeLemming();
		} else {
			getGameMap().incNbDeadLemming();
		}
	}
	
	/**
	 * For the lemmings kill have the same effect as destroy 
	 */
	@Override
	public void kill() {
		destroy();
	}
	
}

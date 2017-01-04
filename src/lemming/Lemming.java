package lemming;

import java.util.List;

import view.Type;
import States.State;
import component.Component;
import component.Coordinate;
import factory.Factory;
import game.GameMap;

public class Lemming extends Component {
	
	public static final int DEFAULT_FALLING = 5;
	
	private AbsState state;
	private Direction realDirection;
	private Direction desiredDirection;
	private int falling = DEFAULT_FALLING;
	private boolean free = false;
	private boolean hasJustInvert = false;
	
	public Lemming(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, Priority.PRIORITY_LEMMING_WEAK, Type.WALKER, gameMap);
		state = Factory.makeState(State.WALKER, this);
		desiredDirection = Direction.RIGHT();
	}
	
	public Direction getRealDirection() {
		return realDirection;
	}
	
	public void setRealDirection(Direction realDirection) {
		this.realDirection = realDirection;
	}
	
	public Direction getDesiredDirection() {
		return desiredDirection;
	}
	
	public void setDesiredDirection(Direction desiredDirection) {
		this.desiredDirection = desiredDirection;
	}
	
	public void decFalling(){
		falling--;
	}
	
	public int getFalling(){
		return falling;
	}
	
	public void resetFalling(){
		falling = DEFAULT_FALLING;
	}
	
	 @Override
	 public boolean changeStateIf(State state) {
		 System.out.println("changeStateIf");
		 changeState(state);
		 return true;
	 }
	
	public void changeState(State state) {
		this.state = Factory.makeState(state, this);
		setType(this.state.getTypeByState());
		setPriority(this.state.getPriority());
	}
	
	public AbsState getState(){
		return state;
	}
	
	public boolean isFree(){
		return free;
	}
	
	public void setFree(){
		free = true;
	}
	
	public void invertDirection(){
		getDesiredDirection().invert();
		hasJustInvert = true;
	}
	
	public boolean getHasJustInvert(){
		return hasJustInvert;
	}
	
	@Override
	public void step() {
		hasJustInvert = false;
		if(getGameMap().isOut(getCoordinate())){
			destroy();
		}
		state.step();
	}
	
	public List<Component> checkSide(Direction direction){
		return getGameMap().getArea(getCoordinate().checkDirection(direction));
	}
	
	public List<Component> checkSide(){
		return getGameMap().getArea(getCoordinate());
	}
	
	@Override
	public boolean collision(Lemming lemming){
		return false;
	}
	
	@Override
	public boolean isVoid(){
		return true;
	}
	
	@Override
	public boolean isInverting(){
		return state.isInverting();
	}
	
	@Override
	public void killLemming(Lemming lemming){
		
	}
	
	@Override
	public boolean isDestructible(){
		return true;
	}
	
	@Override
	public boolean canBeMined(){
		return false;
	}
	
	@Override
	public boolean canBeSkipped(){
		return true;
	}
	
	@Override
	public void destroy(){
		getGameMap().remove(this);
		if(free){
			getGameMap().incNbFreeLemming();
		} else {
			getGameMap().incNbDeadLemming();
		}
	}
	
	@Override
	public void kill() {
		destroy();
	}
}

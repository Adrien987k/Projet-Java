package lemming;

import java.util.List;

import view.Type;
import States.State;
import component.Component;
import component.Coordinate;
import factory.Factory;
import game.GameMap;

public class Lemming extends Component implements IControlPanel {
	
	public static final int DEFAULT_FALLING = 5;
	
	private AbsState state;
	private Direction realDirection;
	private Direction desiredDirection;
	private int falling = DEFAULT_FALLING ;
	private boolean free = false;
	
	/*public Lemming() {
		super();
		state = Factory.makeState(State.WALKER, this);
	}
	public Lemming(Coordinate coordinate) {
		super(coordinate, PRIORITY_LEMMING_WEAK, Type.WALKER, null);
		state = Factory.makeState(State.WALKER, this);
	}*/
	
	public Lemming(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, PRIORITY_LEMMING_WEAK, Type.WALKER, gameMap);
		state = Factory.makeState(State.WALKER, this);
		desiredDirection = Direction.RIGHT;
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
	public void changeState(State state) {
		this.state = Factory.makeState(state, this);
		setType(this.state.getTypeByState());
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
	
	public void step() {
		state.step();
	}
	
	public List<Component> checkSide(Direction direction){
		return getGameMap().getArea(getCoordinate().checkDirection(direction));
	}
	
	public List<Component> checkSide(){
		return getGameMap().getArea(getCoordinate());
	}
	
	public void collision(Lemming lemming){
		
	}
	
	public boolean isVoid(){
		return true;
	}
	
	public boolean isInverting(){
		return state.isInverting();
	}
	
	public void killLemming(Lemming lemming){
		
	}
	
	public boolean isDestructible(){
		return true;
	}
	
	public boolean canBeMined(){
		return false;
	}
	
	public boolean canBeSkipped(){
		return true;
	}
	
	public void destroy(){
		getGameMap().remove(this);
		if(free){
			getGameMap().incNbFreeLemming();
		} else {
			getGameMap().incNbDeadLemming();
		}
	}
}

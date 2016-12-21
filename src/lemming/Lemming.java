package lemming;

import java.util.List;

import view.Type;
import States.State;
import component.Component;
import component.Coordinate;
import factory.Factory;
import game.GameMap;

public class Lemming extends Component implements IControlPanel, IActionPanel{
	
	private AbsState state;
	private Direction direction;
	private int falling = 5;
	
	public Lemming() {
		super();
		state = Factory.makeState(State.WALKER, this);
	}
	public Lemming(Coordinate coordinate) {
		super(coordinate, PRIORITY_LEMMING_WEAK, Type.WALKER, null);
		state = Factory.makeState(State.WALKER, this);
	}
	public Lemming(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, PRIORITY_LEMMING_WEAK, Type.WALKER, gameMap);
		state = Factory.makeState(State.WALKER, this);
	}
	
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	@Override
	public int kill(int how) {
		return 0;
	}
	@Override
	public void changeState(State state) {
		this.state = Factory.makeState(state, this);
		setType(this.state.getTypeByState());
	}
	
	public AbsState getState(){
		return state;
	}
	
	public void step() {
		state.step(this);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void construct() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	public List<Component> checkSide(Direction direction){
		return getGameMap().getArea(getCoordinate().checkDirection(direction));
	}
	
	public List<Component> checkSide(){
		return getGameMap().getArea(getCoordinate());
	}
	
	public boolean isVoid(){
		return false;
	}
}

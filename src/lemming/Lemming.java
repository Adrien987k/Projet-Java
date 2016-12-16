package lemming;

import States.State;
import component.Component;
import component.Coordinate;
import factory.Factory;
import view.Type;

public class Lemming extends Component  implements IControlPanel,IActionPanel{
	private AbsState state;
	private Direction direction;
	private int falling = 5;
	
	public Lemming() {
		super();
		state = Factory.makeState(State.WALKER);
	}
	public Lemming(Coordinate coordinate) {
		super(coordinate, PRIORITY_LEMMING_WEAK,Type.WALKER);
		state = Factory.makeState(State.WALKER);
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
		this.state = Factory.makeState(state);
		setType(this.state.getTypeByState());
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
}

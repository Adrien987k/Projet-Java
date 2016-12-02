package factory;

import lemming.AbsState;
import lemming.Lemming;
import States.Blocker;
import States.Carpenter;
import States.Climber;
import States.Digger;
import States.Parachutist;
import States.State;
import States.Tunneler;
import States.Walker;

public class Factory implements IFactory {
	
	public Factory() {
		// TODO Auto-generated constructor stub
	}
	
	public Lemming makeLemming() {
		return new Lemming();
	}
	
	public static AbsState makeState(State state) {
		switch(state) {
		case WALKER: return new Walker();
		case BLOCKER: return new Blocker();
		case CARPENTER: return new Carpenter();
		case CLIMBER: return new Climber();
		case DIGGER: return new Digger();
		case PARACHUTIST: return new Parachutist();
		case TUNNELER: return new Tunneler();
		default: throw new IllegalArgumentException();
		}
	}

}

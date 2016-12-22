package States;

import lemming.AbsState;
import lemming.Lemming;
import view.Type;

public class Digger extends AbsState{
	
	public Digger(Lemming lemming) {
		super(lemming);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		
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
	
	@Override
	public Type getTypeByState() {
		return Type.DIGGER;
	}
}

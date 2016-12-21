package States;

import lemming.AbsState;
import lemming.Lemming;
import view.Type;

public class Climber extends AbsState	{
	
	public Climber(Lemming lemming) {
		super(lemming);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void step(Lemming lemming) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(Lemming lemming) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void construct(Lemming lemming) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy(Lemming lemming) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Type getTypeByState() {
		return Type.CLIMBER;
	}
}

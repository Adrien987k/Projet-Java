package States;

import block.InvertSide;
import block.KillingSide;
import block.SpecialSide;
import lemming.AbsState;
import lemming.Lemming;
import view.Type;

public class Walker extends AbsState {
	
	@Override
	public void step(Lemming lemming) {
		
	}
	
	public void collision(InvertSide invertSide) {
		
	}
	
	public void collision(KillingSide killingSide) {
		
	}
	

	@Override
	public void move(Lemming lemming) {
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
		return Type.WALKER;
	}

}

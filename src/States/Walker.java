package States;

import java.util.List;

import lemming.AbsState;
import lemming.Direction;
import lemming.Lemming;
import view.Type;
import component.Component;

public class Walker extends AbsState {
	
	//Utilité ???
	public Walker(Lemming lemming) {
		super(lemming);
	}

	@Override
	public void step(Lemming lemming) {
		
		List<Component> down = lemming.checkSide(Direction.DOWN);
		for(Component component : down){
			if(component.isVoid()){
				//TODO Le lemming tombe d'une case
			}
		}
		
		List<Component> foward = lemming.checkSide(lemming.getDirection());
		for(Component component : foward){
			if(component.isKilling()){
				//TODO Le lemming meure
			} else {
				if(component.isInverting()){
					//TODO Le lemming change de direction
				}
			}
		}
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

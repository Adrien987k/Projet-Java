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
		List<Component> diagonal = lemming.checkSide(lemming.getDirection().checkAdd(Direction.UP));
		boolean diagIsVoid = true;
		boolean fowardIsNotVoid = false;
		for(Component dcomponent : diagonal){
			if(!dcomponent.isVoid()) diagIsVoid = false;
		}
		for(Component fcomponent : foward){
			if(!fcomponent.isVoid()) fowardIsNotVoid = true;
		}
		if(diagIsVoid && fowardIsNotVoid){
			//TODO Le lemming monte d'une case
			return;
		}
		for(Component fcomponent : foward){
			if(fcomponent.isKilling()){
				//TODO Le lemming meure
				return;
			} else if(fcomponent.isInverting()){
				lemming.getDirection().invert();
				return;
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

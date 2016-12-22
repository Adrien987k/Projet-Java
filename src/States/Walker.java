package States;

import java.util.List;

import lemming.AbsState;
import lemming.Direction;
import lemming.Lemming;
import view.ChangeMemory;
import view.Type;
import component.Component;

public class Walker extends AbsState {
	
	public Walker(Lemming lemming) {
		super(lemming);
	}

	@Override
	public void step() {
		
		List<Component> down = lemming.checkSide(Direction.DOWN);
		for(Component component : down){
			if(component.isVoid()){
				lemming.setRealDirection(Direction.DOWN);
				lemming.decFalling();
				move();
				return;
			}
		}
		if(lemming.getFalling() <= 0){
			lemming.destroy();
			return;
		}
		lemming.resetFalling();
		List<Component> foward = lemming.checkSide(lemming.getDesiredDirection());
		List<Component> diagonal = lemming.checkSide(lemming.getDesiredDirection().checkAdd(Direction.UP));
		boolean diagIsVoid = true;
		boolean fowardIsNotVoid = false;
		for(Component dcomponent : diagonal){
			if(!dcomponent.isVoid()) diagIsVoid = false;
		}
		for(Component fcomponent : foward){
			if(!fcomponent.isVoid()) fowardIsNotVoid = true;
		}
		if(diagIsVoid && fowardIsNotVoid){
			lemming.setRealDirection(lemming.getDesiredDirection().checkAdd(Direction.UP));
			move();
			return;
		}
		for(Component fcomponent : foward){
			if(fcomponent.isKilling()){
				fcomponent.killLemming(lemming);
				return;
			} else if(fcomponent.isInverting()){
				lemming.getDesiredDirection().invert();
				lemming.setRealDirection(lemming.getDesiredDirection());
				move();
				return;
			}
		}
		
	}

	@Override
	public void move() {
		lemming.getGameMap().addChangeMemory(
										new ChangeMemory(
												lemming.getCoordinate().checkDirection(lemming.getRealDirection()), 
												lemming
										)
											);
	}

	@Override
	public void construct() {
		
	}

	@Override
	public void destroy() {
		
	}
	
	@Override
	public Type getTypeByState() {
		return Type.WALKER;
	}

}

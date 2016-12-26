package States;

import game.GameMap;

import java.util.List;

import component.Component;
import lemming.AbsState;
import lemming.Direction;
import lemming.Lemming;
import view.Type;

public class Tunneler extends AbsState {
	
	public Tunneler(Lemming lemming) {
		super(lemming);
	}

	@Override
	public void step() {
		boolean hasMoved;
		boolean hasDug;
		hasMoved = fall();
		if(!hasMoved){
			hasDug = dig();
			if(hasDug){				
				walk();
			} else {
				lemming.changeState(State.WALKER);
			}
		}
	}
	
	public boolean dig(){
		List<Component> foward = lemming.checkSide(lemming.getDesiredDirection());
		Component componentToMine = null;
		for(Component component : foward){
			if(component.canBeMined()){
				componentToMine = component;
			}
		}
		if(componentToMine != null){
			GameMap gameMap = lemming.getGameMap();
			gameMap.change(componentToMine,
							gameMap.getFactory().make(Type.VOID, componentToMine.getCoordinate(), gameMap)
						   );
			return true;
		}
		return false;
	}
	
	@Override
	public Type getTypeByState() {
		return Type.TUNNELER;
	}
}	

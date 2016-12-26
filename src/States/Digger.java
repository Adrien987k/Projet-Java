package States;

import factory.Factory;
import game.GameMap;

import java.util.List;

import lemming.AbsState;
import lemming.Direction;
import lemming.Lemming;
import view.Type;
import component.Component;

public class Digger extends AbsState{
	
	public static final int NB_BLOCK_TO_DIG = 5;
	
	private int nbBlockToDig = NB_BLOCK_TO_DIG;
	
	public Digger(Lemming lemming) {
		super(lemming);
	}

	@Override
	public void step() {
		boolean hasMoved;
		dig();
		hasMoved = fall();
		if(!hasMoved) walk();
		if(nbBlockToDig == 0){
			lemming.changeState(State.WALKER);
			return;
		}
	}
	
	public boolean dig(){
		List<Component> down = lemming.checkSide(Direction.DOWN);
		Component componentToMine = null;
		for(Component component : down){
			if(component.canBeMined()){
				componentToMine = component;
			}
		}
		if(componentToMine != null){
			nbBlockToDig--;
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
		return Type.DIGGER;
	}
}

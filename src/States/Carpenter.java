package States;

import game.GameMap;

import java.util.List;

import lemming.AbsState;
import lemming.Lemming;
import lemming.Priority;
import view.Type;
import component.Component;
import component.Coordinate;

public class Carpenter extends AbsState {
	
	public static final int NB_BLOCK_TO_BUILD = 5;
	
	private int nbBlockToBuild = NB_BLOCK_TO_BUILD;
	
	public Carpenter(Lemming lemming) {
		super(lemming);
	}

	@Override
	public void step() {
		boolean hasMoved = collision();
		if(!hasMoved) hasMoved |= fall();
		if(!hasMoved) hasMoved |= build();
		if(!hasMoved) hasMoved |= walk();
		if(lemming.getHasJustInvert()) nbBlockToBuild = 0;
		if(nbBlockToBuild == 0){
			lemming.changeState(State.WALKER);
			return;
		}
	}
	
	public boolean build(){
		List<Component> foward = lemming.checkSide(lemming.getDesiredDirection());
		boolean canBuild = true;
		for(Component fcomponent : foward){
			if(!fcomponent.isVoid()) canBuild = false;
		}
		if(canBuild){
			nbBlockToBuild--;
			GameMap gameMap = lemming.getGameMap();
			Coordinate clemm = lemming.getCoordinate();
			Coordinate cfoward = clemm.checkDirection(lemming.getDesiredDirection());
			gameMap.add(cfoward, gameMap.getFactory().make(Type.SIMPLE_DESTRUCTIBLE, cfoward, gameMap));
			return true;
		}
		return false;
	}
	
	@Override
	public Type getTypeByState() {
		return Type.CARPENTER;
	}
	
	@Override
	public Priority getPriority(){
		return Priority.PRIORITY_LEMMING_HIGH;
	}
	
}

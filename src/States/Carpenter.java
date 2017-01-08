package States;

import game.GameMap;

import java.util.List;

import lemming.AbsState;
import lemming.Lemming;
import lemming.Priority;
import view.Type;
import component.Component;
import component.Coordinate;

/**
 * A carpenter can build a bridge
 * 
 * @author Adrien
 *
 */
public class Carpenter extends AbsState {
	
	/**
	 * The initial number of block a carpenter can build
	 */
	public static final int NB_BLOCK_TO_BUILD = 5;
	
	/**
	 * The remaining number a carpenter can build
	 */
	private int nbBlockToBuild = NB_BLOCK_TO_BUILD;
	
	/**
	 * 
	 * @param lemming   The lemming it beling to
	 */
	public Carpenter(Lemming lemming) {
		super(lemming);
	}
	
	/**
	 * A carpenter can collide or fall or build a block or walk
	 * If he cannot build he became he walker
	 */
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
	
	/**
	 * Check if the carpenter can build
	 * if yes he build a simple destructible block in front of him
	 * 
	 * @return Return true if the carpenter has built a block
	 */
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

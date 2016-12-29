package States;

import game.GameMap;

import java.util.List;

import lemming.AbsState;
import lemming.Lemming;
import view.Type;

import component.Component;
import component.Coordinate;

public class Bomber extends AbsState {
	
	public static final int NB_STEPS_BEFORE_BOOM = 3;
	
	private int nbStepsBeforeBoom = NB_STEPS_BEFORE_BOOM;

	public Bomber(Lemming lemming) {
		super(lemming);
	}

	@Override
	public void step() {
		nbStepsBeforeBoom--;
		if(nbStepsBeforeBoom == 0){
			boom();
			return;
		}
		boolean hasMoved;
		hasMoved = collision();
		if(!hasMoved) hasMoved |= fall();
		if(!hasMoved) walk();
	}
	
	public void boom(){
		List<Component> area;
		int posX = lemming.getCoordinate().getX();
		int posY = lemming.getCoordinate().getY();
		GameMap gameMap = lemming.getGameMap();
		for(int i = -2; i < 3; i++){
			for(int j = -2; j < 3; j++){
				area = gameMap.getArea(new Coordinate(posX + i, posY + j));
				for(Component component : area){
					if(component.isDestructible()) component.destroy();
				}
			}
		}
	}

	@Override
	public Type getTypeByState() {
		return Type.BOMBER;
	}
}

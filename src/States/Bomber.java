package States;

import game.GameMap;

import java.util.List;

import lemming.AbsState;
import lemming.Lemming;
import lemming.Priority;
import view.Type;

import component.Component;
import component.Coordinate;

public class Bomber extends AbsState {
	
	public static final int NB_STEPS_BEFORE_BOOM = 3;
	public static final int BOMBER_EXPLOSION_RANGE = 2;
	
	private int nbStepsBeforeBoom = NB_STEPS_BEFORE_BOOM;
	private boolean hasBeenDestroy = false;

	public Bomber(Lemming lemming) {
		super(lemming);
	}

	@Override
	public void step() {
		nbStepsBeforeBoom--;
		if(nbStepsBeforeBoom == 0 && !hasBeenDestroy){
			hasBeenDestroy = true;
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
		for(int i = -BOMBER_EXPLOSION_RANGE; i <= BOMBER_EXPLOSION_RANGE; i++){
			for(int j = -BOMBER_EXPLOSION_RANGE; j <= BOMBER_EXPLOSION_RANGE; j++){
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
	
	@Override
	public Priority getPriority(){
		return Priority.PRIORITY_LEMMING_HIGH;
	}
	
}

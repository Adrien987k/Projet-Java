package block;

import game.GameMap;

import java.util.List;

import lemming.Lemming;
import view.Type;

import component.Component;
import component.Coordinate;

public class Bomb extends AbsDestructible {

	public Bomb(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, PRIORITY_BLOCK, Type.BOMB, gameMap);
	}

	@Override
	public void collision(Lemming lemming) {
		
	}
	
	public void boom(){
		List<Component> area;
		for(int i = -2; i < 3; i++){
			for(int j = -2; j < 3; j++){
				area = getGameMap().getArea(new Coordinate(getCoordinate().getX() + i,
														   getCoordinate().getY() + j));
				for(Component component : area){
					if(component.isDestructible()) component.destroy();
				}
			}
		}
	}
	
}

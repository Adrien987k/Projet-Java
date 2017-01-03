package block;

import java.util.List;

import component.Component;
import component.Coordinate;
import game.GameMap;
import lemming.Priority;
import view.Type;

public class Bomb extends AbsDestructible {
	
	private boolean hasBeenDestroy = false;

	public Bomb(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, Priority.PRIORITY_BLOCK, Type.BOMB, gameMap);
	}
	
	@Override
	public void destroy(){
		super.destroy();
		if(!hasBeenDestroy) {
			hasBeenDestroy = true;
			boom();
		}
	}
	
	public void boom(){
		List<Component> area;
		int posX = getCoordinate().getX();
		int posY = getCoordinate().getY();
		GameMap gameMap = getGameMap();
		for(int i = -2; i < 3; i++){
			for(int j = -2; j < 3; j++){
				area = gameMap.getArea(new Coordinate(posX + i, posY + j));
				for(Component component : area){
					if(component.isDestructible()) component.destroy();
				}
			}
		}
	}
	
}

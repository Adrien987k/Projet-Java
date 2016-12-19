package block;

import lemming.Lemming;
import game.GameMap;
import view.Type;
import component.Coordinate;

public class Lava extends AbsIndestructible {

	public Lava(Coordinate coordinate, GameMap gameMap) {
		super(coordinate,PRIORITY_BLOCK,Type.LAVA, gameMap);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void collision(Lemming lemming) {
		// TODO Auto-generated method stub
		
	}

}

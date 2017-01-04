package block;

import component.Coordinate;
import game.GameMap;
import lemming.Priority;
import view.Type;

public class Again extends AbsDestructible {

	public static final int NB_GENERATION = 3;
	
	private int nbBlockToGenerate = NB_GENERATION;
	
	public Again(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, Priority.PRIORITY_BLOCK, Type.AGAIN, gameMap);
		
	}

	@Override
	public void destroy(){
		super.destroy();
		if(nbBlockToGenerate > 0){
			getGameMap().add(getCoordinate(), this);
			nbBlockToGenerate--;
		}
	}

}

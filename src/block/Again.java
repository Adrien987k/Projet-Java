package block;

import game.GameMap;
import view.Type;
import component.Coordinate;

public class Again extends AbsDestructible {

	public static final int NB_GENERATION = 3;
	
	private int nbBlockToGenerate = NB_GENERATION;
	
	public Again(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, PRIORITY_BLOCK, Type.AGAIN, gameMap);
		
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

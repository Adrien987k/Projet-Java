package block;

import game.GameMap;
import lemming.Lemming;
import view.Type;
import component.Component;
import component.Coordinate;

public abstract class AbsBlock extends Component {
	
	public AbsBlock(Coordinate coordinate, int priority, Type type, GameMap gameMap) {
		super(coordinate, priority, type, gameMap);
	}
	
	public void step() {
		
	}
	
	public void destroy(){
		GameMap gameMap = getGameMap();
		gameMap.change(this, gameMap.getFactory().make(Type.VOID, getCoordinate(), gameMap));
	}
	
	public void collision(Lemming lemming){
		
	}
	
	public boolean isVoid(){
		return false;
	}
	
	public void killLemming(Lemming lemming){
		
	}
	
	public boolean isDestructible(){
		return false;
	}
	
	public boolean canBeMined(){
		return false;
	}
	
	public boolean canBeSkipped(){
		return true;
	}
	
}

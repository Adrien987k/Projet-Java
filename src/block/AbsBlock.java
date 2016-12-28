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
	
	@Override
	public void step() {
		
	}
	
	@Override
	public void destroy(){
		GameMap gameMap = getGameMap();
		gameMap.change(this, gameMap.getFactory().make(Type.VOID, getCoordinate(), gameMap));
	}
	
	@Override
	public boolean collision(Lemming lemming){
		return false;
	}
	
	@Override
	public boolean isVoid(){
		return false;
	}
	
	@Override
	public void killLemming(Lemming lemming){
		
	}
	
	@Override
	public boolean isDestructible(){
		return false;
	}
	
	@Override
	public boolean canBeMined(){
		return false;
	}
	
	@Override
	public boolean canBeSkipped(){
		return true;
	}
	
}

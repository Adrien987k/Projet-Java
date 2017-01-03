package block;

import java.util.HashMap;
import java.util.Map;

import component.Coordinate;
import game.GameMap;
import lemming.Lemming;
import lemming.Priority;
import view.Type;

public class TP extends AbsIndestructible {
	
	private TP destination = null;
	private Map<Lemming, Boolean> hasAlreadyTeleportMap = new HashMap<>();

	public TP(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, Priority.PRIORITY_BLOCK, Type.TP, gameMap);
	}
	
	public void setDestination(TP destination){
		this.destination = destination;
	}

	@Override
	public boolean collision(Lemming lemming) {
		if(!hasAlreadyTeleportMap.containsKey(lemming)) hasAlreadyTeleportMap.put(lemming, false);
		if(destination != null && !hasAlreadyTeleportMap.get(lemming)){
			getGameMap().move(destination.getCoordinate(), lemming);
			hasAlreadyTeleportMap.replace(lemming, true);
			if(!destination.hasAlreadyTeleportMap.containsKey(lemming)) destination.hasAlreadyTeleportMap.put(lemming, false);
			destination.hasAlreadyTeleportMap.replace(lemming, true);
			return true;
		}
		hasAlreadyTeleportMap.replace(lemming, false);
		return false;
	}
	
	@Override
	public boolean canBeSkipped(){
		return false;
	}
	
	@Override
	public boolean isInverting(){
		return false;
	}

}

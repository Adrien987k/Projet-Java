package block;

import java.util.HashMap;
import java.util.Map;

import component.Coordinate;
import game.GameMap;
import lemming.Lemming;
import lemming.Priority;
import view.Type;

/**
 * This block can teleport a lemming from it to an other TP block
 * 
 * @author Adrien
 *
 */
public class TP extends AbsIndestructible {
	
	/**
	 * The TP where the lemming will be teleport
	 */
	private TP destination = null;
	
	/**
	 * This map is use to prevent lemming from teleport from a portal to an other in an infinit loop
	 * Indeed if the lemming has been teleport he have to continue moving rather than teleport again
	 */
	private Map<Lemming, Boolean> hasAlreadyTeleportMap = new HashMap<>();
	
	/**
	 * 
	 * @param coordinate   Coordinate on the map
	 * @param gameMap      The map it belong to 
	 */
	public TP(Coordinate coordinate, GameMap gameMap) {
		super(coordinate, Priority.PRIORITY_BLOCK, Type.TP, gameMap);
	}
	
	/**
	 * @param destination The TP where the lemming will be teleport
	 */
	public void setDestination(TP destination){
		this.destination = destination;
	}
	
	/**
	 * If a lemming collide with a TP he is teleport to the destination
	 */
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
	
	/**
	 * Return false because a lemming have to collide it
	 */
	@Override
	public boolean canBeSkipped(){
		return false;
	}
	
	/**
	 * Return false because a lemming have to collide it
	 */
	@Override
	public boolean isInverting(){
		return false;
	}

}

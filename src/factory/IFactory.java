package factory;

import game.GameMap;
import view.Type;

import component.Component;
import component.Coordinate;

public interface IFactory {
	
	public Component make(Type t, Coordinate coord, GameMap gameMap);
	
}

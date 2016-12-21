package factory;

import game.GameMap;
import lemming.AbsState;
import lemming.Lemming;
import view.Type;
import States.Blocker;
import States.Carpenter;
import States.Climber;
import States.Digger;
import States.Parachutist;
import States.State;
import States.Tunneler;
import States.Walker;
import block.AbsBlock;
import block.Again;
import block.Bomb;
import block.End;
import block.Lava;
import block.SimpleD;
import block.SimpleI;
import block.Start;
import block.TP;
import block.Void;
import component.Component;
import component.Coordinate;

public class Factory implements IFactory {
	
	public Factory() {
		
	}
	
	public Component make(Type t, Coordinate coord, GameMap gameMap) {
		switch(t){
			case LEMMING: return makeLemming(coord, gameMap);
			default: return makeBlock(t, coord, gameMap);
		}
	}
	
	private Lemming makeLemming(Coordinate coord, GameMap gameMap) {
		return new Lemming(coord, gameMap);
	}
	
	private AbsBlock makeBlock(Type t, Coordinate coord, GameMap gameMap) {
		switch(t){
			case SIMPLE_DESTRUCTIBLE: return new SimpleD(coord, gameMap);
			case BOMB: return new Bomb(coord, gameMap);
			case AGAIN: return new Again(coord, gameMap);
			case SIMPLE_INDESTRUCTIBLE: return new SimpleI(coord, gameMap);
			case LAVA: return new Lava(coord, gameMap);
			case TP: return new TP(coord, gameMap);
			case START: return new Start(coord, gameMap);
			case END: return new End(coord, gameMap);
			case VOID: return new Void(coord, gameMap);
			default: throw new IllegalArgumentException();
		}
	}
	
	public static AbsState makeState(State state, Lemming lemming) {
		switch(state) {
			case WALKER: return new Walker(lemming);
			case BLOCKER: return new Blocker(lemming);
			case CARPENTER: return new Carpenter(lemming);
			case CLIMBER: return new Climber(lemming);
			case DIGGER: return new Digger(lemming);
			case PARACHUTIST: return new Parachutist(lemming);
			case TUNNELER: return new Tunneler(lemming);
			default: throw new IllegalArgumentException();
		}
	}

}


/*
	class Again {
		List<Block>;
		block current;
		
		Again(Block ... blocks)
}
*/
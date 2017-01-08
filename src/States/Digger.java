package States;

import java.util.List;

import lemming.AbsState;
import lemming.Direction;
import lemming.Lemming;
import lemming.Priority;
import view.Type;
import component.Component;

/**
 * A digger can dig under him
 * 
 * @author Adrien
 *
 */
public class Digger extends AbsState {
	
	/**
	 * The initial number of block a digger can dig
	 */
	public static final int NB_BLOCK_TO_DIG = 5;
	
	/**
	 * The remaining number of block to dig
	 */
	private int nbBlockToDig = NB_BLOCK_TO_DIG;
	
	/**
	 * Indicate if the digger has already dug
	 */
	private boolean hasDug = false;
	
	/**
	 * 
	 * @param lemming   The lemming it belong to
	 */
	public Digger(Lemming lemming) {
		super(lemming);
	}

	/**
	 * A Digger can collide or fall or dig or walk
	 * If he cannot dig anymore he became a walker
	 */
	@Override
	public void step() {
		boolean hasWalked = false;
		boolean hasMoved = collision();
		if(!hasMoved) hasMoved |= fall();
		if(!hasMoved) hasMoved |= dig();
		if(!hasMoved){
			hasWalked = walk();
		}
		if(nbBlockToDig == 0 || (hasWalked && hasDug)){
			lemming.changeState(State.WALKER);
		}
	}
	
	/**
	 * Check if the lemming can dig 
	 * If yes the lemming dig under him
	 * 
	 * @return Return true if the lemming has dug
	 */
	public boolean dig(){
		List<Component> down = lemming.checkSide(Direction.DOWN());
		Component componentToMine = null;
		for(Component component : down){
			if(component.canBeMined()){
				componentToMine = component;
			}
		}
		if(componentToMine != null){
			componentToMine.destroy();
			nbBlockToDig--;
			hasDug = true;
			return true;
		}
		return false;
	}
	
	@Override
	public Type getTypeByState() {
		return Type.DIGGER;
	}
	
	@Override
	public Priority getPriority(){
		return Priority.PRIORITY_LEMMING_HIGH;
	}
	
}

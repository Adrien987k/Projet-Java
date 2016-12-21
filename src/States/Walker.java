package States;

import java.util.List;

import lemming.AbsState;
import lemming.Direction;
import lemming.Lemming;
import view.Type;
import component.Component;

public class Walker extends AbsState {
	
	//Utilité ???
	public Walker(Lemming lemming) {
		super(lemming);
	}

	@Override
	public void step(Lemming lemming) {
		
		//TODO Verifier si le position est hors du plateau
		
		List<Component> down = lemming.checkSide(Direction.DOWN);
		for(Component component : down){
			if(component.isVoid()){
				//TODO Le lemming tombe d'une case
			}
		}
		//TODO implémenter isKilling is... ect
		
		/*List<Component> foward = lemming.checkSide(Direction.DOWN);
		for(Component component : foward){
			if(component.isKilling()){
				//TODO Le lemming meure
			} else if {
				if(component.isInvert()){
					//TODO Le lemming change de direction
				}
			}
		}*/
	}

	@Override
	public void move(Lemming lemming) {
	}

	@Override
	public void construct(Lemming lemming) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy(Lemming lemming) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Type getTypeByState() {
		return Type.WALKER;
	}

}

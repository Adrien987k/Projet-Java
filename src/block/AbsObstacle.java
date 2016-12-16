package block;

import component.Coordinate;
import view.Type;

public abstract class AbsObstacle extends AbsBlock {
	
	public AbsObstacle(Coordinate coordinate, int priority, Type type) {
		super(coordinate,priority,type);
	}

}

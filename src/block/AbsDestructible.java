package block;

import component.Coordinate;
import view.Type;

public abstract class AbsDestructible extends AbsObstacle {

	public AbsDestructible(Coordinate coordinate, int priority, Type type) {
		super(coordinate,priority,type);
	}

}

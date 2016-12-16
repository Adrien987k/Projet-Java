package block;

import component.Coordinate;
import view.Type;

public abstract class AbsIndestructible extends AbsObstacle {

	public AbsIndestructible(Coordinate coordinate, int priority, Type type) {
		super(coordinate,priority,type);
	}

}

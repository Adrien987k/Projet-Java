package block;

import component.Coordinate;
import view.Type;

public abstract class AbsDoor extends AbsBlock {

	public AbsDoor(Coordinate coordinate, int priority, Type type) {
		super(coordinate,priority,type);
	}

}

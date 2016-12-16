package block;

import component.Component;
import component.Coordinate;
import view.Type;

public abstract class AbsBlock extends Component {
	
	public AbsBlock(Coordinate coordinate, int priority, Type type) {
		super(coordinate,priority,type);
	}
	
	public void step() {
		
	}
	
}

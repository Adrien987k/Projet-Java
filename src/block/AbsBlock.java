package block;

import component.Component;
import component.Coordinate;

public abstract class AbsBlock extends Component {
	
	public AbsBlock(Coordinate coordinate){
		this.coordinate = coordinate;
		priority = PRIORITY_BLOCK;
	}
	
}

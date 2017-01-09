
package view;

import java.util.List;

import component.Coordinate;

@Deprecated
public class ChangeGraphics extends AbsMemoryChange {
	
	private List<Type> changeType;

	public ChangeGraphics(Coordinate next, List<Type> changeType) {
		super(next);
		this.changeType = changeType;
	}
	
	public List<Type> getChangeType(){
		return changeType;
	}
	
}


package view;

import java.util.List;

import component.Coordinate;

public class ChangeGraphics extends AbsChange {
	
	private List<ChangeType> changeType;

	public ChangeGraphics(Coordinate next, List<ChangeType> changeType) {
		super(next);
		this.changeType = changeType;
	}
	
	public List<ChangeType> getChangeType(){
		return changeType;
	}
	
}

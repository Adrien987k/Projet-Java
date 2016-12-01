
package view;

import component.Coordinate;

public class ChangeGraphics extends AbsChange {
	
	private ChangeType changeType;

	public ChangeGraphics(Coordinate next, ChangeType changeType) {
		super(next);
		this.changeType = changeType;
	}
	
	public ChangeType getChangeType(){
		return changeType;
	}
	
}

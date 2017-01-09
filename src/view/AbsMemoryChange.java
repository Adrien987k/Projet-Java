package view;

import component.Coordinate;

@Deprecated
public class AbsMemoryChange extends AbsChange {

	private Coordinate next;

	public AbsMemoryChange(Coordinate next){
		super();
		this.next = next;
	}
	
	public Coordinate getCoordinate(){
		return next;
	}
}

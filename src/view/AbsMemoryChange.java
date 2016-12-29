package view;

import component.Coordinate;

public class AbsMemoryChange extends AbsChange {

	protected Coordinate next;

	public AbsMemoryChange(Coordinate next){
		super();
		this.next = next;
	}
	
	public Coordinate getCoordinate(){
		return next;
	}
}

package view;

import component.Coordinate;

public abstract class AbsChange {

	protected Coordinate next;

	public AbsChange(Coordinate next){
		this.next = next;
	}
	
	public Coordinate getCoordinate(){
		return next;
	}
	
	
}

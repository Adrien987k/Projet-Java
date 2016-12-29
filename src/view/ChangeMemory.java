package view;

import component.Component;
import component.Coordinate;

public class ChangeMemory extends AbsMemoryChange{

	private Component componentNext;
	private Component component;
	
	public ChangeMemory(Coordinate next, Component component, Component componentNext){
		super(next);
		this.component = component;
		this.componentNext = componentNext;
	}
	public ChangeMemory(Coordinate next, Component component){
		super(next);
		this.component = component;
		this.componentNext = component;
	}
	
	public Component getComponent(){
		return component;
	}
	
	public Component getComponentNext(){
		return componentNext;
	}
	
	
	
}

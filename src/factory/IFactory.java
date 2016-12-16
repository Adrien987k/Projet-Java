package factory;

import component.Component;
import lemming.Lemming;
import view.Type;

public interface IFactory {
	
	public Component make(Type t);
	public Lemming makeLemming();
	
}

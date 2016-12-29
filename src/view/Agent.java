package view;

import java.util.List;

public class Agent extends MyObservable implements MyObserver{
	
	public void addChangeToAgent(AbsChange change) {
		addChange(change);
	}
	
	@Override
	public void update(List<? extends AbsChange> changes) {
		addAllChanges(changes);
		notifyObserver();
	}

}

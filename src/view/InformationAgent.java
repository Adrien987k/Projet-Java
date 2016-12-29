package view;

import java.util.List;

public class InformationAgent extends MyObservable implements MyObserver{

	
	@Override
	public void update(List<? extends AbsChange> changes) {
		addAllChanges(changes);
		notifyObserver();
	}

}

package view;

import java.util.List;

public interface MyObserver {

	public void update(List<? extends AbsChange> changes);
	
}

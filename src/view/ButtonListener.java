package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ButtonListener implements ActionListener {
	
	protected AllView view;
	private ActionButton actionButton;
	
	public ButtonListener(AllView view,ActionButton actionButton) {
		this.view = view;
		this.actionButton = actionButton;
	}

	public ActionButton getActionButton() {
		return actionButton;
	}
	
	public AllView getView() {
		return view;
	}
	
	public void instantEffect() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(getView().getLastActionButtonSelected() != null)
			getView().getLastActionButtonSelected().isLast(false);
		getView().setLastActionButtonSelected(getActionButton());
		getView().setLastActionSelected(getActionButton().getActionType());
		getActionButton().isLast(true);
		instantEffect();
	}
	
}
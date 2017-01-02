package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

class ButtonListener implements ActionListener {
	AllView view;
	ActionButton actionButton;
	public ButtonListener(AllView view,ActionButton actionButton) {
		this.view = view;
		this.actionButton = actionButton;
	}

	public ActionButton getActionButton() {
		return actionButton;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		view.setCurrentAction(actionButton.getActionType());
		getActionButton().notifiyAgents();
		System.out.println(actionButton.getActionTitle());
	}
	
}
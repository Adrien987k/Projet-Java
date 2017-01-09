package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * ButtonListener is an ActionListener implementation adapted for @see ActionButton.
 * This implementation add a new method instantEffect to the actionPerformed,
 * 	useful to add more unique interaction with buttons.
 * @author Arnaud
 *
 */
class ButtonListener implements ActionListener {
	
	/**
	 * The view it belongs to.
	 */
	protected AllView view;
	
	/**
	 * The button listened.
	 */
	private ActionButton actionButton;
	
	/**
	 * Create an ButtoListener on the specified button in the specified view.
	 * @param view
	 * @param actionButton
	 */
	public ButtonListener(AllView view,ActionButton actionButton) {
		this.view = view;
		this.actionButton = actionButton;
	}
	
		
	
	/*GETTERS AND SETTERS*/
	
	public ActionButton getActionButton() {
		return actionButton;
	}
	
	public AllView getView() {
		return view;
	}
	
	/*END OF GETTERS AND SETTERS*/
	
	/**
	 * This method can be reimplemented to add more special interaction.
	 * This method should not be called directly, it's called when actionPerformed is.
	 */
	public void instantEffect() {
		
	}
	
	/**
	 * This method sets the current button as the last button selected on the view.
	 * It also adds the action associated with the button in the view.
	 * The method instantEffect is called.
	 */
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
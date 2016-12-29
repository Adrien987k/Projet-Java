package view;

public class ChangeDescription extends AbsGraphicsChange {

	private ActionType actionType;
	
	public ChangeDescription(ActionType actionType) {
		this.actionType = actionType;
	}
	
	public ActionType getActionType() {
		return actionType;
	}
	

}

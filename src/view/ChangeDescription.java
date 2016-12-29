package view;

public class ChangeDescription extends AbsChange {

	private ActionType actionType;
	
	public ChangeDescription(ActionType actionType) {
		super(null);
		this.actionType = actionType;
	}
	
	public ActionType getActionType() {
		return actionType;
	}
	

}

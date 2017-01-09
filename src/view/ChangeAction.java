package view;

@Deprecated
public class ChangeAction extends AbsGraphicsChange {

	private ActionType actionType;
	
	public ChangeAction(ActionType actionType) {
		this.actionType = actionType;
	}
	
	public ActionType getActionType() {
		return actionType;
	}
	

}

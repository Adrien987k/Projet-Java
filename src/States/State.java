package States;

import view.Type;

public enum State {
	
	BLOCKER("blocker", Type.BLOCKER),
	BOMBER("bomber", Type.BOMBER),
	CARPENTER("carpenter", Type.CARPENTER),
	CLIMBER("climber", Type.CLIMBER),
	DIGGER("digger", Type.DIGGER),
	PARACHUTIST("parachutist", Type.PARACHUTIST),
	TUNNELER("tunneler", Type.TUNNELER),
	WALKER("", Type.WALKER);
	
	private String fileToken;
	private Type type;
	
	State(String fileToken, Type type){
		this.fileToken = fileToken;
		this.type = type;
	}
	
	public String getFileToken(){
		return fileToken;
	}
	
	public Type getType(){
		return type;
	}
	
}

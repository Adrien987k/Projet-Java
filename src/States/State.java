package States;

public enum State {
	BLOCKER("blocker"),
	BOMBER("bomber"),
	CARPENTER("carpenter"),
	CLIMBER("climber"),
	DIGGER("digger"),
	PARACHUTIST("parachutist"),
	TUNNELER("tunneler"),
	WALKER("");
	
	private String fileToken;
	
	State(String fileToken){
		this.fileToken = fileToken;
	}
	
	public String getFileToken(){
		return fileToken;
	}
}

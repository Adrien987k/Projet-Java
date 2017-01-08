package States;

import view.Type;

/**
 * Enumeration for the different states for the lemmings
 * 
 * @author Adrien
 *
 */
public enum State {
	
	BLOCKER("blocker", Type.BLOCKER),
	BOMBER("bomber", Type.BOMBER),
	CARPENTER("carpenter", Type.CARPENTER),
	CLIMBER("climber", Type.CLIMBER),
	DIGGER("digger", Type.DIGGER),
	PARACHUTIST("parachutist", Type.PARACHUTIST),
	TUNNELER("tunneler", Type.TUNNELER),
	WALKER("", Type.WALKER);
	
	/**
	 * The token use in the file to indicate the number of use of this state in the level
	 */
	private String fileToken;
	
	/**
	 * The type corresponding
	 */
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

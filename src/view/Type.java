package view;

public enum Type {
	LEMMING("LL"),
	WALKER("LW"),
	BLOCKER("LBL"),
	TUNNELER("LT"),
	DIGGER("LD"),
	PARACHUTIST("LP"),
	CARPENTER("LCA"),
	BOMBER("LB"),
	CLIMBER("LC"),
	SIMPLE_DESTRUCTIBLE("SD"),
	BOMB("B"),
	AGAIN("A"),
	SIMPLE_INDESTRUCTIBLE("SI"),
	LAVA("L"),
	TP("T"),
	START("S"),
	END("E"),
	VOID("V");
	
	private String fileEncoding;
	
	Type(String fileEncoding){
		this.fileEncoding = fileEncoding;
	}
	
	public String getFileEncoding(){
		return fileEncoding;
	}
}

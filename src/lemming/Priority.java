package lemming;

public enum Priority {

	PRIORITY_BLOCK(0),
	PRIORITY_LEMMING_WEAK(1),
	PRIORITY_LEMMING_MEDIUM(2),
	PRIORITY_LEMMING_HIGH(3);
	
	private int value;
	
	Priority(int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
}

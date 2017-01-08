package lemming;

/**
 * Indicate the display priority when multiple components have to be render at the same coordinate
 * 
 * @author Adrien
 *
 */
public enum Priority {

	PRIORITY_BLOCK(0),
	PRIORITY_LEMMING_WEAK(1),
	PRIORITY_LEMMING_MEDIUM(2),
	PRIORITY_LEMMING_HIGH(3);
	
	/**
	 * Indicate the value of the priority
	 * Indeed we are now able to sort a list of priority now
	 */
	private int value;
	
	Priority(int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	
}

package view;

public class ScoreBoard {
	private int nbDeadLemmings;
	private int nbFreeLemmings;
	private boolean isVictory;	
	private String message;
	
	public ScoreBoard(int nbDeadLemmings, int nbFreeLemmings, boolean isVictory) {
		this.nbDeadLemmings = nbDeadLemmings;
		this.nbFreeLemmings = nbFreeLemmings;
		this.isVictory = isVictory;
	}
	
	public int getNbDeadLemmings() {
		return nbDeadLemmings;
	}
	public void setNbDeadLemmings(int nbDeadLemmings) {
		this.nbDeadLemmings = nbDeadLemmings;
	}
	public int getNbFreeLemmings() {
		return nbFreeLemmings;
	}
	public void setNbFreeLemmings(int nbFreeLemmings) {
		this.nbFreeLemmings = nbFreeLemmings;
	}
	public boolean isVictory() {
		return isVictory;
	}
	public void setVictory(boolean isVictory) {
		this.isVictory = isVictory;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public String toString() {
		if(isVictory())
			setMessage("Victory !");
		else
			setMessage("Failure !");
		
		return "Dead lemmings: " + getNbDeadLemmings() + "\nFree lemmings:" + getNbFreeLemmings() + "\n"+ message;
	}
	
	
}

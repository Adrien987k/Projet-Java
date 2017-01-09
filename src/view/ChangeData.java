package view;

import java.util.Map;

@Deprecated
public class ChangeData extends AbsChange {
	
		private int nbDeadLemmings;
		private int nbFreeLemmings;
		private int nbRemainingLemmings;
		private boolean running;
		private Map<String,Integer> levelParameters;
		
		public ChangeData(int nbDeadLemmings, int nbFreeLemmings, int nbRemainingLemmings) {
			super();
			this.nbDeadLemmings = nbDeadLemmings;
			this.nbFreeLemmings = nbFreeLemmings;
			this.nbRemainingLemmings = nbRemainingLemmings;
		}
		public ChangeData(int nbDeadLemmings, int nbFreeLemmings, int nbRemainingLemmings, Map<String,Integer> levelparameters) {
			super();
			this.nbDeadLemmings = nbDeadLemmings;
			this.nbFreeLemmings = nbFreeLemmings;
			this.nbRemainingLemmings = nbRemainingLemmings;
			this.levelParameters = levelparameters;
		}
		public ChangeData(int nbDeadLemmings, int nbFreeLemmings, int nbRemainingLemmings, boolean running, Map<String,Integer> levelparameters) {
			super();
			this.nbDeadLemmings = nbDeadLemmings;
			this.nbFreeLemmings = nbFreeLemmings;
			this.nbRemainingLemmings = nbRemainingLemmings;
			this.running = running;
			this.levelParameters = levelparameters;
		}

		public Map<String, Integer> getLevelParameters() {
			return levelParameters;
		}
		
		public void setLevelParameters(Map<String, Integer> levelParameters) {
			this.levelParameters = levelParameters;
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

		public int getNbRemainingLemmings() {
			return nbRemainingLemmings;
		}
		
		public void setNbRemainingLemmings(int nbRemainingLemmings) {
			this.nbRemainingLemmings = nbRemainingLemmings;
		}
		
		public boolean getRunning() {
			return running;
		}
}

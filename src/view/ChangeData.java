package view;

public class ChangeData extends AbsChange{
		private int nbDeadLemmings;
		private int nbFreeLemmings;
		private int nbRemainingLemmings;
		
		
		public ChangeData(int nbDeadLemmings, int nbFreeLemmings, int nbRemainingLemmings) {
			super();
			this.nbDeadLemmings = nbDeadLemmings;
			this.nbFreeLemmings = nbFreeLemmings;
			this.nbRemainingLemmings = nbRemainingLemmings;
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
}

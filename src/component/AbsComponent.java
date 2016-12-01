package component;

public abstract class AbsComponent {
	private Coordinate coordinate;
	
	public AbsComponent() {
		coordinate = new Coordinate();
	}
	public AbsComponent(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
}

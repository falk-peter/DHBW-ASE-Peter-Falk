package domain;

public class Destroyer extends Ship {
	public Destroyer(ShipCoordinates coordinates) {
		super(coordinates);
	}
	
	@Override
	boolean sizeCorrect() {
		return (size == 3);
	}
}

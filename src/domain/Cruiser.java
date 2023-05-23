package domain;

public class Cruiser extends Ship {
	public Cruiser(ShipCoordinates coordinates) {
		super(coordinates);
	}
	
	@Override
	boolean sizeCorrect() {
		return (size == 4);
	}
}

package domain;

public class Cruiser extends Ship {
	public Cruiser(ShipCoordinates coordinates) {
		super(coordinates);
	}
	
	@Override
	public boolean sizeCorrect() {
		return (size == 4);
	}
}

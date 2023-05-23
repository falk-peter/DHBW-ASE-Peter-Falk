package domain;

public class Submarine extends Ship{
	public Submarine(ShipCoordinates coordinates) {
		super(coordinates);
	}

	@Override
	boolean sizeCorrect() {
		return (size == 2);
	}
}
package domain;

public class Battleship extends Ship {
	public Battleship(ShipCoordinates coordinates) {
		super(coordinates);
	}
	
	@Override
	boolean sizeCorrect() {
		return (size == 5);
	}
}

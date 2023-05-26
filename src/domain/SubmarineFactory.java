package domain;

public class SubmarineFactory extends ShipFactory {
	public Submarine createShip(ShipCoordinates shipCoordinates) {
		return new Submarine(shipCoordinates);
	}
}
package domain;

public class DestroyerFactory extends ShipFactory {
	public Destroyer createShip(ShipCoordinates shipCoordinates) {
		return new Destroyer(shipCoordinates);
	}
}

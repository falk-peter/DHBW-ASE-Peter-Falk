package domain;

public class CruiserFactory extends ShipFactory {
	public Cruiser createShip(ShipCoordinates shipCoordinates) {
		return new Cruiser(shipCoordinates);
	}
}

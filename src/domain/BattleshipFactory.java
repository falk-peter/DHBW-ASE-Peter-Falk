package domain;

public class BattleshipFactory extends ShipFactory {
	public Battleship createShip(ShipCoordinates shipCoordinates) {
		return new Battleship(shipCoordinates);
	}
}

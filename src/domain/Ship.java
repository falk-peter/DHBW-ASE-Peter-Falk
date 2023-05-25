package domain;

public abstract class Ship {
	protected ShipCoordinates coordinates;

	public Ship(ShipCoordinates coordinates) {
		this.coordinates = coordinates;
	}
	
	public abstract boolean sizeCorrect();

	public void setCoordinates(ShipCoordinates coordinates) {
		this.coordinates = coordinates;
	}

	public ShipCoordinates getCoordinates() {
		return coordinates;
	}
}
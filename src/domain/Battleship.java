package domain;

public class Battleship extends Ship {
	public Battleship(ShipCoordinates coordinates) {
		super(coordinates);
	}

	@Override
	public boolean sizeCorrect() {
		int size_x = coordinates.getX2() - coordinates.getX1();
		int size_y = coordinates.getY2() - coordinates.getY2();

		int size;
		if (size_x > size_y)
			size = size_x;
		else
			size = size_y;

		return (size == 4);
	}
}

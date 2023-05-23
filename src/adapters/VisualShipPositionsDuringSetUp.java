package adapters;

import domain.ShipPositions;
import interfaces.VisualShipPositionsInterface;

public class VisualShipPositionsDuringSetUp implements VisualShipPositionsInterface {
	private char[][] shipPositions;
	private final int xAxis = 29;
	private final int yAxis = 15;
	private final char STAR = '*';
	private final char WATER = '~';

	public VisualShipPositionsDuringSetUp() {
		this.shipPositions = new char[yAxis][xAxis];
		initialize();
	}

	public void initialize() {
		initializeSize();
		initializeStarBorders();
		initializeAxisDescription();
		initializeXAxis();
		initializeYAxis();
		initializeWater();
	}

	public void initializeSize() {
		for (int y = 0; y < yAxis; y++) {
			for (int x = 0; x < xAxis; x++) {
				shipPositions[y][x] = ' ';
			}
		}
	}

	public void initializeStarBorders() {
		for (int x = 0; x < xAxis; x = x + 2) {
			shipPositions[0][x] = STAR;
		}

		for (int x = 0; x < xAxis; x = x + 2) {
			shipPositions[yAxis - 1][x] = STAR;
		}

		for (int y = 0; y < yAxis; y++) {
			shipPositions[y][0] = STAR;
		}

		for (int y = 0; y < yAxis; y++) {
			shipPositions[y][xAxis - 1] = STAR;
		}
	}

	public void initializeAxisDescription() {
		shipPositions[1][15] = 'x';
		shipPositions[8][2] = 'y';
	}

	public void initializeXAxis() {
		int axisNumber = 0;
		for (int x = 6; x < 25; x = x + 2) {
			shipPositions[2][x] = (char) (axisNumber + '0');
			axisNumber++;
		}
	}

	public void initializeYAxis() {
		int axisNumber = 0;
		for (int y = 3; y < 13; y++) {
			shipPositions[y][4] = (char) (axisNumber + '0');
			axisNumber++;
		}
	}

	public void initializeWater() {
		for (int y = 3; y < 13; y++) {
			for (int x = 6; x < 25; x = x + 2) {
				shipPositions[y][x] = WATER;
			}
		}
	}

	@Override
	public void update(ShipPositions shipPositions) {
		int xShipPosition = 0, yShipPosition = 0;
		char[][] newPositions = shipPositions.getPositionOfShips();
		boolean[][] newBlockedFields = shipPositions.getBlockedFields();

		for (int y = 3; y < 13; y++) {
			for (int x = 6; x < 25; x = x + 2) {
				if (newBlockedFields[yShipPosition][xShipPosition] == true)
					this.shipPositions[y][x] = 'X';
				if (newPositions[yShipPosition][xShipPosition] == 'S')
					this.shipPositions[y][x] = 'S';
				xShipPosition++;
			}
			yShipPosition++;
			xShipPosition = 0;
		}
	}

	@Override
	public void print() {
		for (int y = 0; y < yAxis; y++) {
			for (int x = 0; x < xAxis; x++) {
				System.out.print(shipPositions[y][x]);
			}
			System.out.println();
		}
	}

	public char[][] getShipPositions() {
		return this.shipPositions;
	}
}

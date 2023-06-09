package field;

import ship.Ship;

public class ShipPositions {
	private char[][] positionOfShips;
	private boolean[][] blockedFields;
	private final int xAxis = 10;
	private final int yAxis = 10;

	public ShipPositions() {
		positionOfShips = new char[yAxis][xAxis];
		blockedFields = new boolean[yAxis][xAxis];
		initialize();
	}

	public void initialize() {
		initializePositionOfShips();
		initializeBlockedFields();
	}

	public void initializePositionOfShips() {
		for (int y = 0; y < yAxis; y++) {
			for (int x = 0; x < xAxis; x++) {
				positionOfShips[y][x] = '~';
			}
		}
	}

	public void initializeBlockedFields() {
		for (int y = 0; y < yAxis; y++) {
			for (int x = 0; x < xAxis; x++) {
				blockedFields[y][x] = false;
			}
		}
	}

	public boolean addShip(Ship ship) {
		if (isBlocked(ship)) {
			System.out.println("Ship is blocked! Try again!");
			return false;
		} else {
			updateBlockedFields(ship);
			updateShipPositions(ship);
			return true;
		}
	}

	public boolean isBlocked(Ship ship) {
		for (int y = ship.getStartY(); y <= ship.getEndY(); y++) {
			for (int x = ship.getStartX(); x <= ship.getEndX(); x++) {
				if (blockedFields[y][x] == true) {
					System.out.println("Error: (" + x + "/" + y + ") is already blocked by another ship!") ;
					return true;
				}
			}
		}
		return false;
	}

	public void updateBlockedFields(Ship ship) {
		for (int y = ship.getStartY(); y <= ship.getEndY(); y++) {
			for (int x = ship.getStartX(); x <= ship.getEndX(); x++) {
				blockedFields[y][x] = true;

				if (ship.getStartY() != 0)
					blockedFields[y - 1][x] = true;

				if (ship.getEndY() != 9)
					blockedFields[y + 1][x] = true;

				if (ship.getStartX() != 0)
					blockedFields[y][x - 1] = true;

				if (ship.getEndX() != 9)
					blockedFields[y][x + 1] = true;

				if (ship.getStartX() != 0 && ship.getStartY() != 0) {
					blockedFields[y - 1][x - 1] = true;
				}

				if (ship.getEndX() != 9 && ship.getStartY() != 0) {
					blockedFields[y - 1][x + 1] = true;
				}

				if (ship.getStartX() != 0 && ship.getEndY() != 9) {
					blockedFields[y + 1][x - 1] = true;
				}

				if (ship.getEndX() != 9 && ship.getEndY() != 9) {
					blockedFields[y + 1][x + 1] = true;
				}
			}
		}

	}

	public void updateShipPositions(Ship ship) {
		for (int y = ship.getStartY(); y <= ship.getEndY(); y++) {
			for (int x = ship.getStartX(); x <= ship.getEndX(); x++) {
				positionOfShips[y][x] = 'S';
			}
		}
	}

	public boolean isHit(int x, int y) {
		if (positionOfShips[y][x] == 'S')
			return true;
		else
			return false;
	}

	public void printPositionOfShips() {
		for (int y = 0; y < yAxis; y++) {
			for (int x = 0; x < xAxis; x++) {
				System.out.print(positionOfShips[y][x]);
			}
			System.out.println();
		}
	}

	public void printBlockedFields() {
		for (int y = 0; y < yAxis; y++) {
			for (int x = 0; x < xAxis; x++) {
				if (blockedFields[y][x])
					System.out.print(1);
				else
					System.out.print(0);
			}
			System.out.println();
		}
	}

	public char[][] getPositionOfShips() {
		return positionOfShips;
	}

	public void setPositionOfShips(char[][] positionOfShips) {
		this.positionOfShips = positionOfShips;
	}

	public boolean[][] getBlockedFields() {
		return blockedFields;
	}

	public void setBlockedFields(boolean[][] blockedFields) {
		this.blockedFields = blockedFields;
	}
}
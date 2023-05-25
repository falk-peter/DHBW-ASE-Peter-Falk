package domain;

import interfaces.VisualShipPositionsInterface;

public class ShipPositions {
	private VisualShipPositionsInterface visualOutput;
	
	private char[][] positionOfShips;
	private boolean[][] blockedFields;
	
	private final int xAxis = 10;
	private final int yAxis = 10;

	public ShipPositions(VisualShipPositionsInterface visualOutput) {
		this.visualOutput = visualOutput;
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
			visualOutput.update(this);
			print();
			
			return true;
		}
	}

	public boolean isBlocked(Ship ship) {
		for (int y = ship.getCoordinates().getY1(); y <= ship.getCoordinates().getY2(); y++) {
			for (int x = ship.getCoordinates().getX1(); x <= ship.getCoordinates().getX2(); x++) {
				if (blockedFields[y][x] == true) {
					System.out.println("Error: (" + x + "/" + y + ") is already blocked by another ship!") ;
					return true;
				}
			}
		}
		return false;
	}

	public void updateBlockedFields(Ship ship) {
		for (int y = ship.getCoordinates().getY1(); y <= ship.getCoordinates().getY2(); y++) {
			for (int x = ship.getCoordinates().getX1(); x <= ship.getCoordinates().getX2(); x++) {
				blockedFields[y][x] = true;

				if (ship.getCoordinates().getY1() != 0)
					blockedFields[y - 1][x] = true;

				if (ship.getCoordinates().getY2() != 9)
					blockedFields[y + 1][x] = true;

				if (ship.getCoordinates().getX1() != 0)
					blockedFields[y][x - 1] = true;

				if (ship.getCoordinates().getX2() != 9)
					blockedFields[y][x + 1] = true;

				if (ship.getCoordinates().getX1() != 0 && ship.getCoordinates().getY1() != 0) {
					blockedFields[y - 1][x - 1] = true;
				}

				if (ship.getCoordinates().getX2() != 9 && ship.getCoordinates().getY1() != 0) {
					blockedFields[y - 1][x + 1] = true;
				}

				if (ship.getCoordinates().getX1() != 0 && ship.getCoordinates().getY2() != 9) {
					blockedFields[y + 1][x - 1] = true;
				}

				if (ship.getCoordinates().getX2() != 9 && ship.getCoordinates().getY2() != 9) {
					blockedFields[y + 1][x + 1] = true;
				}
			}
		}

	}

	public void updateShipPositions(Ship ship) {
		for (int y = ship.getCoordinates().getY1(); y <= ship.getCoordinates().getY2(); y++) {
			for (int x = ship.getCoordinates().getX1(); x <= ship.getCoordinates().getX2(); x++) {
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
	
	public void print() {
		visualOutput.print();
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
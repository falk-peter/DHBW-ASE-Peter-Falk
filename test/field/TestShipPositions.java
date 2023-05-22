package field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import domain.ShipCoordinates;
import ship.Ship;

public class TestShipPositions {
	@Test
	public void testShipPositions() {
		ShipPositions shipPositions = new ShipPositions();
		char[][] resultShipPositions = shipPositions.getPositionOfShips();
		boolean[][] resultBlockedFields = shipPositions.getBlockedFields();

		assertEquals('~', resultShipPositions[0][0]);
		assertEquals('~', resultShipPositions[9][9]);
		assertEquals(false, resultBlockedFields[0][0]);
		assertEquals(false, resultBlockedFields[9][9]);
	}

	@Test
	public void testIsNotBlocked() {
		ShipPositions shipPositions = new ShipPositions();
		ShipCoordinates shipCoordinates = new ShipCoordinates();

		shipCoordinates.setX1(0);
		shipCoordinates.setX2(1);
		shipCoordinates.setY1(0);
		shipCoordinates.setY2(0);

		Ship ship = new Ship(shipCoordinates);
		assertFalse(shipPositions.isBlocked(ship));
	}

	@Test
	public void testIsBlocked() {
		ShipPositions shipPositions = new ShipPositions();
		ShipCoordinates shipCoordinates = new ShipCoordinates();

		shipCoordinates.setX1(0);
		shipCoordinates.setX2(1);
		shipCoordinates.setY1(0);
		shipCoordinates.setY2(0);

		boolean[][] blockedFields = shipPositions.getBlockedFields();
		blockedFields[0][0] = true;

		Ship ship = new Ship(shipCoordinates);
		assertTrue(shipPositions.isBlocked(ship));
	}

	@Test
	public void testUpdateShipPositionsForHorizontalShip() {
		ShipPositions shipPositions = new ShipPositions();
		ShipCoordinates shipCoordinates = new ShipCoordinates();

		shipCoordinates.setX1(0);
		shipCoordinates.setX2(1);
		shipCoordinates.setY1(0);
		shipCoordinates.setY2(0);
		Ship ship = new Ship(shipCoordinates);
		shipPositions.updateShipPositions(ship);

		char[][] result = shipPositions.getPositionOfShips();

		assertEquals('S', result[0][0]);
		assertEquals('S', result[0][1]);
	}

	@Test
	public void testUpdateShipPositionsForVerticalShip() {
		ShipPositions shipPositions = new ShipPositions();
		ShipCoordinates shipCoordinates = new ShipCoordinates();

		shipCoordinates.setX1(0);
		shipCoordinates.setX2(0);
		shipCoordinates.setY1(0);
		shipCoordinates.setY2(1);
		Ship ship = new Ship(shipCoordinates);

		shipPositions.updateShipPositions(ship);

		char[][] result = shipPositions.getPositionOfShips();

		assertEquals('S', result[0][0]);
		assertEquals('S', result[1][0]);
	}

	@Test
	public void testUpdateBlockedFields() {
		ShipPositions shipPositions = new ShipPositions();
		ShipCoordinates shipCoordinates = new ShipCoordinates();

		shipCoordinates.setX1(4);
		shipCoordinates.setX2(5);
		shipCoordinates.setY1(2);
		shipCoordinates.setY2(2);
		Ship ship1 = new Ship(shipCoordinates);

		shipPositions.updateBlockedFields(ship1);

		boolean[][] result = shipPositions.getBlockedFields();

		for (int y = ship1.getStartY() - 1; y <= ship1.getEndY() + 1; y++) {
			for (int x = ship1.getStartX() - 1; x <= ship1.getEndX(); x++) {
				assertEquals(true, result[y][x]);
			}
		}

		System.out.println();

		shipCoordinates.setX1(0);
		shipCoordinates.setX2(1);
		shipCoordinates.setY1(0);
		shipCoordinates.setY2(0);

		Ship ship2 = new Ship(shipCoordinates);

		shipPositions.updateBlockedFields(ship2);

		result = shipPositions.getBlockedFields();

		for (int y = ship2.getStartY(); y <= ship2.getEndY() + 1; y++) {
			for (int x = ship2.getStartX(); x <= ship2.getEndX() + 1; x++) {
				assertEquals(true, result[y][x]);
			}
		}
	}

	@Test
	public void testAddShipSuccess() {
		ShipPositions shipPositions = new ShipPositions();
		ShipCoordinates shipCoordinates = new ShipCoordinates();

		shipCoordinates.setX1(4);
		shipCoordinates.setX2(5);
		shipCoordinates.setY1(2);
		shipCoordinates.setY2(2);
		Ship ship1 = new Ship(shipCoordinates);

		assertEquals(true, shipPositions.addShip(ship1));
	}

	@Test
	public void testAddShipFailBecauseOfBlock() {
		ShipPositions shipPositions = new ShipPositions();
		ShipCoordinates shipCoordinates = new ShipCoordinates();

		shipCoordinates.setX1(4);
		shipCoordinates.setX2(5);
		shipCoordinates.setY1(2);
		shipCoordinates.setY2(2);
		Ship ship1 = new Ship(shipCoordinates);

		boolean[][] blockedFields = shipPositions.getBlockedFields();
		blockedFields[2][4] = true;
		shipPositions.setBlockedFields(blockedFields);

		assertEquals(false, shipPositions.addShip(ship1));
	}

	@Test
	public void testIsNotAHit() {
		ShipPositions shipPositions = new ShipPositions();

		assertEquals(false, shipPositions.isHit(5, 5));
	}
	
	@Test
	public void testIsHot() {
		ShipPositions shipPositions = new ShipPositions();
		char shipPositionsArray[][] = shipPositions.getPositionOfShips();
		shipPositionsArray[5][5] = 'S';
		shipPositions.setPositionOfShips(shipPositionsArray);
		
		assertEquals(true, shipPositions.isHit(5, 5));
	}
}

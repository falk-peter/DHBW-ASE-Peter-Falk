package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import adapters.VisualShipPositionsDuringSetUp;

public class TestShipPositions {
	ShipPositions shipPositions;

	@Before
	public void setUp() {
		VisualShipPositionsDuringSetUp visual = new VisualShipPositionsDuringSetUp();
		shipPositions = new ShipPositions(visual);
	}

	@Test
	public void testShipPositions() {
		char[][] resultShipPositions = shipPositions.getPositionOfShips();
		boolean[][] resultBlockedFields = shipPositions.getBlockedFields();

		assertEquals('~', resultShipPositions[0][0]);
		assertEquals('~', resultShipPositions[9][9]);
		assertEquals(false, resultBlockedFields[0][0]);
		assertEquals(false, resultBlockedFields[9][9]);
	}

	@Test
	public void testIsNotBlocked() {
		ShipCoordinates shipCoordinates = new ShipCoordinates(0, 0, 1, 0);
		Ship ship = new Submarine(shipCoordinates);
		assertFalse(shipPositions.isBlocked(ship));
	}

	@Test
	public void testIsBlocked() {
		ShipCoordinates shipCoordinates = new ShipCoordinates(0, 0, 1, 0);
		boolean[][] blockedFields = shipPositions.getBlockedFields();
		blockedFields[0][0] = true;

		Ship ship = new Submarine(shipCoordinates);
		assertTrue(shipPositions.isBlocked(ship));
	}

	@Test
	public void testUpdateShipPositionsForHorizontalShip() {
		ShipCoordinates shipCoordinates = new ShipCoordinates(0, 0, 1, 0);
		Ship ship = new Submarine(shipCoordinates);
		shipPositions.updateShipPositions(ship);

		char[][] result = shipPositions.getPositionOfShips();

		assertEquals('S', result[0][0]);
		assertEquals('S', result[0][1]);
	}

	@Test
	public void testUpdateShipPositionsForVerticalShip() {
		ShipCoordinates shipCoordinates = new ShipCoordinates(0, 0, 0, 1);
		Ship ship = new Submarine(shipCoordinates);

		shipPositions.updateShipPositions(ship);

		char[][] result = shipPositions.getPositionOfShips();

		assertEquals('S', result[0][0]);
		assertEquals('S', result[1][0]);
	}

	@Test
	public void testUpdateBlockedFields() {
		ShipCoordinates shipCoordinates = new ShipCoordinates(4, 2, 5, 2);
		Ship ship1 = new Submarine(shipCoordinates);

		shipPositions.updateBlockedFields(ship1);

		boolean[][] result = shipPositions.getBlockedFields();

		for (int y = ship1.getCoordinates().getY1() - 1; y <= ship1.getCoordinates().getY2() + 1; y++) {
			for (int x = ship1.getCoordinates().getX1() - 1; x <= ship1.getCoordinates().getX2(); x++) {
				assertEquals(true, result[y][x]);
			}
		}

		ShipCoordinates shipCoordinates2 = new ShipCoordinates(0, 0, 1, 0);
		Ship ship2 = new Submarine(shipCoordinates2);

		shipPositions.updateBlockedFields(ship2);

		result = shipPositions.getBlockedFields();

		for (int y = ship2.getCoordinates().getY1(); y <= ship2.getCoordinates().getY2() + 1; y++) {
			for (int x = ship2.getCoordinates().getX1(); x <= ship2.getCoordinates().getX2() + 1; x++) {
				assertEquals(true, result[y][x]);
			}
		}
	}

	@Test
	public void testAddShipSuccess() {
		ShipCoordinates shipCoordinates = new ShipCoordinates(4, 2, 5, 2);
		Ship ship1 = new Submarine(shipCoordinates);

		assertEquals(true, shipPositions.addShip(ship1));
	}

	@Test
	public void testAddShipFailBecauseOfBlock() {
		ShipCoordinates shipCoordinates = new ShipCoordinates(4, 2, 5, 2);
		Ship ship1 = new Submarine(shipCoordinates);

		boolean[][] blockedFields = shipPositions.getBlockedFields();
		blockedFields[2][4] = true;
		shipPositions.setBlockedFields(blockedFields);

		assertEquals(false, shipPositions.addShip(ship1));
	}

	@Test
	public void testIsNotAHit() {
		assertEquals(false, shipPositions.isHit(5, 5));
	}

	@Test
	public void testIsHot() {
		char shipPositionsArray[][] = shipPositions.getPositionOfShips();
		shipPositionsArray[5][5] = 'S';
		shipPositions.setPositionOfShips(shipPositionsArray);

		assertEquals(true, shipPositions.isHit(5, 5));
	}
}

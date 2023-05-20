package game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import input.ShipCoordinates;
import player.Player;

public class TestGame {
	/*
	 * Only tested setUpSubmarine => other setUpXYZ()-methods work the same
	 */
	@Test
	public void testSetUpSubmarine() {
		Player testPlayer = new Player("Test");
		Game game = new Game();
		game.setPlayerA(testPlayer);

		ShipCoordinates shipCoordinates = new ShipCoordinates();
		shipCoordinates.setX1(0);
		shipCoordinates.setY1(0);
		shipCoordinates.setX2(1);
		shipCoordinates.setY2(0);

		boolean[][] result = testPlayer.getShipPositions().getBlockedFields();

		assertEquals(true, game.setUpSubmarine(shipCoordinates, testPlayer));
		assertEquals(true, result[0][0]);
	}

	@Test
	public void testSetUpSubmarineFailTooBig() {
		Player testPlayer = new Player("Test");
		Game game = new Game();
		game.setPlayerA(testPlayer);

		ShipCoordinates shipCoordinates = new ShipCoordinates();
		shipCoordinates.setX1(0);
		shipCoordinates.setY1(0);
		shipCoordinates.setX2(2);
		shipCoordinates.setY2(0);

		assertEquals(false, game.setUpSubmarine(shipCoordinates, testPlayer));
	}

	@Test
	public void testSetUpSubmarineFailIsBlocked() {
		Player testPlayer = new Player("Test");
		Game game = new Game();
		game.setPlayerA(testPlayer);
		boolean[][] blockedFields = testPlayer.getShipPositions().getBlockedFields();
		blockedFields[0][0] = true;
		testPlayer.getShipPositions().setBlockedFields(blockedFields);

		ShipCoordinates shipCoordinates = new ShipCoordinates();
		shipCoordinates.setX1(0);
		shipCoordinates.setY1(0);
		shipCoordinates.setX2(1);
		shipCoordinates.setY2(0);

		assertEquals(false, game.setUpSubmarine(shipCoordinates, testPlayer));
	}

	@Test
	public void testDetermineWhoStarts() {
		Game game = new Game();
		assertEquals('A', game.determineWhoStarts(1, 0));
	}
}

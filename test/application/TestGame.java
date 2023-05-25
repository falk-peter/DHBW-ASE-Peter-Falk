package application;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import adapters.OutputConsole;
import adapters.VisualBattlefieldDuringGame;
import adapters.VisualShipPositionsDuringSetUp;
import domain.Ship;
import domain.ShipCoordinates;
import domain.Submarine;

public class TestGame {
	Game testGame;
	Player testPlayer;
	
	@Before
	public void setUp() {
		OutputConsole output = new OutputConsole();
		VisualShipPositionsDuringSetUp positions = new VisualShipPositionsDuringSetUp();
		VisualBattlefieldDuringGame battlefield = new VisualBattlefieldDuringGame();
		testPlayer = new Player("Test", positions, battlefield);
		testGame = new Game(output, null, null, null, null, null);
		testGame.setPlayerA(testPlayer);
	}
	
	@Test
	public void testSetUpShip() {
		ShipCoordinates shipCoordinates = new ShipCoordinates(0, 0, 1, 0);
		Ship ship = new Submarine(shipCoordinates);

		boolean testMethod = testGame.tryAddShipToPlayer(ship, testPlayer);
		boolean[][] result = testPlayer.getShipPositions().getBlockedFields();

		assertEquals(true, testMethod);
		assertEquals(true, result[0][0]);
	}

	@Test
	public void testSetUpShipFailTooBig() {
		ShipCoordinates shipCoordinates = new ShipCoordinates(0, 0, 2, 0);
		Ship ship = new Submarine(shipCoordinates);

		assertEquals(false, testGame.tryAddShipToPlayer(ship, testPlayer));
	}

	@Test
	public void testSetUpShipFailIsBlocked() {
		boolean[][] blockedFields = testPlayer.getShipPositions().getBlockedFields();
		blockedFields[0][0] = true;
		testPlayer.getShipPositions().setBlockedFields(blockedFields);

		ShipCoordinates shipCoordinates = new ShipCoordinates(0, 0, 1, 0);
		Ship ship = new Submarine(shipCoordinates);

		assertEquals(false, testGame.tryAddShipToPlayer(ship, testPlayer));
	}
}

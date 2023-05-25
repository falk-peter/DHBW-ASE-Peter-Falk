package application;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import adapters.VisualBattlefieldDuringGame;
import adapters.VisualShipPositionsDuringSetUp;
import domain.Ship;
import domain.ShipCoordinates;
import domain.Shot;
import domain.ShotCoordinates;
import domain.Submarine;

public class TestPlayer {
	Player testPlayer;

	@Before
	public void setUp() {
		VisualShipPositionsDuringSetUp positions = new VisualShipPositionsDuringSetUp();
		VisualBattlefieldDuringGame battlefield = new VisualBattlefieldDuringGame();
		testPlayer = new Player("Test", positions, battlefield);
	}

	@Test
	public void testAddShip() {
		ShipCoordinates shipCoordinates = new ShipCoordinates(5, 5, 6, 5);
		Submarine ship = new Submarine(shipCoordinates);

		assertEquals(true, testPlayer.addShip(ship));
	}

	@Test
	public void testAddShipTouching() {

		ShipCoordinates shipCoordinates = new ShipCoordinates(5, 5, 6, 5);
		Ship ship1 = new Submarine(shipCoordinates);
		assertEquals(true, testPlayer.addShip(ship1));

		ShipCoordinates shipCoordinates2 = new ShipCoordinates(5, 6, 6, 6);
		Ship ship2 = new Submarine(shipCoordinates2);
		assertEquals(false, testPlayer.addShip(ship2));
	}

	@Test
	public void testAddShipsNextToEachOther() {
		ShipCoordinates shipCoordinates = new ShipCoordinates(5, 5, 6, 5);
		Ship ship1 = new Submarine(shipCoordinates);
		assertEquals(true, testPlayer.addShip(ship1));

		ShipCoordinates shipCoordinates2 = new ShipCoordinates(8, 5, 8, 6);
		Ship ship2 = new Submarine(shipCoordinates2);
		assertEquals(true, testPlayer.addShip(ship2));
	}

	@Test
	public void testAddSuccessfullTry() {
		ShotCoordinates shotCoordinates = new ShotCoordinates(5, 5);
		Shot shot = new Shot(shotCoordinates);

		char[][] shipPositions = testPlayer.getShipPositions().getPositionOfShips();
		shipPositions[5][5] = 'S';

		testPlayer.addTry(shot);
		char[][] result = testPlayer.getBattlefieldDuringGame().getPlayingField();

		assertEquals('X', result[5][5]);
		assertEquals(1, testPlayer.getBattlefieldDuringGame().getNumberOfHits());
	}

	@Test
	public void testAddTryNoHit() {
		ShotCoordinates shotCoordinates = new ShotCoordinates(9, 9);
		Shot shot = new Shot(shotCoordinates);

		testPlayer.addTry(shot);
		char[][] result = testPlayer.getBattlefieldDuringGame().getPlayingField();

		assertEquals('O', result[9][9]);
	}

	@Test
	public void testAddTryTriedAlready() {
		ShotCoordinates shotCoordinates = new ShotCoordinates(5, 5);
		Shot shot = new Shot(shotCoordinates);

		char[][] result = testPlayer.getBattlefieldDuringGame().getPlayingField();
		result[5][5] = 'X';
		testPlayer.getBattlefieldDuringGame().setPlayingField(result);

		assertEquals(false, testPlayer.addTry(shot));

	}
}

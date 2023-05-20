package player;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;

import input.ShipCoordinates;
import input.ShotCoordinates;
import ship.Ship;
import shot.Shot;

public class TestPlayer {
	@Test
	public void testAddShip() {
		Player testPlayer = new Player("TestPlayer");
		ShipCoordinates shipCoordinates = new ShipCoordinates();
		shipCoordinates.setX1(5);
		shipCoordinates.setX2(6);
		shipCoordinates.setY1(5);
		shipCoordinates.setY2(5);
		Ship ship = new Ship(shipCoordinates);

		assertEquals(true, testPlayer.addShip(ship));
	}

	@Test
	public void testAddShipTouching() {
		Player testPlayer = new Player("TestPlayer");

		ShipCoordinates shipCoordinates = new ShipCoordinates();
		shipCoordinates.setX1(5);
		shipCoordinates.setX2(6);
		shipCoordinates.setY1(5);
		shipCoordinates.setY2(5);
		Ship ship1 = new Ship(shipCoordinates);
		assertEquals(true, testPlayer.addShip(ship1));

		shipCoordinates.setX1(5);
		shipCoordinates.setX2(6);
		shipCoordinates.setY1(6);
		shipCoordinates.setY2(6);
		Ship ship2 = new Ship(shipCoordinates);
		assertEquals(false, testPlayer.addShip(ship2));
	}

	@Test
	public void testAddShipsNextToEachOther() {
		Player testPlayer = new Player("TestPlayer");

		ShipCoordinates shipCoordinates = new ShipCoordinates();
		shipCoordinates.setX1(5);
		shipCoordinates.setX2(6);
		shipCoordinates.setY1(5);
		shipCoordinates.setY2(5);
		Ship ship1 = new Ship(shipCoordinates);
		assertEquals(true, testPlayer.addShip(ship1));

		shipCoordinates.setX1(8);
		shipCoordinates.setX2(8);
		shipCoordinates.setY1(5);
		shipCoordinates.setY2(6);
		Ship ship2 = new Ship(shipCoordinates);
		assertEquals(true, testPlayer.addShip(ship2));
	}

	@Test
	public void testAddSuccessfullTry() {
		Player testPlayer = new Player("TestPlayer");

		ShotCoordinates shotCoordinates = new ShotCoordinates();
		shotCoordinates.setX(5);
		shotCoordinates.setY(5);
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
		Player testPlayer = new Player("TestPlayer");

		ShotCoordinates shotCoordinates = new ShotCoordinates();
		shotCoordinates.setX(5);
		shotCoordinates.setY(5);
		Shot shot = new Shot(shotCoordinates);
		
		testPlayer.addTry(shot);
		char[][] result = testPlayer.getBattlefieldDuringGame().getPlayingField();

		assertEquals('O', result[5][5]);
	}
	
	@Test
	public void testAddTryTriedAlready() {
		Player testPlayer = new Player("TestPlayer");
		
		ShotCoordinates shotCoordinates = new ShotCoordinates();
		shotCoordinates.setX(5);
		shotCoordinates.setY(5);
		Shot shot = new Shot(shotCoordinates);
		
		char[][] result = testPlayer.getBattlefieldDuringGame().getPlayingField();
		result[5][5] = 'X';
		testPlayer.getBattlefieldDuringGame().setPlayingField(result);
		
		assertEquals(false, testPlayer.addTry(shot));
		
	}
}

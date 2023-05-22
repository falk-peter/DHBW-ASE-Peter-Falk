package input;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;

import adapters.InputConsole;
import domain.ShipCoordinates;
import domain.ShotCoordinates;

public class TestInputSource {
	@Test
	public void testGetShipCoordinates() {
		InputConsole inputSource = new InputConsole();
		String input = "0 0 1 0";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		Scanner scanner = new Scanner(in);
		inputSource.setScanner(scanner);

		ShipCoordinates result = inputSource.getCoordinatesForShip();

		assertEquals(0, result.getX1());
		assertEquals(0, result.getY1());
		assertEquals(1, result.getX2());
		assertEquals(0, result.getY2());
	}

	@Test
	public void testTransformShipCoordinates() {
		InputConsole inputSource = new InputConsole();
		ShipCoordinates result = inputSource.transformShipCoordinates("0 0 1 0");

		assertEquals(0, result.getX1());
		assertEquals(0, result.getY1());
		assertEquals(1, result.getX2());
		assertEquals(0, result.getY2());
	}

	@Test
	public void testGetCordinatesForShot() {
		InputConsole inputSource = new InputConsole();
		String input = "0 1";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		Scanner scanner = new Scanner(in);
		inputSource.setScanner(scanner);

		ShotCoordinates result = inputSource.getCordinatesForShot();
		
		assertEquals(0, result.getX());
		assertEquals(1, result.getY());
	}
	
	@Test
	public void testTransformShotCoordinates() {
		InputConsole inputSource = new InputConsole();
		ShotCoordinates result = inputSource.transformShotCoordinates("4 5");

		assertEquals(4, result.getX());
		assertEquals(5, result.getY());
	}
}

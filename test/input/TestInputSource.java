package input;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;

public class TestInputSource {
	@Test
	public void testGetShipCoordinates() {
		InputSource inputSource = new InputSource();
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
		InputSource inputSource = new InputSource();
		ShipCoordinates result = inputSource.transformShipCoordinates("0 0 1 0");

		assertEquals(0, result.getX1());
		assertEquals(0, result.getY1());
		assertEquals(1, result.getX2());
		assertEquals(0, result.getY2());
	}

	@Test
	public void testGetCordinatesForShot() {
		InputSource inputSource = new InputSource();
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
		InputSource inputSource = new InputSource();
		ShotCoordinates result = inputSource.transformShotCoordinates("4 5");

		assertEquals(4, result.getX());
		assertEquals(5, result.getY());
	}
}

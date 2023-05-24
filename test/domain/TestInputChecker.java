package domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TestInputChecker {
	@Test
	public void testCheckLength() {
		InputChecker inputChecker = new InputChecker();
		assertEquals(true, inputChecker.checkLength("0 0 0 0", 7));
	}
	
	@Test
	public void testCheckLengthFail() {
		InputChecker inputChecker = new InputChecker();
		assertEquals(false, inputChecker.checkLength("0 0 0 12", 7));
	}
	
	@Test
	public void testCheckFormatInput() {
		InputChecker inputChecker = new InputChecker();
		assertEquals(true, inputChecker.checkFormatInput("0 0 0 0"));
	}

	@Test
	public void testCheckFormatInputFail() {
		InputChecker inputChecker = new InputChecker();
		assertEquals(false, inputChecker.checkFormatInput("10 0  0"));
	}

	@Test
	public void testCheckNumeric() {
		InputChecker inputChecker = new InputChecker();
		assertEquals(true, inputChecker.checkNumeric("0 1 0 0"));
	}

	@Test
	public void testCheckNumericFail() {
		InputChecker inputChecker = new InputChecker();
		assertEquals(false, inputChecker.checkNumeric("0 a 0 0"));
	}
	
	@Test
	public void testCheckOrder() {
		InputChecker inputChecker = new InputChecker();
		assertEquals(true, inputChecker.checkOrder("0 0 1 0"));
	}
	
	@Test
	public void testCheckOrderFail() {
		InputChecker inputChecker = new InputChecker();
		assertEquals(false, inputChecker.checkOrder("2 0 1 0"));
	}
	
	@Test
	public void testCheckInLine() {
		InputChecker inputChecker = new InputChecker();
		assertEquals(true, inputChecker.checkInLine("0 0 1 0"));
	}
	
	@Test
	public void testCheckInLineFail() {
		InputChecker inputChecker = new InputChecker();
		assertEquals(false, inputChecker.checkInLine("0 0 1 1"));
	}
	
	@Test
	public void testCheckShipCooridnates() {
		InputChecker inputChecker = new InputChecker();
		assertEquals(true, inputChecker.checkShipCoordinates("0 0 1 0"));
	}
	
	@Test
	public void testCheckShotCooridnates() {
		InputChecker inputChecker = new InputChecker();
		assertEquals(true, inputChecker.checkShotCoordinates("0 0"));
	}
}

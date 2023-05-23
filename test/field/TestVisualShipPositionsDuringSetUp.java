package field;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import adapters.VisualShipPositionsDuringSetUp;
import domain.ShipPositions;

public class TestVisualShipPositionsDuringSetUp {
	@Test
	public void testVisualShipPositionsDuringSetUp() {
		VisualShipPositionsDuringSetUp shipPositions = new VisualShipPositionsDuringSetUp();
		char[][] result = shipPositions.getShipPositions();

		assertEquals('*', result[0][0]);
		assertEquals('*', result[14][28]);
		assertEquals('0', result[2][6]);
		assertEquals('9', result[2][24]);
		assertEquals('0', result[3][4]);
		assertEquals('9', result[12][4]);
		assertEquals('~', result[3][6]);
		assertEquals('~', result[12][24]);
	}

	@Test
	public void testUpdate() {
		VisualShipPositionsDuringSetUp visualShipPositions = new VisualShipPositionsDuringSetUp();
		ShipPositions shipPositions = new ShipPositions();

		char[][] updatedShipPositions = shipPositions.getPositionOfShips();
		boolean[][] updatedBlockedFields = shipPositions.getBlockedFields();

		updatedShipPositions[2][3] = 'S';
		updatedShipPositions[2][4] = 'S';
		updatedBlockedFields[2][3] = true;
		updatedBlockedFields[2][4] = true;
		updatedBlockedFields[1][4] = true;

		shipPositions.setBlockedFields(updatedBlockedFields);
		shipPositions.setPositionOfShips(updatedShipPositions);
		visualShipPositions.update(shipPositions);

		char[][] result = visualShipPositions.getShipPositions();
		
		assertEquals('X', result[4][14]);
		assertEquals('S', result[5][14]);
		assertEquals('S', result[5][12]);
	}
}

package field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestBattlefieldDuringGame {
	@Test
	public void testBattlefieldDuringGame() {
		BattlefieldDuringGame battlefield = new BattlefieldDuringGame();
		char[][] result = battlefield.getPlayingField();

		assertEquals('~', result[0][0]);
		assertEquals('~', result[9][9]);
	}

	@Test
	public void testAlreadyTried() {
		BattlefieldDuringGame battlefield = new BattlefieldDuringGame();
		char[][] result = battlefield.getPlayingField();
		result[5][5] = 'X';
		battlefield.setPlayingField(result);

		assertTrue(battlefield.alreadyTried(5, 5));
	}

	@Test
	public void testNotAlreadyTried() {
		BattlefieldDuringGame battlefield = new BattlefieldDuringGame();

		assertFalse(battlefield.alreadyTried(5, 5));
	}

	@Test
	public void testUpdateWithShotFail() {
		BattlefieldDuringGame battlefield = new BattlefieldDuringGame();
		char[][] result = battlefield.getPlayingField();
		result[5][5] = 'X';
		battlefield.setPlayingField(result);
		battlefield.updateWithShot(5, 5, 'O');
		
		result = battlefield.getPlayingField();
		assertEquals('O', result[5][5]);
	}

	@Test
	public void testUpdateWithShotSuccess() {
		BattlefieldDuringGame battlefield = new BattlefieldDuringGame();
		battlefield.updateWithShot(5, 5, 'X');

		char[][] result = battlefield.getPlayingField();
		assertEquals('X', result[5][5]);
	}
}

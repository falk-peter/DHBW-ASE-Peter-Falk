package domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import adapters.VisualBattlefieldDuringGame;

public class TestBattlefieldDuringGame {
	BattlefieldDuringGame battlefield;

	@Before
	public void setUp() {
		VisualBattlefieldDuringGame visual = new VisualBattlefieldDuringGame();
		battlefield = new BattlefieldDuringGame(visual);
	}

	@Test
	public void testBattlefieldDuringGameInitializing() {
		char[][] result = battlefield.getPlayingField();

		assertEquals('~', result[0][0]);
		assertEquals('~', result[9][9]);
	}

	@Test
	public void testAlreadyTried() {
		char[][] result = battlefield.getPlayingField();
		result[5][5] = 'X';
		battlefield.setPlayingField(result);

		assertTrue(battlefield.alreadyTried(5, 5));
	}

	@Test
	public void testNotAlreadyTried() {
		assertFalse(battlefield.alreadyTried(5, 5));
	}

	@Test
	public void testUpdateWithShotFail() {
		char[][] result = battlefield.getPlayingField();
		result[5][5] = 'X';
		battlefield.setPlayingField(result);
		battlefield.updateWithShot(5, 5, 'O');

		result = battlefield.getPlayingField();
		assertEquals('O', result[5][5]);
	}

	@Test
	public void testUpdateWithShotSuccess() {
		battlefield.updateWithShot(5, 5, 'X');

		char[][] result = battlefield.getPlayingField();
		assertEquals('X', result[5][5]);
	}
}

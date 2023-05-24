package adapters;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import domain.BattlefieldDuringGame;

public class TestVisualBattlefieldDuringGame {
	@Test
	public void testVisualBattlefieldDuringGameInitialization() {
		VisualBattlefieldDuringGame battlefield = new VisualBattlefieldDuringGame();
		char[][] result = battlefield.getPlayingField();

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
		VisualBattlefieldDuringGame visualBattlefield = new VisualBattlefieldDuringGame();
		BattlefieldDuringGame battlefield = new BattlefieldDuringGame(visualBattlefield);

		char[][] updatedField = new char[10][10];
		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				updatedField[y][x] = '~';
			}
		}
		updatedField[5][5] = 'X';

		battlefield.setPlayingField(updatedField);
		visualBattlefield.update(battlefield);
		char[][] result = visualBattlefield.getPlayingField();

		assertEquals('X', result[8][16]);
	}
}

package field;

public class VisualBattlefieldDuringGame {
	private final int xAxis = 29;
	private final int yAxis = 15;
	private char visualPlayingField[][];
	private final char STAR = '*';
	private final char WATER = '~';

	public VisualBattlefieldDuringGame() {
		visualPlayingField = new char[yAxis][xAxis];
		initialize();
	}

	public void initialize() {
		initializeSize();
		initializeStarBorders();
		initializeAxisDescription();
		initializeXAxis();
		initializeYAxis();
		initializeWater();
	}

	public void initializeSize() {
		for (int y = 0; y < yAxis; y++) {
			for (int x = 0; x < xAxis; x++) {
				visualPlayingField[y][x] = ' ';
			}
		}
	}

	public void initializeStarBorders() {
		for (int x = 0; x < xAxis; x = x + 2) {
			visualPlayingField[0][x] = STAR;
		}

		for (int x = 0; x < xAxis; x = x + 2) {
			visualPlayingField[yAxis - 1][x] = STAR;
		}

		for (int y = 0; y < yAxis; y++) {
			visualPlayingField[y][0] = STAR;
		}

		for (int y = 0; y < yAxis; y++) {
			visualPlayingField[y][xAxis - 1] = STAR;
		}
	}

	public void initializeAxisDescription() {
		visualPlayingField[1][15] = 'x';
		visualPlayingField[8][2] = 'y';
	}

	public void initializeXAxis() {
		int axisNumber = 0;
		for (int x = 6; x < 25; x = x + 2) {
			visualPlayingField[2][x] = (char) (axisNumber + '0');
			axisNumber++;
		}
	}

	public void initializeYAxis() {
		int axisNumber = 0;
		for (int y = 3; y < 13; y++) {
			visualPlayingField[y][4] = (char) (axisNumber + '0');
			axisNumber++;
		}
	}

	public void initializeWater() {
		for (int y = 3; y < 13; y++) {
			for (int x = 6; x < 25; x = x + 2) {
				visualPlayingField[y][x] = WATER;
			}
		}
	}

	public void update(BattlefieldDuringGame battlefield) {
		int xCurrentTries = 0, yCurrentTries = 0;
		char[][] currentTries = battlefield.getPlayingField();
		for (int y = 3; y < 13; y++) {
			for (int x = 6; x < 25; x = x + 2) {
				visualPlayingField[y][x] = currentTries[yCurrentTries][xCurrentTries];
				xCurrentTries++;
			}
			yCurrentTries++;
			xCurrentTries = 0;
		}
	}

	public void print() {
		for (int y = 0; y < yAxis; y++) {
			for (int x = 0; x < xAxis; x++) {
				System.out.print(visualPlayingField[y][x]);
			}
			System.out.println();
		}
	}

	public int getxAxis() {
		return xAxis;
	}

	public int getyAxis() {
		return yAxis;
	}

	public char[][] getPlayingField() {
		return visualPlayingField;
	}

	public void setPlayingField(char[][] playingField) {
		this.visualPlayingField = playingField;
	}
}
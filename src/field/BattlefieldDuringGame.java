package field;

public class BattlefieldDuringGame {
	private char[][] playingField;
	private final int xAxis = 10;
	private final int yAxis = 10;
	private final char WATER = '~';
	private final char HIT = 'X';
	private final char MISS = 'O';
	private int numberOfHits = 0;

	public BattlefieldDuringGame() {
		playingField = new char[yAxis][xAxis];
		initialize();
	}

	public void initialize() {
		for (int y = 0; y < yAxis; y++) {
			for (int x = 0; x < xAxis; x++) {
				playingField[y][x] = '~';
			}
		}
	}

	public void updateWithShot(int x, int y, char resultOfShot) {
		if (resultOfShot == HIT) {
			System.out.println("HIT at (" + x + "/" + y + ")!");
			playingField[y][x] = resultOfShot;
			numberOfHits++;
		}
		
		if (resultOfShot == MISS) {
			System.out.println("MISS at (" + x + "/" + y + ")!");
			playingField[y][x] = resultOfShot;
		}
	}

	public boolean alreadyTried(int x, int y) {
		 return playingField[y][x] != WATER;
	}

	public void setPlayingField(char[][] playingField) {
		this.playingField = playingField;
	}

	public char[][] getPlayingField() {
		return playingField;
	}
	
	public int getNumberOfHits() {
		return numberOfHits;
	}
	
	public void setNumberOfHits(int numberOfHits) {
		this.numberOfHits = numberOfHits;
	}
	
	public void print() {
		for (int y = 0; y < yAxis; y++) {
			for (int x = 0; x < xAxis; x++) {
				System.out.print(playingField[y][x]);
			}
			System.out.println();
		}
	}
}
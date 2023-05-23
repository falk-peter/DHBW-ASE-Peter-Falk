package domain;

import interfaces.VisualBattlefieldInterface;

public class BattlefieldDuringGame {
	private char[][] playingField;
	private final int xAxis = 10;
	private final int yAxis = 10;
	private int numberOfHits = 0;
	
	private VisualBattlefieldInterface visualBattlefield;
	
	private final char WATER = '~';
	private final char HIT = 'X';
	private final char MISS = 'O';

	public BattlefieldDuringGame(VisualBattlefieldInterface visualBattlefield) {
		this.visualBattlefield = visualBattlefield;
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
		
		visualBattlefield.update(this);
		print();
	}

	public boolean alreadyTried(int x, int y) {
		 return playingField[y][x] != WATER;
	}
	
	public void print() {
		visualBattlefield.print();
	}
	
	/*
	 * set- and get-section
	 */

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
	
	public VisualBattlefieldInterface getVisualBattlefield() {
		return visualBattlefield;
	}

	public void setVisualBattlefield(VisualBattlefieldInterface visualBattlefield) {
		this.visualBattlefield = visualBattlefield;
	}
}
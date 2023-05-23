package application;

import domain.BattlefieldDuringGame;
import domain.Ship;
import domain.ShipPositions;
import domain.Shot;
import interfaces.VisualBattlefieldInterface;
import interfaces.VisualShipPositionsInterface;

public class Player {
	private String name;
	private BattlefieldDuringGame battlefieldDuringGame;
	private ShipPositions shipPositions;

	public Player(String name, VisualShipPositionsInterface visualShipPositions, VisualBattlefieldInterface visualBattlefield) {
		this.name = name;
		this.battlefieldDuringGame = new BattlefieldDuringGame(visualBattlefield);
		this.shipPositions = new ShipPositions(visualShipPositions);
	}

	public boolean addShip(Ship ship) {
		if (shipPositions.addShip(ship)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean addTry(Shot shot) {
		if (battlefieldDuringGame.alreadyTried(shot.getX(), shot.getY())) {
			System.out.println("Already shot there! Try again with new cooridnates!");
			return false;
		}
		
		if (shipPositions.isHit(shot.getX(), shot.getY())) {
			battlefieldDuringGame.updateWithShot(shot.getX(), shot.getY(), 'X');
		}
		else {
			battlefieldDuringGame.updateWithShot(shot.getX(), shot.getY(), 'O');
		}
		
		return true;
	}

	public boolean allShipsDestroyed() {
		return (battlefieldDuringGame.getNumberOfHits() == 19);
	}

	/*
	 * ------------------------- getter and setter methods -------------------------
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BattlefieldDuringGame getBattlefieldDuringGame() {
		return battlefieldDuringGame;
	}

	public void setBattlefieldDuringGame(BattlefieldDuringGame battlefieldDuringGame) {
		this.battlefieldDuringGame = battlefieldDuringGame;
	}

	public ShipPositions getShipPositions() {
		return shipPositions;
	}

	public void setShipPositions(ShipPositions shipPositions) {
		this.shipPositions = shipPositions;
	}
}

package player;

import field.BattlefieldDuringGame;
import field.ShipPositions;
import field.VisualBattlefieldDuringGame;
import field.VisualShipPositionsDuringSetUp;
import ship.Ship;
import shot.Shot;

public class Player {
	private String name;
	private VisualBattlefieldDuringGame visualBattlefieldDuringGame;
	private BattlefieldDuringGame battlefieldDuringGame;
	private ShipPositions shipPositions;
	private VisualShipPositionsDuringSetUp visualShipPositionsDuringSetUp;

	public Player(String name) {
		this.name = name;
		this.visualBattlefieldDuringGame = new VisualBattlefieldDuringGame();
		this.battlefieldDuringGame = new BattlefieldDuringGame();
		this.shipPositions = new ShipPositions();
		this.visualShipPositionsDuringSetUp = new VisualShipPositionsDuringSetUp();
	}

	public boolean addShip(Ship ship) {
		if (shipPositions.addShip(ship)) {
			visualShipPositionsDuringSetUp.update(shipPositions);
			visualShipPositionsDuringSetUp.print();
			return true;
		} else {
			visualShipPositionsDuringSetUp.print();
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
		
		visualBattlefieldDuringGame.update(battlefieldDuringGame);
		visualBattlefieldDuringGame.print();
		
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

	public VisualBattlefieldDuringGame getVisualBattlefieldDuringGame() {
		return visualBattlefieldDuringGame;
	}

	public void setVisualBattlefieldDuringGame(VisualBattlefieldDuringGame visualBattlefieldDuringGame) {
		this.visualBattlefieldDuringGame = visualBattlefieldDuringGame;
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

	public VisualShipPositionsDuringSetUp getVisualShipPositionsDuringSetUp() {
		return visualShipPositionsDuringSetUp;
	}

	public void setVisualShipPositionsDuringSetUp(VisualShipPositionsDuringSetUp visualShipPositionsDuringSetUp) {
		this.visualShipPositionsDuringSetUp = visualShipPositionsDuringSetUp;
	}
}

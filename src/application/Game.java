package application;

import domain.Battleship;
import domain.Cruiser;
import domain.Destroyer;
import domain.ShipCoordinates;
import domain.Shot;
import domain.ShotCoordinates;
import domain.Submarine;
import interfaces.InputInterface;
import interfaces.VisualBattlefieldInterface;
import interfaces.VisualShipPositionsInterface;

public class Game {
	private Player playerA;
	private Player playerB;
	private char playersTurn;
	
	private InputInterface input;

	public Game(InputInterface input, VisualShipPositionsInterface visualShipPositions1, VisualShipPositionsInterface visualShipPositions2, VisualBattlefieldInterface visualBattlefield1, VisualBattlefieldInterface visualBattlefield2) {
		this.input = input;
		this.playerA = new Player("PlayerA", visualShipPositions1, visualBattlefield1);
		this.playerB = new Player("PlayerB", visualShipPositions2, visualBattlefield2);
	}

	public void startGameSetUp() {
		System.out.println("Let's play a game of sinking ships!");
		System.out.println("Let's start with your both names!");
		addPlayers(input.getName(), input.getName());

		System.out.println("Alright! Let's continue with setting up your fleets! " + playerA.getName()
				+ " is starting! " + playerB.getName() + " should not look at the screen!");
		setUpShips(playerA);

		clearConsole();

		System.out.println("That's it for " + playerA.getName() + "! Change player to set up the fleet of "
				+ playerB.getName() + "!");
		setUpShips(playerB);

		clearConsole();

		System.out.println("Both players have successfully placed all their ships! Let's start the game!");
		System.out.println("Every turn each player gets one shot until one of you destroyed all ships!");
		startGame();
	}

	public void addPlayers(String nameA, String nameB) {
		playerA.setName(nameA);
		playerB.setName(nameB);
	}

	public void setUpShips(Player player) {
		boolean successfullSetUp = false;

		System.out.println("First, place a battleship with a size of five blocks!");
		player.getShipPositions().print();
		
		while (!successfullSetUp)
			successfullSetUp = setUpBattleship(input.getCoordinatesForShip(), player);

		successfullSetUp = false;

		System.out.println("Now, set up one cruiser with a size of four blocks!");
		while (!successfullSetUp)
			successfullSetUp = setUpCruiser(input.getCoordinatesForShip(), player);

		successfullSetUp = false;

		System.out.println("Next, place two destroyers of size 3.");
		while (!successfullSetUp)
			successfullSetUp = setUpDestroyers(player);

		successfullSetUp = false;

		System.out.println("Finally, place two submarines of size 2.");
		while (!successfullSetUp)
			successfullSetUp = setUpSubmarines(player);
	}

	public boolean setUpBattleship(ShipCoordinates cordinatesOfBattleship, Player player) {
		if (!cordinatesOfBattleship.checkIfSizeCorrect(5))
			return false;

		Battleship battleship = new Battleship(cordinatesOfBattleship);
		if (!player.getShipPositions().isBlocked(battleship)) {
			player.addShip(battleship);
		} else
			return false;

		return true;
	}

	public boolean setUpCruiser(ShipCoordinates coordinatesOfCruiser, Player player) {
		if (!coordinatesOfCruiser.checkIfSizeCorrect(4))
			return false;

		Cruiser cruiser = new Cruiser(coordinatesOfCruiser);
		if (!player.getShipPositions().isBlocked(cruiser))
			player.addShip(cruiser);
		else
			return false;

		return true;
	}

	public boolean setUpDestroyers(Player player) {
		int numberOfDestroyers = 0;

		while (numberOfDestroyers < 2)
			if (setUpDestroyer(input.getCoordinatesForShip(), player))
				numberOfDestroyers++;

		return true;
	}

	public boolean setUpDestroyer(ShipCoordinates coordinatesOfDestroyer, Player player) {
		if (!coordinatesOfDestroyer.checkIfSizeCorrect(3))
			return false;

		Destroyer destroyer = new Destroyer(coordinatesOfDestroyer);
		if (!player.getShipPositions().isBlocked(destroyer))
			player.addShip(destroyer);
		else
			return false;

		return true;
	}

	public boolean setUpSubmarines(Player player) {
		int numberOfSubmarines = 0;

		while (numberOfSubmarines < 2)
			if (setUpSubmarine(input.getCoordinatesForShip(), player))
				numberOfSubmarines++;

		return true;
	}

	public boolean setUpSubmarine(ShipCoordinates cordinatesOfSubmarine, Player player) {
		if (!cordinatesOfSubmarine.checkIfSizeCorrect(2))
			return false;

		Submarine submarine = new Submarine(cordinatesOfSubmarine);
		if (!player.getShipPositions().isBlocked(submarine))
			player.addShip(submarine);
		else
			return false;

		return true;
	}

	public void startGame() {
		playersTurn = determineWhoStarts(Math.random(), Math.random());
		boolean gameFinished = false;

		while (!gameFinished) {
			if (playersTurn == 'A') {
				System.out.println(playerA.getName() + "'s turn!");
				commitTurnOn(playerB);
			} else {
				System.out.println(playerB.getName() + "'s turn!");
				commitTurnOn(playerA);
			}

			if (gameIsOver())
				gameFinished = true;
			else {
				changeTurn();
				for (int i = 0; i < 5; ++i)
					System.out.println();
			}
		}

		endGame();
	}

	public void commitTurnOn(Player player) {
		player.getBattlefieldDuringGame().print();
		
		boolean successfullTurn = false;
		
		while(!successfullTurn) {
			ShotCoordinates shotCoordinates = input.getCordinatesForShot();
			Shot shot = new Shot(shotCoordinates);
			successfullTurn = player.addTry(shot);
		}
	}

	public void endGame() {
		System.out.println("The game is over!");
		String winner;

		if (playersTurn == 'A')
			winner = playerA.getName();
		else
			winner = playerB.getName();

		System.out.println("Congratulation " + winner + "! You have won!");
	}

	public boolean gameIsOver() {
		if (playersTurn == 'A')
			return playerB.allShipsDestroyed();
		else
			return playerA.allShipsDestroyed();
	}

	public void changeTurn() {
		if (playersTurn == 'A')
			playersTurn = 'B';
		else
			playersTurn = 'A';
	}

	public char determineWhoStarts(double numberForA, double numberForB) {
		if (numberForA > numberForB)
			return 'A';
		else
			return 'B';
	}

	/*
	 * set- and get-section
	 */

	public Player getPlayerA() {
		return playerA;
	}

	public void setPlayerA(Player playerA) {
		this.playerA = playerA;
	}

	public Player getPlayerB() {
		return playerB;
	}

	public void setPlayerB(Player playerB) {
		this.playerB = playerB;
	}

	private void clearConsole() {
		for (int i = 0; i < 50; ++i)
			System.out.println();
	}

	public char getPlayersTurn() {
		return playersTurn;
	}

	public void setPlayersTurn(char playersTurn) {
		this.playersTurn = playersTurn;
	}

	public InputInterface getInput() {
		return input;
	}

	public void setInput(InputInterface input) {
		this.input = input;
	}
}

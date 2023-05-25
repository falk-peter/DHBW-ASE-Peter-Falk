package application;

import domain.Battleship;
import domain.Cruiser;
import domain.Destroyer;
import domain.Ship;
import domain.Shot;
import domain.ShotCoordinates;
import domain.Submarine;
import interfaces.InputInterface;
import interfaces.OutputInterface;
import interfaces.VisualBattlefieldInterface;
import interfaces.VisualShipPositionsInterface;

public class Game {
	private Player playerA;
	private Player playerB;
	private char playersTurn;

	private InputInterface input;
	private OutputInterface output;

	public Game(OutputInterface output, InputInterface input, VisualShipPositionsInterface visualShipPositions1,
			VisualShipPositionsInterface visualShipPositions2, VisualBattlefieldInterface visualBattlefield1,
			VisualBattlefieldInterface visualBattlefield2) {
		this.output = output;
		this.input = input;
		this.playerA = new Player("PlayerA", visualShipPositions1, visualBattlefield1);
		this.playerB = new Player("PlayerB", visualShipPositions2, visualBattlefield2);
	}

	public void setUpGame() {
		output.printLine("Let's play a game of sinking ships!");
		output.printLine("Let's start with your both names!");

		setUpPlayerNames();
		setUpShips();

		output.clear();

		output.printLine("Both players have successfully placed all their ships! Let's start the game!");
		output.printLine("Every turn each player gets one shot until one of you destroyed all ships!");
		startGame();
	}

	public void setUpPlayerNames() {
		output.printLine("Enter your name Player A:");
		playerA.setName(input.getName());
		output.printLine("Enter your name Player B:");
		playerB.setName(input.getName());
	}

	public void setUpShips() {
		output.printLine("Alright! Let's continue with setting up your fleets! " + playerA.getName() + " is starting! "
				+ playerB.getName() + " should not look at the screen!");
		setUpShipsFor(playerA);

		output.clear();

		output.printLine("That's it for " + playerA.getName() + "! Change player to set up the fleet of "
				+ playerB.getName() + "!");
		setUpShipsFor(playerB);

		output.clear();
	}

	public void setUpShipsFor(Player player) {
		boolean successfullSetUp = false;

		output.printLine("First, place a battleship with a size of five blocks!");
		player.getShipPositions().print();

		while (!successfullSetUp) {
			output.askForShipCoordinates();
			Battleship battleship = new Battleship(input.getCoordinatesForShip());
			successfullSetUp = setUpShip(battleship, player);
		}

		successfullSetUp = false;

		output.printLine("Now, set up one cruiser with a size of four blocks!");
		while (!successfullSetUp) {
			output.askForShipCoordinates();
			Cruiser cruiser = new Cruiser(input.getCoordinatesForShip());
			successfullSetUp = setUpShip(cruiser, player);
		}

		successfullSetUp = false;

		output.printLine("Next, place two destroyers of size 3.");
		while (!successfullSetUp) {
			successfullSetUp = setUpDestroyers(player);
		}

		successfullSetUp = false;

		output.printLine("Finally, place two submarines of size 2.");
		while (!successfullSetUp)
			successfullSetUp = setUpSubmarines(player);
	}

	public boolean setUpShip(Ship ship, Player player) {
		if (!ship.sizeCorrect()) {
			output.printLine("Error: Wrong size! Try again!");
			return false;
		}
		
		if (!player.getShipPositions().isBlocked(ship)) {
			player.addShip(ship);
			return true;
		} else 
			return false;
	}

	public boolean setUpDestroyers(Player player) {
		int numberOfDestroyers = 0;

		while (numberOfDestroyers < 2) {
			output.askForShipCoordinates();
			Destroyer destroyer = new Destroyer(input.getCoordinatesForShip());
			if (setUpShip(destroyer, player))
				numberOfDestroyers++;
		}

		return true;
	}

	public boolean setUpSubmarines(Player player) {
		int numberOfSubmarines = 0;

		while (numberOfSubmarines < 2) {
			output.askForShipCoordinates();
			Submarine submarine = new Submarine(input.getCoordinatesForShip());
			if (setUpShip(submarine, player))
				numberOfSubmarines++;
		}

		return true;
	}

	public void startGame() {
		playersTurn = determineWhoStarts(Math.random(), Math.random());
		boolean gameFinished = false;

		while (!gameFinished) {
			if (playersTurn == 'A') {
				output.printLine(playerA.getName() + "'s turn!");
				commitTurnOn(playerB);
			} else {
				output.printLine(playerB.getName() + "'s turn!");
				commitTurnOn(playerA);
			}

			if (gameIsOver())
				gameFinished = true;
			else {
				changeTurn();
				for (int i = 0; i < 5; ++i)
					output.printLine("");
			}
		}

		endGame();
	}

	public void commitTurnOn(Player player) {
		player.getBattlefieldDuringGame().print();

		boolean successfullTurn = false;

		while (!successfullTurn) {
			output.printLine("Enter the desired coordinates in format: x y");
			ShotCoordinates shotCoordinates = input.getCordinatesForShot();
			Shot shot = new Shot(shotCoordinates);
			successfullTurn = player.addTry(shot);

			if (successfullTurn == false) {
				output.printLine("Already shot there! Try again with new cooridnates!");
			}
		}
	}

	public void endGame() {
		output.printLine("The game is over!");
		String winner;
 
		if (playersTurn == 'A')
			winner = playerA.getName();
		else
			winner = playerB.getName();

		output.printLine("Congratulation " + winner + "! You have won!");
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

package application;

import domain.Battleship;
import domain.BattleshipFactory;
import domain.Cruiser;
import domain.CruiserFactory;
import domain.Destroyer;
import domain.DestroyerFactory;
import domain.Ship;
import domain.Shot;
import domain.ShotCoordinates;
import domain.Submarine;
import domain.SubmarineFactory;
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

		setUpPlayersWithNames();
		setUpShipsForBothPlayers();

		output.clear();

		output.printLine("Both players have successfully placed all their ships! Let's start the game!");
		output.printLine("Every turn each player gets one shot until one of you destroyed all ships!");
		startGame();
	}

	protected void setUpPlayersWithNames() {
		output.printLine("Enter your name Player A:");
		playerA.setName(input.getName());
		output.printLine("Enter your name Player B:");
		playerB.setName(input.getName());
	}

	protected void setUpShipsForBothPlayers() {
		output.printLine("Alright! Let's continue with setting up your fleets! " + playerA.getName() + " is starting! "
				+ playerB.getName() + " should not look at the screen!");
		setUpShipsFor(playerA);

		output.clear();

		output.printLine("That's it for " + playerA.getName() + "! Change player to set up the fleet of "
				+ playerB.getName() + "!");
		setUpShipsFor(playerB);

		output.clear();
	}

	protected void setUpShipsFor(Player player) {
		player.getShipPositions().print();
		output.printLine("First, place a battleship with a size of five blocks!");
		setUpBattleshipFor(player);
		
		output.printLine("Now, set up one cruiser with a size of four blocks!");
		setUpCruiserFor(player);
		
		output.printLine("Next, place two destroyers of size 3.");
		setUpDestroyersFor(player);
		
		output.printLine("Finally, place two submarines of size 2.");
		setUpSubmarinesFor(player);
	}

	protected void setUpBattleshipFor(Player player) {
		output.askForShipCoordinates();
		
		BattleshipFactory shipFactory = new BattleshipFactory();
		Battleship battleship = shipFactory.createShip(input.getCoordinatesForShip());
		
		if (!tryAddShipToPlayer(battleship, player))
			setUpBattleshipFor(player);
	}

	protected void setUpCruiserFor(Player player) {
		output.askForShipCoordinates();
		
		CruiserFactory shipFactory = new CruiserFactory();
		Cruiser cruiser = shipFactory.createShip(input.getCoordinatesForShip());
		
		if (!tryAddShipToPlayer(cruiser, player))
			setUpBattleshipFor(player);
	}
	
	protected void setUpDestroyersFor(Player player) {
		int numberOfDestroyers = 0;
		DestroyerFactory shipFactory = new DestroyerFactory();
		
		while (numberOfDestroyers < 2) {
			output.askForShipCoordinates();
			Destroyer destroyer = shipFactory.createShip(input.getCoordinatesForShip());
			if (tryAddShipToPlayer(destroyer, player))
				numberOfDestroyers++;
		}
	}
	
	protected void setUpSubmarinesFor(Player player) {
		int numberOfSubmarines = 0;
		SubmarineFactory shipFactory = new SubmarineFactory();
		
		while (numberOfSubmarines < 2) {
			output.askForShipCoordinates();
			Submarine submarine = shipFactory.createShip(input.getCoordinatesForShip());
			if (tryAddShipToPlayer(submarine, player))
				numberOfSubmarines++;
		}
	}

	protected boolean tryAddShipToPlayer(Ship ship, Player player) {
		if (!ship.sizeCorrect()) {
			output.printLine("Error: Wrong size! Try again!");
			return false;
		}

		return player.addShip(ship);
	}

	protected void startGame() {
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

	protected void commitTurnOn(Player player) {
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

	protected void endGame() {
		output.printLine("The game is over!");
		String winner;

		if (playersTurn == 'A')
			winner = playerA.getName();
		else
			winner = playerB.getName();

		output.printLine("Congratulation " + winner + "! You have won!");
	}

	protected boolean gameIsOver() {
		if (playersTurn == 'A')
			return playerB.allShipsDestroyed();
		else
			return playerA.allShipsDestroyed();
	}

	protected void changeTurn() {
		if (playersTurn == 'A')
			playersTurn = 'B';
		else
			playersTurn = 'A';
	}

	protected char determineWhoStarts(double numberForA, double numberForB) {
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

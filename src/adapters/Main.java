package adapters;

import game.Game;
import interfaces.InputInterface;

public class Main {
	public static void main(String[] args) {
		InputInterface input = new InputConsole();
		Game game = new Game(input);
		game.startGameSetUp();
	}
}

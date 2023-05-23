package adapters;

import application.Game;
import interfaces.InputInterface;
import interfaces.VisualBattlefieldInterface;
import interfaces.VisualShipPositionsInterface;

public class Main {
	public static void main(String[] args) {
		InputInterface input = new InputConsole();
		VisualBattlefieldInterface visualBattlefieldInterfacePlayerA = new VisualBattlefieldDuringGame();
		VisualBattlefieldInterface visualBattlefieldInterfacePlayerB = new VisualBattlefieldDuringGame();
		VisualShipPositionsInterface visualShipPositionsInterfacePlayerA = new VisualShipPositionsDuringSetUp();
		VisualShipPositionsInterface visualShipPositionsInterfacePlayerB = new VisualShipPositionsDuringSetUp();

		Game game = new Game(input, visualShipPositionsInterfacePlayerA, visualShipPositionsInterfacePlayerB,
				visualBattlefieldInterfacePlayerA, visualBattlefieldInterfacePlayerB);
		game.startGameSetUp();
	}
}

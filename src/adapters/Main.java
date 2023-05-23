package adapters;

import application.Game;
import interfaces.InputInterface;
import interfaces.OutputInterface;
import interfaces.VisualBattlefieldInterface;
import interfaces.VisualShipPositionsInterface;

public class Main {
	public static void main(String[] args) {
		OutputInterface output = new OutputConsole();
		InputInterface input = new InputConsole();
		VisualBattlefieldInterface visualBattlefieldInterfacePlayerA = new VisualBattlefieldDuringGame();
		VisualBattlefieldInterface visualBattlefieldInterfacePlayerB = new VisualBattlefieldDuringGame();
		VisualShipPositionsInterface visualShipPositionsInterfacePlayerA = new VisualShipPositionsDuringSetUp();
		VisualShipPositionsInterface visualShipPositionsInterfacePlayerB = new VisualShipPositionsDuringSetUp();

		Game game = new Game(output, input, visualShipPositionsInterfacePlayerA, visualShipPositionsInterfacePlayerB,
				visualBattlefieldInterfacePlayerA, visualBattlefieldInterfacePlayerB);
		game.setUpGame();
	}
}

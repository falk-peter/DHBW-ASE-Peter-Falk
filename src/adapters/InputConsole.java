package adapters;

import java.util.Scanner;

import domain.InputChecker;
import domain.ShipCoordinates;
import domain.ShotCoordinates;
import interfaces.InputInterface;

public class InputConsole implements InputInterface {
	Scanner scanner;
	InputChecker inputChecker;

	public InputConsole() {
		this.scanner = new Scanner(System.in);
		this.inputChecker = new InputChecker();
	}

	public String getName() {
		return scanner.nextLine();
	}

	public ShipCoordinates getCoordinatesForShip() {
		String input = scanner.nextLine();

		if (inputChecker.checkShipCoordinates(input)) {
			ShipCoordinates shipCoordinates = transformShipCoordinates(input);
			return shipCoordinates;
		} else {
			return getCoordinatesForShip();
		}
	}

	protected ShipCoordinates transformShipCoordinates(String input) {
		int x1 = (Character.getNumericValue(input.charAt(0)));
		int y1 = (Character.getNumericValue(input.charAt(2)));
		int x2 = (Character.getNumericValue(input.charAt(4)));
		int y2 = (Character.getNumericValue(input.charAt(6)));
		ShipCoordinates shipCoordinates = new ShipCoordinates(x1, y1, x2, y2);

		return shipCoordinates;
	} 

	public ShotCoordinates getCordinatesForShot() {
		String input = scanner.nextLine();

		if (inputChecker.checkShotCoordinates(input)) {
			ShotCoordinates shotCoordinates = transformShotCoordinates(input);
			return shotCoordinates;
		} else {
			return getCordinatesForShot();
		}
	}

	protected ShotCoordinates transformShotCoordinates(String input) {
		int x = Character.getNumericValue(input.charAt(0));
		int y = Character.getNumericValue(input.charAt(2));
		ShotCoordinates shotCoordinates = new ShotCoordinates(x, y);

		return shotCoordinates;
	}
 
	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
}

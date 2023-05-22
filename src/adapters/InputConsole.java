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
		System.out.println("Enter your name:");
		return scanner.nextLine();
	}

	public ShipCoordinates getCoordinatesForShip() {
		System.out.println("Enter the desired coordinates in format: startX startY endX endY");
		String input = scanner.nextLine();

		if (inputChecker.checkShipCoordinates(input)) {
			ShipCoordinates shipCoordinates = transformShipCoordinates(input);
			return shipCoordinates;
		} else {
			return getCoordinatesForShip();
		}
	}

	public ShipCoordinates transformShipCoordinates(String input) {
		ShipCoordinates shipCoordinates = new ShipCoordinates();
		shipCoordinates.setX1(Character.getNumericValue(input.charAt(0)));
		shipCoordinates.setY1(Character.getNumericValue(input.charAt(2)));
		shipCoordinates.setX2(Character.getNumericValue(input.charAt(4)));
		shipCoordinates.setY2(Character.getNumericValue(input.charAt(6)));

		return shipCoordinates;
	} 

	public ShotCoordinates getCordinatesForShot() {
		System.out.println("Enter the desired coordinates in format: x y");
		String input = scanner.nextLine();

		if (inputChecker.checkShotCoordinates(input)) {
			ShotCoordinates shotCoordinates = transformShotCoordinates(input);
			return shotCoordinates;
		} else {
			return getCordinatesForShot();
		}
	}

	public ShotCoordinates transformShotCoordinates(String input) {
		ShotCoordinates shotCoordinates = new ShotCoordinates();
		shotCoordinates.setX(Character.getNumericValue(input.charAt(0)));
		shotCoordinates.setY(Character.getNumericValue(input.charAt(2)));

		return shotCoordinates;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
}

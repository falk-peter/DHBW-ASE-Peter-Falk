package domain;

public class InputChecker {

	public InputChecker() {
	}

	public boolean checkShipCoordinates(String input) {
		if (!checkLength(input, 7)) {
			System.out.println("Error: Wrong format! Stick to: x1 y1 x2 y2! Example: 0 0 1 0");
			return false;
		}

		if (!checkFormatInput(input)) {
			System.out.println("Error: Wrong format! Stick to: x1 y1 x2 y2! Example: 0 0 1 0");
			return false;
		}

		if (!checkNumeric(input)) {
			System.out.println("Error: One of the coordinates is not numeric!");
			return false;
		}

		if (!checkInLine(input)) {
			System.out.println("Error: Coordinates must be in one line!");
			return false;
		}

		if (!checkOrder(input)) {
			System.out.println(
					"Error: Coordinates must be in the right order! x1 and y1 must be smaller or same as x2 and y2!");
			return false;
		}

		return true;
	}

	public boolean checkShotCoordinates(String input) {
		if (!checkLength(input, 3)) {
			System.out.println("Error: Wrong format! Stick to: x y! Example: 1 1");
			return false;
		}
		
		if(!checkFormatInput(input)) {
			System.out.println("Error: Wrong format! Stick to: x y! Example: 1 1");
			return false;
		}
		
		if(!checkNumeric(input)) {
			System.out.println("Error: One of the coordinates is numeric!");
			return false;
		}
		
		return true;
	}

	protected boolean checkLength(String input, int expectedLength) {
		return (input.length() == expectedLength);
	}

	protected boolean checkFormatInput(String input) {
		for (int i = 1; i <= input.length() - 2; i = i + 2) {
			if (!(input.charAt(i) == ' '))
				return false;
		}

		return true;
	}

	protected boolean checkNumeric(String input) {
		for (int i = 0; i <= input.length() - 1; i = i + 2) {
			char c = input.charAt(i);
			if (!(String.valueOf(c).matches("[0-9]")))
				return false;
		}

		return true;
	}

	protected boolean checkInLine(String input) {
		int x1 = Character.getNumericValue(input.charAt(0));
		int y1 = Character.getNumericValue(input.charAt(2));
		int x2 = Character.getNumericValue(input.charAt(4));
		int y2 = Character.getNumericValue(input.charAt(6));

		if (x1 == x2 || y1 == y2) {
			return true;
		}

		return false;
	}

	protected boolean checkOrder(String input) {
		int x1 = Character.getNumericValue(input.charAt(0));
		int y1 = Character.getNumericValue(input.charAt(2));
		int x2 = Character.getNumericValue(input.charAt(4));
		int y2 = Character.getNumericValue(input.charAt(6));

		if (x1 > x2 || y1 > y2) {
			return false;
		}

		return true;
	}
}

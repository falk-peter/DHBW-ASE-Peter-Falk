package domain;

import java.util.Objects;

public class ShotCoordinates {
	private int x, y;

	public ShotCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		ShotCoordinates other = (ShotCoordinates) obj;
		return x == other.x && y == other.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
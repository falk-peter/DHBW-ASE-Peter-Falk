package shot;

import domain.ShotCoordinates;

public class Shot {
	private int x;
	private int y;

	public Shot(ShotCoordinates shotCoordinates) {
		this.x = shotCoordinates.getX();
		this.y = shotCoordinates.getY();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}

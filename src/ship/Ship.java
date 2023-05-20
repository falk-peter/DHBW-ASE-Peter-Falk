package ship;

import input.ShipCoordinates;

public class Ship {
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	private boolean isHorizontal;
	private ShipCoordinates coordinates;

	public Ship(ShipCoordinates coordinates) {
		this.coordinates = coordinates;
		this.setStartX(coordinates.getX1());
		this.setStartY(coordinates.getY1());
		this.setEndX(coordinates.getX2());
		this.setEndY(coordinates.getY2());
		this.isHorizontal = checkIsHorizontal();
	}

	private boolean checkIsHorizontal() {
		return getEndY() == getStartY();
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}

	public boolean isHorizontal() {
		return isHorizontal;
	}

	public ShipCoordinates getCoordinates() {
		return coordinates;
	}
}
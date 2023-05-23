package domain;

public abstract class Ship {
	protected int startX;
	protected int startY;
	protected int endX;
	protected int endY;
	protected ShipCoordinates coordinates;
	protected int size;

	public Ship(ShipCoordinates coordinates) {
		this.coordinates = coordinates;
		this.setStartX(coordinates.getX1());
		this.setStartY(coordinates.getY1());
		this.setEndX(coordinates.getX2());
		this.setEndY(coordinates.getY2());
		calulateSize();
	}
	
	public void calulateSize() {
		int size_x = this.endX - this.startX;
		int size_y = this.endY - this.endY;
		
		if  (size_x > size_y)	
			size = size_x + 1;
		else
			size = size_y + 1;
	}
	
	abstract boolean sizeCorrect();

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

	public ShipCoordinates getCoordinates() {
		return coordinates;
	}
}
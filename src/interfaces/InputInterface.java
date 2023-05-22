package interfaces;

import domain.ShipCoordinates;
import domain.ShotCoordinates;

public interface InputInterface {
	String getName();
	ShipCoordinates getCoordinatesForShip();
	ShotCoordinates getCordinatesForShot();
}

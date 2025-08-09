package battleship.ships;

import battleship.exceptions.WrongShipLength;
import battleship.exceptions.WrongShipLocation;

public class AircraftCarrier extends Ship {
    public AircraftCarrier(String[] coordinates) throws WrongShipLocation, WrongShipLength {
        super.validateCoordinates(coordinates, 5);
        this.x1 = coordinates[0].charAt(0)-64;
        this.x2 = coordinates[1].charAt(0)-64;
        this.y1 = Integer.parseInt(coordinates[0].replaceAll("\\D", ""));
        this.y2 = Integer.parseInt(coordinates[1].replaceAll("\\D", ""));
        this.name = "Aircraft Carrier";
        this.position = new String[length];
        setPosition();
        this.health = 5;
    }

    public AircraftCarrier(){
        super();
        this.length = 5;
        this.name = "Aircraft Carrier";
    }
}

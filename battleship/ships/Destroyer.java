package battleship.ships;

import battleship.exceptions.WrongShipLength;
import battleship.exceptions.WrongShipLocation;

public class Destroyer extends Ship {
    public Destroyer(String[] coordinates) throws WrongShipLocation, WrongShipLength {
        super.validateCoordinates(coordinates, 2);
        this.x1 = coordinates[0].charAt(0) - 64;
        this.x2 = coordinates[1].charAt(0) - 64;
        this.y1 = Integer.parseInt(coordinates[0].replaceAll("\\D", ""));
        this.y2 = Integer.parseInt(coordinates[1].replaceAll("\\D", ""));
        this.name = "Destroyer";
        this.position = new String[length];
        setPosition();
        this.health = 2;
    }

    public Destroyer(){
        super();
        this.length = 2;
        this.name = "Destroyer";
    }
}
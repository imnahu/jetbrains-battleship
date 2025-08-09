package battleship.ships;

import battleship.exceptions.WrongShipLength;
import battleship.exceptions.WrongShipLocation;

public class Submarine extends Ship {
    public Submarine(String[] coordinates) throws WrongShipLocation, WrongShipLength {
        super.validateCoordinates(coordinates, 3);
        this.x1 = coordinates[0].charAt(0) - 64;
        this.x2 = coordinates[1].charAt(0) - 64;
        this.y1 = Integer.parseInt(coordinates[0].replaceAll("\\D", ""));
        this.y2 = Integer.parseInt(coordinates[1].replaceAll("\\D", ""));
        this.name = "Submarine";
        this.position = new String[length];
        setPosition();
        this.health = 3;
    }

    public Submarine(){
        super();
        this.length = 3;
        this.name = "Submarine";
    }
}
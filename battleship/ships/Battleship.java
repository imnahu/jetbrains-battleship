package battleship.ships;

import battleship.exceptions.WrongShipLength;
import battleship.exceptions.WrongShipLocation;

public class Battleship extends Ship {
    public Battleship(String[] coordinates) throws WrongShipLocation, WrongShipLength {
        super.validateCoordinates(coordinates, 4);
        this.x1 = coordinates[0].charAt(0) - 64;
        this.x2 = coordinates[1].charAt(0) - 64;
        this.y1 = Integer.parseInt(coordinates[0].replaceAll("\\D", ""));
        this.y2 = Integer.parseInt(coordinates[1].replaceAll("\\D", ""));
        this.name = "Battleship";
        this.position = new String[length];
        setPosition();
        this.health = 4;
    }

    public Battleship() {
        super();
        this.length = 4;
        this.name = "Battleship";
    }
}
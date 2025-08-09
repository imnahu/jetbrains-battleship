package battleship.ships;

import battleship.exceptions.*;

import java.util.Arrays;

public class Ship {
    protected int length;
    protected int x1;
    protected int y1;
    protected int x2;
    protected int y2;
    protected String name;
    protected int health;
    protected boolean sankStatus;
    protected String[] position;

    public Ship(){
        this.length = 0;
        this.x1 = 0;
        this.y1 = 0;
        this.x2 = 0;
        this.y2 = 0;
        this.name = "";
        this.health = 0;
        this.sankStatus = false;
        this.position = new String[length];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public String getPosition() {
        return Arrays.toString(position);
    }

    public boolean isSankStatus() {
        return sankStatus;
    }

    public void setSankStatus(boolean sankStatus) {
        this.sankStatus = sankStatus;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    protected void validateCoordinates (String[] coordinates, int length) throws WrongShipLength, WrongShipLocation {

        char firstLetter = coordinates[0].charAt(0);
        char secondLetter = coordinates[1].charAt(0);
        int firstCoordinate = Integer.parseInt(coordinates[0].replaceAll("\\D", ""));
        int secondCoordinate = Integer.parseInt(coordinates[1].replaceAll("\\D", ""));

        if (firstLetter > 74 || secondLetter > 74){
            throw new WrongShipLocation();
        }

        if (firstLetter == secondLetter) {
            boolean firstRange = firstCoordinate >= 1 && firstCoordinate <= 10;
            boolean secondRange = secondCoordinate >= 1 && secondCoordinate <= 10;

            if (firstRange && secondRange) {
                int calculatedLength = (Math.abs(firstCoordinate - secondCoordinate)) + 1;
                if (calculatedLength != length) {
                    throw new WrongShipLength();
                }
                else {
                    this.length = calculatedLength;
                }
            }
            else {
                throw new WrongShipLocation();
            }
        }
        else if (firstCoordinate == secondCoordinate) {
            boolean validRange = firstCoordinate >= 1 && firstCoordinate <= 10;

            if (validRange) {
                int calculatedLength = (Math.abs(firstLetter - secondLetter)) + 1;
                if (calculatedLength != length) {
                    throw new WrongShipLength();
                }
                else {
                    this.length = calculatedLength;
                }
            }
            else {
                throw new WrongShipLocation();
            }
        }
        else {
            throw new WrongShipLocation();
        }
    }

    protected void setPosition (){
        int localX1 = this.x1-1;
        int localY1 = this.y1-1;
        if (x1 != x2){
            if (x1 > x2){
                for (int i = 0; i < this.length; i++) {
                    this.position[i] = Integer.toString(localX1) + Integer.toString(localY1);
                    localX1--;
                }
            }
            else {
                for (int i = 0; i < this.length; i++) {
                    this.position[i] = Integer.toString(localX1) + Integer.toString(localY1);
                    localX1++;
                }
            }
        }
        else if (x1 == x2){
            if (y1 > y2){
                for (int i = 0; i < this.length; i++) {
                    this.position[i] = Integer.toString(localX1) + Integer.toString(localY1);
                    localY1--;
                }
            }
            else {
                for (int i = 0; i < this.length; i++) {
                    this.position[i] = Integer.toString(localX1) + Integer.toString(localY1);
                    localY1++;
                }
            }
        }

    }

    public boolean takeHit (String[] hit){
        String x = Integer.toString((hit[0].charAt(0) - 64) - 1);
        String y = Integer.toString(Integer.parseInt(hit[0].replaceAll("\\D", "")) -1);
        String pos = x + y;
        for (int i = 0; i < this.length; i++) {
            if (this.position[i].equals(pos)){
                this.position[i] = "x";
                this.health--;
                if (this.health == 0){
                    this.sankStatus = true;
                }
                return true;
            };
        }
        return false;
    }

    @Override
    public String toString() {
        String message = "x1: " + x1 + " x2: " + x2 + " y1: " + y1 + " y2: " + y2;
        return message;
    }
}

package battleship;

import battleship.exceptions.InexistentLocation;
import battleship.exceptions.InvalidHitLocation;
import battleship.ships.Ship;
import battleship.exceptions.WrongShipPlacement;

public class Field {
    private String[][] field;
    private String[][] fieldFog;

    public Field() {
        setField();
    }

    private void setField() {
        this.field = new String[10][10];
        this.fieldFog = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                field[i][j] = "~";
                fieldFog[i][j] = "~";
            }
        }
    }

    public void drawField() {
        System.out.print(" ");
        char rowLetter = 'A';
        for (int i = 1; i < 11; i++) {
            System.out.print(" " + i);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println();
            System.out.print(rowLetter);
            rowLetter++;
            for (int j = 0; j < 10; j++) {
                System.out.print(" " + this.field[i][j]);
            }
        }
        System.out.println();
    }

    public void drawFieldFog() {
        System.out.print(" ");
        char rowLetter = 'A';
        for (int i = 1; i < 11; i++) {
            System.out.print(" " + i);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println();
            System.out.print(rowLetter);
            rowLetter++;
            for (int j = 0; j < 10; j++) {
                System.out.print(" " + this.fieldFog[i][j]);
            }
        }
        System.out.println();
    }

    private void setHorizontal(int x1, int y1, int y2) throws WrongShipPlacement {
        // Check left
        if (y1 > 1 && this.field[x1-1][y1-2].equals("O")){
            throw new WrongShipPlacement();
        }
        // Check right
        if (y2 < 10 && this.field[x1-1][y2].equals("O")){
            throw new WrongShipPlacement();
        }

        if (y1 < y2){
            for (int i = y1; i <= y2; i++){
                if (this.field[x1-1][y1-1].equals("O")){
                    throw new WrongShipPlacement();
                }
                // Check up
                if (x1 > 1 && this.field[x1-2][y1-1].equals("O")){
                    throw new WrongShipPlacement();
                }
                // Check down
                if (x1 < 10 && this.field[x1][y1-1].equals("O")){
                    throw new WrongShipPlacement();
                }
                else {
                    this.field[x1-1][y1-1] = "O";
                    y1++;
                }
            }
        }
        else {
            for (int i = y1; i >= y2; i--){
                if (this.field[x1-1][y1-1].equals("O")){
                    throw new WrongShipPlacement();
                }
                // Check up
                if (x1 > 1 && this.field[x1-2][y1-1].equals("O")){
                    throw new WrongShipPlacement();
                }
                // Check down
                if (x1 < 10 && this.field[x1][y1-1].equals("O")){
                    throw new WrongShipPlacement();
                }
                else {
                    this.field[x1-1][y1-1] = "O";
                    y1--;
                }
            }
        }
    }

    private void setVertical(int x1, int x2, int y1) throws WrongShipPlacement {
        // Check up
        if (x1 > 1 && this.field[x1-2][y1-1].equals("O")){
            throw new WrongShipPlacement();
        }
        // Check down
        if (x1 < 10 && this.field[x1][y1-1].equals("O")){
            throw new WrongShipPlacement();
        }


        if (x1 < x2){
            for (int i = x1; i <= x2; i++){
                if (this.field[x1-1][y1-1].equals("O")){
                    throw new WrongShipPlacement();
                }
                // Check left
                if (y1 > 1 && this.field[x1-1][y1-2].equals("O")){
                    throw new WrongShipPlacement();
                }
                // Check right
                if (y1 < 10 && this.field[x1-1][y1].equals("O")){
                    throw new WrongShipPlacement();
                }
                else {
                    this.field[x1-1][y1-1] = "O";
                    x1++;
                }
            }
        }
        else {
            for (int i = x1; i >= x2; i--){
                if (this.field[x1-1][y1-1].equals("O")){
                    throw new WrongShipPlacement();
                }
                // Check left
                if (y1 > 1 && this.field[x1-1][y1-2].equals("O")){
                    throw new WrongShipPlacement();
                }
                // Check right
                if (y1 < 10 && this.field[x1-1][y1].equals("O")){
                    throw new WrongShipPlacement();
                }
                else {
                    this.field[x1-1][y1-1] = "O";
                    x1--;
                }
            }
        }
    }

    public void setCoordinates(Ship ship) throws WrongShipPlacement {
        if (ship.getX1() == ship.getX2()){
            setHorizontal(ship.getX1(), ship.getY1(), ship.getY2());
        }
        else {
            setVertical(ship.getX1(), ship.getX2(), ship.getY1());
        }
    }

    public void takeHit(String[] hit) throws InvalidHitLocation, InexistentLocation {
        int x1 = hit[0].charAt(0) - 64;
        int y1 = Integer.parseInt(hit[0].replaceAll("\\D", ""));

        if ((x1 >= 1 && x1 <= 10) && (y1 >= 1 && y1 <= 10)) {
            if (field[x1 - 1][y1 - 1].equals("O")) {
                field[x1 - 1][y1 - 1] = "X";
                fieldFog[x1 - 1][y1 - 1] = "X";

            } else if (field[x1-1][y1-1].equals("~") || field[x1-1][y1-1].equals("M")) {
                field[x1-1][y1-1] = "M";
                fieldFog[x1-1][y1-1] = "M";
                throw new InvalidHitLocation();
            }
        } else {
            throw new InexistentLocation();
        }
    }
}

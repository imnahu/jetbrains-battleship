package battleship;

import battleship.exceptions.*;
import battleship.ships.*;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.printf("Player 1, place your ships on the game field\n\n");
        Scanner sc = new Scanner(System.in);
        Field fieldPlayerOne = new Field();
        Field fieldPlayerTwo = new Field();
        int[] sankShipsOne = {0};
        int[] sankShipsTwo = {0};;



        Ship[] shipsPlayerOne = new Ship[5];
        Ship[] shipsPlayerTwo = new Ship[5];
        initializeShips(shipsPlayerOne);
        initializeShips(shipsPlayerTwo);




        int sankShips = 0;

        loadShips(shipsPlayerOne, fieldPlayerOne);
        passToPlayer();
        System.out.println("Player 2, place your ships to the game field");
        loadShips(shipsPlayerTwo, fieldPlayerTwo);
        passToPlayer();

        while (true) {
            drawField("Player 1", fieldPlayerOne, fieldPlayerTwo);
            tryShoot(fieldPlayerTwo, shipsPlayerTwo, sankShipsTwo);
            if (sankShipsTwo[0] == 5){
                break;
            }
            passToPlayer();
            drawField("Player 2", fieldPlayerOne, fieldPlayerTwo);
            tryShoot(fieldPlayerOne, shipsPlayerOne, sankShipsOne);
            if (sankShipsOne[0] == 5){
                break;
            }
            passToPlayer();
        }
    }

    static void loadShips(Ship[] ships, Field field) {
        int count = 0;
        Scanner sc = new Scanner(System.in);
        field.drawField();
        System.out.printf("Enter the coordinates of the %s (%d cells):\n", ships[count].getName(), ships[count].getLength());
        String[] coordinates = sc.nextLine().split(" ");

        while (count < 5) {
            try {
                switch (count) {
                    case 0:
                        ships[0] = new AircraftCarrier(coordinates);
                        break;
                    case 1:
                        ships[1] = new Battleship(coordinates);
                        break;
                    case 2:
                        ships[2] = new Submarine(coordinates);
                        break;
                    case 3:
                        ships[3] = new Cruiser(coordinates);
                        break;
                    case 4:
                        ships[4] = new Destroyer(coordinates);
                        break;
                }
                try {
                    field.setCoordinates(ships[count]);
                    field.drawField();
                    count++;
                    if (count < 5){
                        System.out.printf("Enter the coordinates of the %s (%d cells):\n", ships[count].getName(), ships[count].getLength());
                        coordinates = sc.nextLine().split(" ");
                    }

                } catch (WrongShipPlacement e) {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    coordinates = sc.nextLine().split(" ");
                }
            } catch (WrongShipLength e){
                System.out.printf("Error! Wrong length of the %s! Try again:\n", ships[count].getName());
                coordinates = sc.nextLine().split(" ");
            } catch (WrongShipLocation e){
                System.out.println("Error! Wrong ship location! Try again:");
                coordinates = sc.nextLine().split(" ");
            }
        }
    }

    static void initializeShips(Ship[] ships){
        ships[0] = new AircraftCarrier();
        ships[1] = new Battleship();
        ships[2] = new Submarine();
        ships[3] = new Cruiser();
        ships[4] = new Destroyer();
    }

    static void passToPlayer(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Press Enter and pass the move to another player");
        System.out.println("...");
        sc.nextLine();
    }

    static void drawField(String player, Field fieldOne, Field fieldTwo){
        if (player.equals("Player 1")){
            fieldTwo.drawFieldFog();
            System.out.println("---------------------");
            fieldOne.drawField();
        }
        else if (player.equals("Player 2")) {
            fieldOne.drawFieldFog();
            System.out.println("---------------------");
            fieldTwo.drawField();
        }
        System.out.printf("\n%s, it's your turn:\n", player);
    }

    static void tryShoot(Field field, Ship[] ships, int[] sankShips){
        boolean exception = true;
        while (exception){
            try {
                Scanner sc = new Scanner(System.in);
                String[] hit = sc.nextLine().split(" ");
                field.takeHit(hit);
                for (int i = 0; i < 5; i++){
                    if (!ships[i].isSankStatus() && ships[i].takeHit(hit)){
                        if (ships[i].isSankStatus() && sankShips[0] < 5){
                            sankShips[0]++;
                            if (sankShips[0] == 5){
                                System.out.println("You sank the last ship. You won. Congratulations!");
                            }
                            else {
                            System.out.println("You sank a ship!");
                            }
                        }
                        else {
                            System.out.println("You hit a ship!");
                        }
                    }
                }
                exception = false;
            } catch (InexistentLocation e) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
            } catch (InvalidHitLocation e) {
                System.out.println("You missed!");
                exception = false;
            }
        }
    }

}

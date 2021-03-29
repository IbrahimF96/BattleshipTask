import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class BattleshipTest {

    /*
    10x10 grid needed

    Place ships on grid at random, 1x Battleship (5 squares), 2x Destroyers (4 squares):
    * When grid is empty
    * When there are ships on the grid already

    Functionality to select coordinates:
    * Pick valid coordinates - Scans grid and returns a hit or miss.
    * Pick invalid coordinates - Throws exception

    Game ends when grid is empty again.

     */

    @Test
    public void addShipToEmptyGrid(){
        BattleshipsGame battleships = new BattleshipsGame();
        battleships.addShip(new Ship(5));
        assertEquals(5, battleships.numberOfSquaresUsed(), 0);
    }

    @Test
    public void addTwoShipsToEmptyGrid(){
        BattleshipsGame battleships = new BattleshipsGame();
        battleships.addShip(new Ship(5));
        battleships.addShip(new Ship(4));
        assertEquals(9, battleships.numberOfSquaresUsed() , 0);
    }

    @Test
    public void addThreeShipsToEmptyGrid(){
        BattleshipsGame battleships = new BattleshipsGame();
        battleships.addShip(new Ship(5));
        battleships.addShip(new Ship(4));
        battleships.addShip(new Ship(4));
        assertEquals(13, battleships.numberOfSquaresUsed() , 0);
    }

    @Test
    public void chooseValidCoordinatesWithEmptyGrid(){
        BattleshipsGame battleships = new BattleshipsGame();
        String outcome = battleships.pickCoordinates('A',1);
        assertEquals("Miss", outcome);

    }

    @Test
    public void chooseValidCoordinatesWithEditedGrid(){
        BattleshipsGame battleships = new BattleshipsGame();
        battleships.grid[0][0] = 1;
        String outcome = battleships.pickCoordinates('A',1);
        assertEquals("Hit", outcome);
    }

    @Test
    public void chooseInvalidCoordinatesArrayOutOfBoundsException(){
        BattleshipsGame battleships = new BattleshipsGame();
        String outcome = battleships.pickCoordinates('A',15);
        assertEquals("Invalid input, coordinates chosen outside of Battleship grid.", outcome);
    }





}

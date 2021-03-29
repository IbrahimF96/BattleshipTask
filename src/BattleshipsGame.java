import java.util.*;

public class BattleshipsGame {
    public int[][] grid = new int[10][10]; // Grid for the ships.

    public String[][] gridGuesses = new String[10][10];  // Initialise the grid for guesses made.

    public void addShip(Ship ship) {
        Random random = new Random();
        String orientation = ship.getOrientation();
        int shipSize = ship.getNumberOfSquares();

        if (orientation.equals("Horizontal")) {
            int row = random.nextInt(10);
            int column = random.nextInt(10 - shipSize);

            while (insufficientSpace(grid, row, column, shipSize, orientation)) {
                row = random.nextInt(10);
            }

            for (int i = 0; i < shipSize; i++) {
                grid[row][column + i] = 1;
            }
        }

        else {
            int column = random.nextInt(10);
            int row = random.nextInt(10 - shipSize);

            while (insufficientSpace(grid, row, column, shipSize, orientation)) {
                column = random.nextInt(10);
            }

            for (int i = 0; i < shipSize; i++) {
                grid[row + i][column] = 1;
            }
        }
    }

    public int numberOfSquaresUsed() {
        // Count number of squares on the grid covered by a ship.

        int counter = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (grid[i][j] == 1) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private boolean insufficientSpace(int[][] arr, int row, int column, int shipSize, String orientation) {
        // State whether there is sufficient space for the ship on the grid, given the size, orientation and starting coordinates.

        if (orientation.equals("Vertical")){
            for (int i = 0; i < shipSize; i++) {
                if (arr[row + i][column] == 1) {
                    return true;
                }
            }
        }
        else {
            for (int i = 0; i < shipSize; i++) {
                if (arr[row][column + i] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public String pickCoordinates(char column, int row){
        // Pick the coordinates for guesses to be made on the grid.

        int columnCoord = column - 65;
        int rowCoord = row - 1;

        try{
            if (grid[rowCoord][columnCoord] == 1){
                grid[rowCoord][columnCoord] = 0;
                gridGuesses[rowCoord][columnCoord] = "X";
                return "Hit";
            }
            else{
                gridGuesses[rowCoord][columnCoord] = "O";
                return "Miss";
            }
        }
        catch (ArrayIndexOutOfBoundsException ae) {
            return "Invalid input, coordinates chosen outside of Battleship grid.";
        }
    }

    public static void formatGrid(String[][] a){
        // Helper method to display the grid, makes it easier to visualise future guesses.

        String userGuesses = "";
        for (int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                userGuesses = userGuesses.concat(a[i][j] + " ");
            }
            userGuesses = userGuesses.concat("\n");
        }
        System.out.println("Guesses: \n" + userGuesses);
    }

    public static void main(String[] args) {

        BattleshipsGame battleships = new BattleshipsGame();

        battleships.addShip(new Ship(5));
        battleships.addShip(new Ship(4));
        battleships.addShip(new Ship(4));

        for(int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                battleships.gridGuesses[i][j] = "-";
            }
        }

        Scanner input = new Scanner(System.in);
        boolean gameRunning = true;

        System.out.println("Welcome to Battleships! Let's play!\n");

        while(gameRunning){
            System.out.println("Enter your column coordinate: ");
            char column = input.next().charAt(0);
            try{
                System.out.println("Enter your row coordinate: ");
                int rowCoord = input.nextInt();
                String outcome = battleships.pickCoordinates(column,rowCoord);
                System.out.println(outcome + "\n");
            }
            catch (InputMismatchException ex) {
                System.out.println("Row input is not an integer. Please enter a valid row coordinate! \n");
                input.next();
            }

            formatGrid(battleships.gridGuesses);

            if (battleships.numberOfSquaresUsed() == 0){
                gameRunning = false;
                System.out.println("Game Over!");
            }
        }
    }
}



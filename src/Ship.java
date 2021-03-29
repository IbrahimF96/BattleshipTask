import java.util.Random;

public class Ship {

    private final int numberOfSquares;

    public Ship(int numberOfSquares) {
        this.numberOfSquares = numberOfSquares;
    }

    public int getNumberOfSquares() {
        return numberOfSquares;
    }

    public String getOrientation() {
        Random random = new Random();
        String[] orientation = {"Horizontal", "Vertical"};
        int randomNumber = random.nextInt(2);
        return orientation[randomNumber];
    }
}

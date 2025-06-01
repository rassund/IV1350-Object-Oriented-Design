package se.kth.iv1350.higherGrade;

import java.util.Random;

public class DieComposition {
    private final int noSides;
    private final Random random;

    /**
     * Constructs a virtual die with the specified number of sides.
     * @param noSides The number of sides for the dice.
     */
    public DieComposition(int noSides) {
        this.noSides = noSides;
        random = new Random();
    }

    /**
     * Simulates a die roll.
     * @return An <code>int</code> representing a die roll.
     */
    public int rollDie() {
        return random.nextInt(noSides) + 1;
    }

    /**
     * Gets the number of sides on a die.
     * @return The number of sides on the die.
     */
    public int getSides() {
        return noSides;
    }
}
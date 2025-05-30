package se.kth.iv1350.higherGrade;

import java.util.Random;

public class DieInheritance extends Random {
    private final int noSides;

    /**
     * Constructs a virtual die with the specified number of sides.
     * @param noSides The number of sides for the dice.
     */
    public DieInheritance(int noSides) {
        this.noSides = noSides;
    }

    /**
     * Simulates a die roll.
     * @return An <code>int</code> representing a die roll.
     */
    public int rollDie() {
        return nextInt(noSides) + 1;
    }

    /**
     * Gets the number of sides on a die.
     * @return The number of sides on the die.
     */
    public int getSides() {
        return noSides;
    }
}
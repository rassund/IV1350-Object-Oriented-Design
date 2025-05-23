package se.kth.iv1350.higherGrade;

import java.util.Random;

public class DiceComposition {
    private final int noSides;
    private final Random random;

    public DiceComposition(int noSides) {
        this.noSides = noSides;
        random = new Random();
    }

    public int rollDice() {
        return random.nextInt(noSides) + 1;
    }

    public int getSides() {
        return noSides;
    }
}
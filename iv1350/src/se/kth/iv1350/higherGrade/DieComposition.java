package se.kth.iv1350.higherGrade;

import java.util.Random;

public class DieComposition {
    private final int noSides;
    private final Random random;

    public DieComposition(int noSides) {
        this.noSides = noSides;
        random = new Random();
    }

    public int rollDie() {
        return random.nextInt(noSides) + 1;
    }

    public int getSides() {
        return noSides;
    }
}
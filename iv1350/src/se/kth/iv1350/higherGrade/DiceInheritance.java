package se.kth.iv1350.higherGrade;

import java.util.Random;

public class DiceInheritance extends Random {
    private final int noSides;

    public DiceInheritance(int noSides) {
        this.noSides = noSides;
    }

    public int rollDice() {
        return nextInt(noSides) + 1;
    }

    public int getSides() {
        return noSides;
    }
}
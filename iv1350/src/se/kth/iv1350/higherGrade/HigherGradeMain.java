package se.kth.iv1350.higherGrade;

public class HigherGradeMain {
    public static void main(String[] args) {
        DiceInheritance diceInheritance = new DiceInheritance(6);
        System.out.println("D" + diceInheritance.getSides() + " throw using inheritance");
        System.out.println(diceInheritance.rollDice());
        System.out.println(diceInheritance.rollDice());
        System.out.println(diceInheritance.rollDice());

        DiceComposition diceComposition = new DiceComposition(6);
        System.out.println("D" + diceComposition.getSides() + " throw using composition");
        System.out.println(diceComposition.rollDice());
        System.out.println(diceComposition.rollDice());
        System.out.println(diceComposition.rollDice());
    }
}
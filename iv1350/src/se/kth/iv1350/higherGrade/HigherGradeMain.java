package se.kth.iv1350.higherGrade;

public class HigherGradeMain {
    public static void main(String[] args) {
        DieInheritance dieInheritance = new DieInheritance(6);
        System.out.println("D" + dieInheritance.getSides() + " throw using inheritance:");
        System.out.println(dieInheritance.rollDie());
        System.out.println(dieInheritance.rollDie());
        System.out.println(dieInheritance.rollDie());

        DieComposition dieComposition = new DieComposition(6);
        System.out.println("D" + dieComposition.getSides() + " throw using composition:");
        System.out.println(dieComposition.rollDie());
        System.out.println(dieComposition.rollDie());
        System.out.println(dieComposition.rollDie());
    }
}
package se.kth.iv1350.startup;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.view.View;

/**
 * Contains the <code>main</code> method, which starts the program.
 */
public class Main {
    /**
     * Starts the application
     * @param args The application does not take any command line parameters.
     */
    public static void main(String[] args) {
        Controller contr = new Controller();
        View view = new View(contr);

        view.testRun();
        view.testRun();
    }
}

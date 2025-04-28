package se.kth.iv1350.startup;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.integration.AccountingHandler;
import se.kth.iv1350.integration.DiscountHandler;
import se.kth.iv1350.integration.InventoryHandler;
import se.kth.iv1350.integration.PrinterHandler;
import se.kth.iv1350.model.Register;
import se.kth.iv1350.model.Amount;
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
        final String STARTING_BALANCE = "0";

        AccountingHandler accHandler = new AccountingHandler();
        InventoryHandler invHandler = new InventoryHandler();
        DiscountHandler discHandler = new DiscountHandler();
        PrinterHandler printHandler = new PrinterHandler();
        Amount balance = new Amount(STARTING_BALANCE);
        Register register = new Register(balance);

        Controller contr = new Controller(accHandler, invHandler, discHandler, printHandler, register);
        View view = new View(contr);

        view.testRun();
    }
}
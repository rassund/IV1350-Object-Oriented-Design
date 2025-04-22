package se.kth.iv1350.controller;

import se.kth.iv1350.integration.AccountingHandler;
import se.kth.iv1350.integration.DiscountHandler;
import se.kth.iv1350.integration.InventoryHandler;
import se.kth.iv1350.integration.PrinterHandler;
import se.kth.iv1350.model.Register;
import se.kth.iv1350.model.Sale;

/**
 * Contains code for the Controller. It is responsible for directing traffic between the different layers in the system.
 */
public class Controller {
    private AccountingHandler accHandler;
    private InventoryHandler invHandler;
    private DiscountHandler discHandler;
    private PrinterHandler printHandler;
    private Register register;

    private Sale sale;

    // ASK ON HANDLEDNING IF TOO MANY ARGUMENTS
    public Controller(AccountingHandler accHandler, InventoryHandler invHandler, DiscountHandler discHandler, PrinterHandler printHandler, Register register) {
        this.accHandler = accHandler;
        this.invHandler = invHandler;
        this.discHandler = discHandler;
        this.printHandler = printHandler;
        this.register = register;
    }

    public void startSale() {
        this.sale = new Sale();
    }
}

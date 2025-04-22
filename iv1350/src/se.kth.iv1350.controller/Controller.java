package se.kth.iv1350.controller;

import se.kth.iv1350.integration.AccountingHandler;
import se.kth.iv1350.integration.DiscountHandler;
import se.kth.iv1350.integration.InventoryHandler;
import se.kth.iv1350.integration.PrinterHandler;
import se.kth.iv1350.model.Register;

public class Controller {
    private AccountingHandler accHandler;
    private InventoryHandler invHandler;
    private DiscountHandler discHandler;
    private PrinterHandler printHandler;
    private Register register;

    // ASK ON HANDLEDNING IF TOO MANY ARGUMENTS
    public Controller(AccountingHandler accHandler, InventoryHandler invHandler, DiscountHandler discHandler, PrinterHandler printHandler, Register register) {
        this.accHandler = accHandler;
        this.invHandler = invHandler;
        this.discHandler = discHandler;
        this.printHandler = printHandler;
        this.register = register;
    }
}

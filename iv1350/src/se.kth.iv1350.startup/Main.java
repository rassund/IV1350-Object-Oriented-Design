package se.kth.iv1350.startup;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.integration.AccountingHandler;
import se.kth.iv1350.integration.DiscountHandler;
import se.kth.iv1350.integration.InventoryHandler;
import se.kth.iv1350.integration.PrinterHandler;
import se.kth.iv1350.model.Register;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.view.View;

public class Main {
    private final float STARTING_BALANCE = 0;

    public void Main(String[] args) {
        AccountingHandler accHandler = new AccountingHandler();
        InventoryHandler invHandler = new InventoryHandler();
        DiscountHandler discHandler = new DiscountHandler();
        PrinterHandler printHandler = new PrinterHandler();
        Amount balance = new Amount(STARTING_BALANCE);
        Register register = new Register(balance);

        Controller contr = new Controller(accHandler, invHandler, discHandler, printHandler, register);
        View view = new View(contr);
    }
}
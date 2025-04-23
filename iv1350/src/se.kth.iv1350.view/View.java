package se.kth.iv1350.view;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.model.Amount;

import java.math.BigDecimal;

/**
 * The <code>view</code> method, which is a hardcoded mockup of the layer that handles the cashiers interaction with the program.
 */

public class View {
    private Controller contr;

    public View(Controller contr) {
        this.contr = contr;
    }

    public void testRun() {
        contr.startSale();
        for (int i = 0; i < 5; i++) {
            contr.enterItemID(i);
        }
        Amount amountOfChange = contr.payForSale(new Amount(new BigDecimal(1000)));
    }
}
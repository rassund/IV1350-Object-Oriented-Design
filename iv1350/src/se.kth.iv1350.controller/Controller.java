package se.kth.iv1350.controller;

import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.SaleSummaryDTO;
import se.kth.iv1350.integration.AccountingHandler;
import se.kth.iv1350.integration.DiscountHandler;
import se.kth.iv1350.integration.InventoryHandler;
import se.kth.iv1350.integration.PrinterHandler;
import se.kth.iv1350.model.Register;
import se.kth.iv1350.model.Sale;

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

    public SaleSummaryDTO enterItemID(int itemID) {
        ItemDTO itemDTO = getItemFromSale(itemID);
        if (itemDTO == null) {
            itemDTO = invHandler.fetchInfo(itemID);
        }

        return sale.addItem(itemDTO);
    }

    private boolean itemIDAlreadyEntered(int itemID) {
        return getItemFromSale(itemID) != null;
    }

    private ItemDTO getItemFromSale(int itemID) {
        for (ItemDTO item : sale.getItems()) {
            if (item.getID() == itemID) {
                return item;
            }
        }
        return null;
    }
}
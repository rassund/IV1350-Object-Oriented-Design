package se.kth.iv1350.controller;

import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.ItemInBasketDTO;
import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.DTO.SaleSummaryDTO;
import se.kth.iv1350.integration.AccountingHandler;
import se.kth.iv1350.integration.DiscountHandler;
import se.kth.iv1350.integration.InventoryHandler;
import se.kth.iv1350.integration.PrinterHandler;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.Receipt;
import se.kth.iv1350.model.Register;
import se.kth.iv1350.model.Sale;

/**
 * Contains code for the Controller. It is responsible for directing traffic between the different layers in the system and all calls are made through it after startup.
 */
public class Controller {
    private AccountingHandler accHandler;
    private InventoryHandler invHandler;
    private DiscountHandler discHandler;
    private PrinterHandler printHandler;
    private Register register;

    private Sale sale;

    public Controller(AccountingHandler accHandler, InventoryHandler invHandler, DiscountHandler discHandler, PrinterHandler printHandler, Register register) {
        this.accHandler = accHandler;
        this.invHandler = invHandler;
        this.discHandler = discHandler;
        this.printHandler = printHandler;
        this.register = register;
    }

    /**
     * Initiates a new sale.
     */
    public void startSale() {
        this.sale = new Sale();
    }

    /**
     * Adds an item with corresponding <code>itemID</code> to the sale. If the item is not already in the sale, the item is fetched from the inventory.
     * @param itemID The ID of the item to addToThis.
     * @return A <code>SaleSummaryDTO</code> with information to be displayed in the view.
     */
    public SaleSummaryDTO enterItemID(int itemID) {
        ItemInBasketDTO itemInBasketDTO = getItemFromSale(itemID);
        ItemDTO itemDTO;
        if (!itemAlreadyInSale(itemInBasketDTO)) {
            itemDTO = invHandler.fetchItemDTO(itemID);
            itemInBasketDTO = new ItemInBasketDTO(itemDTO, 1);
        }
        else {
            int amountOfItemsInBasket = itemInBasketDTO.getAmountInBasket();
            itemInBasketDTO = new ItemInBasketDTO(itemInBasketDTO, amountOfItemsInBasket + 1);
        }
        return sale.addItem(itemInBasketDTO);
    }

    private ItemInBasketDTO getItemFromSale(int itemID) {
        for (ItemInBasketDTO item : sale.getItems()) {
            if (item.getID() == itemID) {
                return item;
            }
        }
        return null;
    }

    private boolean itemAlreadyInSale(ItemInBasketDTO itemInBasketDTO) {
        return itemInBasketDTO != null;
    }

    /**
     * Handles updating inventory and accounting systems, printing the receipt, and updating the register when the sale is paid for.
     * @param amountPaid The <code>Amount</code> paid by the customer.
     * @return The <code>Amount</code> of change to give back to the customer.
     */
    public Amount payForSale(Amount amountPaid) {
        SaleDTO saleDTO = sale.endSale(amountPaid);
        invHandler.updateInventory(saleDTO);
        accHandler.updateAccounting(saleDTO);
        Receipt receipt = new Receipt(saleDTO);
        printHandler.printReceipt(receipt);
        register.updateRegister(saleDTO.getTotalPrice());
        return saleDTO.getChange();
    }
}
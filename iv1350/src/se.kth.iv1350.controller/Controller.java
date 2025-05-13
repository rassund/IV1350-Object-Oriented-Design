package se.kth.iv1350.controller;

import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.ItemInBasketDTO;
import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.DTO.SaleSummaryDTO;
import se.kth.iv1350.integration.*;
import se.kth.iv1350.model.*;
import se.kth.iv1350.model.Discount;

import java.util.ArrayList;

/**
 * Contains code for the Controller. It is responsible for directing traffic between the different layers in the system and all calls are made through it after startup.
 */
public class Controller {
    private final AccountingHandler accHandler;
    private final InventoryHandler invHandler;
    private final DiscountHandler discHandler;
    private final PrinterHandler printHandler;
    private final Register register;

    private Sale sale;
    private ArrayList<SaleObserver> saleObservers = new ArrayList<>();

    public Controller(AccountingHandler accHandler, DiscountHandler discHandler, PrinterHandler printHandler, Register register) {
        this.accHandler = accHandler;
        this.invHandler = InventoryHandler.getInstance();
        this.discHandler = discHandler;
        this.printHandler = printHandler;
        this.register = register;
    }

    /**
     * Initiates a new sale.
     */
    public void startSale() {
        sale = new Sale();
        sale.addSaleObservers(saleObservers);
    }

    public void addSaleObserver(SaleObserver obs) {
        saleObservers.add(obs);
    }

    /**
     * Adds an item with corresponding <code>itemID</code> to the sale. If the item is not already in the sale, the item is fetched from the inventory.
     * @param itemID The ID of the item to add to the sale.
     * @return A <code>SaleSummaryDTO</code> with information to be displayed in the view.
     * @throws InvalidIDException
     */
    public SaleSummaryDTO enterItemID(int itemID) throws InvalidIDException {
        ItemInBasketDTO itemInBasketDTO = getItemFromSale(itemID);
        ItemDTO itemDTO;
        if (!itemAlreadyInSale(itemInBasketDTO)) {
            itemDTO = invHandler.fetchItemDTO(itemID);
            itemInBasketDTO = new ItemInBasketDTO(itemDTO, 1);
        }
        else {
            int amountOfItemsInBasket = itemInBasketDTO.amountInBasket();
            itemInBasketDTO = new ItemInBasketDTO(itemInBasketDTO, amountOfItemsInBasket + 1);
        }
        return sale.addItem(itemInBasketDTO);
    }

    private ItemInBasketDTO getItemFromSale(int itemID) {
        for (ItemInBasketDTO item : sale.getItems()) {
            if (item.itemID() == itemID) {
                return item;
            }
        }
        return null;
    }

    private boolean itemAlreadyInSale(ItemInBasketDTO itemInBasketDTO) {
        return itemInBasketDTO != null;
    }

    public void applyDiscount(int customerID) {
        Discount disc = discHandler.fetchDiscount(customerID);
        disc.applyDiscount(sale);
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
        register.addAmountToRegister(saleDTO.totalPrice());
        sale = null;
        return saleDTO.change();
    }
}

package se.kth.iv1350.model;

import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.ItemInBasketDTO;
import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.DTO.SaleSummaryDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Contains details for all things related to the current instance of a sale of item(s).
 */
public class Sale {
    private final Amount runningTotal;
    private final ArrayList<ItemInBasketDTO> items;
    private final Amount totalVAT;
    private ArrayList<SaleObserver> saleObservers;

    /**
     * Creates a {@link Sale} instance containing no items.
     */
    public Sale() {
        this.runningTotal = new Amount("0");
        this.items = new ArrayList<>();
        this.totalVAT = new Amount("0");
        this.saleObservers = new ArrayList<>();
    }

    /**
     * Adds all <code>SaleObserver</code> instances of the given list into the list contained in this <code>Sale</code> object.
     * Used when a new sale is started.
     * @param observers The list of <code>SaleObserver</code> instances to notify when the sale has ended.
     */
    public void addSaleObservers(ArrayList<SaleObserver> observers) { saleObservers = observers; }

    /**
     * Returns a reference to the list of items included in the sale.
     * @return A {@link ArrayList} containing {@link ItemInBasketDTO} objects representing items in the sale.
     */
    public ArrayList<ItemInBasketDTO> getItems() { return items; }

    /**
     * Returns the running total of the sale.
     * @return The running total of the sale.
     */
    public Amount getRunningTotal() { return runningTotal; }

    /**
     * Adds an {@link ItemDTO} to the sale and returns a {@link SaleSummaryDTO} for the register to display. Also updates the running total and total VAT.
     * @param itemToAdd The item to add to the sale.
     * @return A {@link SaleSummaryDTO} object containing info to show on the Register.
     */
    public SaleSummaryDTO addItem(ItemInBasketDTO itemToAdd) {
        int indexOfItemToAdd = -1;
        for (ItemInBasketDTO item : items) {
            if (itemIsAlreadyInBasket(item.itemID(), itemToAdd.itemID())) {
                indexOfItemToAdd = items.indexOf(item);
                break;
            }
        }
        if (indexOfItemToAdd == -1)
            items.add(itemToAdd);
        else
            items.set(indexOfItemToAdd, itemToAdd);

        Amount priceOfItem = itemToAdd.price();
        Amount costAddedByVAT = costAddedByVAT(itemToAdd);

        runningTotal.addToThis(priceOfItem);
        totalVAT.addToThis(costAddedByVAT);
        return new SaleSummaryDTO(itemToAdd, runningTotal, totalVAT);
    }

    private boolean itemIsAlreadyInBasket(int itemInBasketID, int itemToAddID) {
        return itemInBasketID == itemToAddID;
    }

    private Amount costAddedByVAT(ItemInBasketDTO itemInBasketDTO) {
        BigDecimal price = itemInBasketDTO.price().getAmount();
        BigDecimal vatRate = itemInBasketDTO.vatRate().getRateAsDecimal();
        BigDecimal amountToSubtract = price.divide(vatRate.add(BigDecimal.ONE), RoundingMode.HALF_UP);
        BigDecimal vatPortion = price.subtract(amountToSubtract);
        return new Amount(vatPortion);
    }

    /**
     * Ends the sale, returning info to be displayed on the receipt. Also notifies all <code>SaleObserver</code> instances that the sale has ended.
     * @param amountPaid The amount the customer gives to pay for the sale.
     * @return A <code>SaleDTO</code> object containing info to be shown on the receipt given to the customer. Includes how much money the customer gets back as change.
     */
    public SaleDTO endSale(Amount amountPaid) {
        LocalDateTime dateTime = LocalDateTime.now();
        Amount change = new Amount(amountPaid.getAmount());
        change.subtractFromThis(runningTotal);
        notifyAllObservers();
        return new SaleDTO(dateTime, items, runningTotal, totalVAT, amountPaid, change);
    }

    private void notifyAllObservers() {
        for (SaleObserver obs : saleObservers) {
            obs.saleHasEnded(runningTotal);
        }
    }
}

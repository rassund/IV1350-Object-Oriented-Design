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

    public Sale() {
        this.runningTotal = new Amount(BigDecimal.ZERO);
        this.items = new ArrayList<ItemInBasketDTO>();
        this.totalVAT = new Amount(BigDecimal.ZERO);
    }

    public ArrayList<ItemInBasketDTO> getItems() { return items; }

    public Amount getRunningTotal() { return runningTotal; }

    /**
     * Adds an <code>ItemDTO</code> to the sale and returns a <code>SaleSummaryDTO</code> for the register to display. Also updates the running total.
     * @param itemInBasketDTO The item to add to the sale.
     * @return A <code>SaleSummaryDTO</code> object containing info to show on the Register.
     */
    public SaleSummaryDTO addItem(ItemInBasketDTO itemInBasketDTO) {
        int indexOfItemToAdd = -1;
        for (ItemInBasketDTO item : this.getItems()) {
            if (item.getID() == itemInBasketDTO.getID()) {
                indexOfItemToAdd = items.indexOf(itemInBasketDTO);
            }
        }
        if (indexOfItemToAdd == -1)
            items.add(itemInBasketDTO);
        else
            items.set(indexOfItemToAdd, itemInBasketDTO);

        Amount priceOfItem = itemInBasketDTO.getPrice();
        Amount costAddedByVAT = new Amount(priceOfItem.getAmount().subtract(priceOfItem.getAmount().divide(itemInBasketDTO.getVATRate().getRateAsDecimal().add(BigDecimal.ONE), RoundingMode.HALF_UP)));

        runningTotal.addToThis(priceOfItem);
        totalVAT.addToThis(costAddedByVAT);
        return new SaleSummaryDTO(itemInBasketDTO, runningTotal, totalVAT);
    }

    /**
     * Ends the sale, returning info to be displayed on the receipt.
     * @param amountPaid The amount the customer gives to pay for the sale.
     * @return A <code>SaleDTO</code> object containing info to be shown on the receipt given to the customer. Includes how much money the customer gets back as change.
     */
    public SaleDTO endSale(Amount amountPaid) {
        LocalDateTime dateTime = LocalDateTime.now();
        // If amountPaid > runningTotal
        Amount change = new Amount(amountPaid.getAmount());
        change.subtractFromThis(runningTotal);
        return new SaleDTO(dateTime, items, runningTotal, totalVAT, amountPaid, change);
    }
}
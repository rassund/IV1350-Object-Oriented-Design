package se.kth.iv1350.model;

import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.DTO.SaleSummaryDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Contains details for all things related to the current instance of a sale of item(s).
 */
public class Sale {
    private Amount runningTotal;
    private ArrayList<ItemDTO> items;
    private Amount costAddedByVAT;

    public Sale() {
        this.runningTotal = new Amount(BigDecimal.ZERO);
        this.items = new ArrayList<ItemDTO>();
        this.costAddedByVAT = new Amount(BigDecimal.ZERO);
    }

    public ArrayList<ItemDTO> getItems() { return items; }

    public Amount getRunningTotal() { return runningTotal; }

    /**
     * Adds an <code>ItemDTO</code> to the sale and returns a <code>SaleSummaryDTO</code> for the register to display. Also updates the running total.
     * @param itemDTO The item to add to the sale.
     * @return A <code>SaleSummaryDTO</code> object containing info to show on the Register.
     */
    public SaleSummaryDTO addItem(ItemDTO itemDTO) {
        items.add(itemDTO);
        Amount priceOfItem = itemDTO.getPrice();
        Amount costAddedByVAT = new Amount(priceOfItem.getAmount().multiply(itemDTO.getVatRate()));
        Amount amountToAdd = new Amount(priceOfItem.getAmount().add(costAddedByVAT.getAmount()));

        runningTotal.addToThis(amountToAdd);
        this.costAddedByVAT.addToThis(costAddedByVAT);
        return new SaleSummaryDTO(itemDTO.getDescription(), itemDTO.getPrice(), runningTotal);
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
        return new SaleDTO(dateTime, items, runningTotal, costAddedByVAT, amountPaid, change);
    }
}
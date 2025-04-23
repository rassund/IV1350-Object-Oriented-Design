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

    public SaleSummaryDTO addItem(ItemDTO itemDTO) {
        items.add(itemDTO);
        Amount priceOfItem = itemDTO.getPrice();
        Amount costAddedByVAT = new Amount(priceOfItem.getAmount().multiply(itemDTO.getVatRate()));

        runningTotal.add(priceOfItem.getAmount().add(costAddedByVAT.getAmount()));
        this.costAddedByVAT.add(costAddedByVAT.getAmount());
        return new SaleSummaryDTO(itemDTO.getDescription(), itemDTO.getPrice(), runningTotal);
    }

    public ArrayList<ItemDTO> getItems() {
        return items;
    }

    public SaleDTO endSale(Amount amountPaid) {
        LocalDateTime dateTime = LocalDateTime.now();
        // If amountPaid > runningTotal
        Amount change = amountPaid.subtract(runningTotal);
        return new SaleDTO(dateTime, items, runningTotal, costAddedByVAT, amountPaid, change);
    }
}
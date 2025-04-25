package se.kth.iv1350.model;

import se.kth.iv1350.DTO.ItemDTO;
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
    private final ArrayList<ItemDTO> items;
    private final Amount totalVAT;

    public Sale() {
        this.runningTotal = new Amount(BigDecimal.ZERO);
        this.items = new ArrayList<ItemDTO>();
        this.totalVAT = new Amount(BigDecimal.ZERO);
    }

    public SaleSummaryDTO addItem(ItemDTO itemDTO) {
        items.add(itemDTO);
        Amount priceOfItem = itemDTO.getPrice();
        Amount costAddedByVAT = new Amount(priceOfItem.getAmount().subtract(priceOfItem.getAmount().divide(itemDTO.getVATRate().getRateAsDecimal().add(BigDecimal.ONE), RoundingMode.HALF_UP)));

        runningTotal.add(priceOfItem);
        totalVAT.add(costAddedByVAT);
        return new SaleSummaryDTO(itemDTO, runningTotal, totalVAT);
    }

    public SaleDTO endSale(Amount amountPaid) {
        LocalDateTime dateTime = LocalDateTime.now();
        // If amountPaid > runningTotal
        Amount change = new Amount(amountPaid.getAmount());
        change.subtract(runningTotal);
        return new SaleDTO(dateTime, items, runningTotal, totalVAT, amountPaid, change);
    }

    public ArrayList<ItemDTO> getItems() {
        return items;
    }

    public Amount getRunningTotal() { return runningTotal; }
}
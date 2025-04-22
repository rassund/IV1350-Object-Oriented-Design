package se.kth.iv1350.model;

import se.kth.iv1350.DTO.ItemDTO;
import se.kth.iv1350.DTO.SaleSummaryDTO;

import java.util.ArrayList;

/**
 * Contains details for all things related to the current instance of a sale of item(s).
 */
public class Sale {
    private Amount runningTotal;
    private ArrayList<ItemDTO> items;
    private Amount costAddedByVAT;

    public Sale() {
        this.runningTotal = new Amount(0);
        this.items = new ArrayList<ItemDTO>();
        this.costAddedByVAT = new Amount(0);
    }

    public SaleSummaryDTO addItem(ItemDTO itemDTO) {
        items.add(itemDTO);
        float priceOfItem = itemDTO.getPrice().getAmount();
        float costAddedByVAT = priceOfItem * itemDTO.getVatRate();

        runningTotal.setAmount(runningTotal.getAmount() + priceOfItem + costAddedByVAT);
        this.costAddedByVAT.setAmount(this.costAddedByVAT.getAmount() + costAddedByVAT);
        return new SaleSummaryDTO(itemDTO.getDescription(), itemDTO.getPrice(), runningTotal);
    }

    public ArrayList<ItemDTO> getItems() {
        return items;
    }
}
package se.kth.iv1350.DTO;

import se.kth.iv1350.model.Amount;

public class SaleSummaryDTO {
    String itemDescription;
    Amount itemPrice;
    Amount runningTotal;

    public SaleSummaryDTO(String itemDescription, Amount itemPrice, Amount runningTotal) {
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.runningTotal = runningTotal;
    }

    public String getLatestItemAddedDescription() {return itemDescription;}

    public Amount getLatestItemAddedPrice() {return itemPrice;}

    public Amount getRunningTotal() {return runningTotal;}
}

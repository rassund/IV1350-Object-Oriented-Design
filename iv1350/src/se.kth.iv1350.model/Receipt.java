package se.kth.iv1350.model;

import se.kth.iv1350.DTO.SaleDTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class Receipt {
    private final LocalDate dateOfSale;
    private final  LocalTime timeOfSale;
    private final  Amount totalPrice;
    private final  Amount totalVAT;
    private final  Amount amountPaid;
    private final  Amount change;



    public Receipt(SaleDTO saleDTO) {
        this.dateOfSale = saleDTO.getDateTime().toLocalDate();
        this.timeOfSale = saleDTO.getDateTime().toLocalTime().withSecond(0).withNano(0);
        this.totalPrice = saleDTO.getTotalPrice();
        this.totalVAT = saleDTO.getTotalVAT();
        this.amountPaid = saleDTO.getAmountPaid();
        this.change = saleDTO.getChange();

    }


    public LocalDate getDateOfSale() { return dateOfSale; }

    public LocalTime getTimeOfSale() { return timeOfSale; }

    public Amount getTotalPrice() { return totalPrice; }

    public Amount getTotalVAT() { return totalVAT; }

    public Amount getAmountPaid() { return amountPaid; }

    public Amount getChange() { return change; }
}
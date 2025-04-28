package se.kth.iv1350.model;

import se.kth.iv1350.DTO.SaleDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;



public class Receipt {
    private final LocalDateTime dateTimeOfSale;
    private final  Amount totalPrice;
    private final  Amount totalVAT;
    private final  Amount amountPaid;
    private final  Amount change;

    /**
     * Creates a <code>receipt</code> for a finished instance of a sale.
     * @param saleDTO contains all the needed information except for the <code>dateTimeOfSale</code> for the <code>receipt</code>.
     */

    public Receipt(SaleDTO saleDTO) {
        this.dateTimeOfSale = saleDTO.getDateTime();
        this.totalPrice = saleDTO.getTotalPrice();
        this.totalVAT = saleDTO.getTotalVAT();
        this.amountPaid = saleDTO.getAmountPaid();
        this.change = saleDTO.getChange();

    }


    public LocalDate getDateOfSale() { return dateTimeOfSale.toLocalDate(); }

    public LocalTime getTimeOfSale() { return dateTimeOfSale.toLocalTime().withSecond(0).withNano(0); }

    public Amount getTotalPrice() { return totalPrice; }

    public Amount getTotalVAT() { return totalVAT; }

    public Amount getAmountPaid() { return amountPaid; }

    public Amount getChange() { return change; }
}
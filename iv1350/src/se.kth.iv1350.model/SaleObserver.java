package se.kth.iv1350.model;

/**
 * A listener interface for receiving notifications for when a sale has ended.
 * The class that wants to receive such notifications can create a <code>SaleObserver</code> instance and
 * add it to the corresponding array in the <code>Controller</code> class. When a sale is ended, this "saleHasEnded" method is called.
 */
public interface SaleObserver {

    /**
     * Runs when a sale has ended.
     * @param amount The amount of total revenue, or the running total, for the sale that has ended.
     */
    void saleHasEnded(Amount amount);
}
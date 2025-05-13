package se.kth.iv1350.integration;

import se.kth.iv1350.DTO.SaleDTO;

/**
 * Used for handling the database used for accounting.
 */
public class AccountingHandler {
    private static final AccountingHandler INSTANCE = new AccountingHandler();

    private AccountingHandler() {}

    /**
     * @return The only Singleton instance of this <code>AccountingHandler</code> class.
     */
    public static AccountingHandler getInstance() {
        return INSTANCE;
    }

    /**
     * Updates the corresponding table in the database handled by the AccountingHandler class.
     * Contains no code since there is no such database right now.
     * @param saleDTO Contains all the info about what needs to be updated inside the database.
     */
    public void updateAccounting(SaleDTO saleDTO) {
        return;
    }
}

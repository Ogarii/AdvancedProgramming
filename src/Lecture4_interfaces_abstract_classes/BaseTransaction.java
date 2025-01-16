package Lecture4_interfaces_abstract_classes;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

public class BaseTransaction implements TransactionInterface {
    private final int amount;
    private final Calendar date;
    private final String transactionID;

    /**
     * Constructor for BaseTransaction.
     * @param amount in an integer
     * @param date: Not null, and must be a Calendar object
     * Initializes the attributes of a transaction.
     */
    public BaseTransaction(int amount, @NotNull Calendar date) {
        this.amount = amount;
        this.date = (Calendar) date.clone();
        int uniq = (int) (Math.random() * 10000); // Ensure random number generation is corrected
        transactionID = date.toString() + uniq;
    }

    /**
     * Returns the amount of the transaction.
     * @return the amount as a double
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Returns the date of the transaction.
     * @return a defensive copy of the Calendar object
     */
    public Calendar getDate() {
        return (Calendar) date.clone(); // Defensive copying
    }

    /**
     * Returns a unique transaction identifier.
     * @return the transaction ID as a String
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * Prints the details of the transaction.
     * can be overridden by subclasses.
     */
    @Override
    public void printTransactionDetails() {
        System.out.println("Transaction ID: " + getTransactionID());
        System.out.println("Date: " + getDate().getTime());
        System.out.println("Amount: " + getAmount());
    }

    /**
     * Returns the transaction amount.
     * can be overridden by subclasses
     * @return the amount as a Double
     */
    @Override
    public Double getAmounts() {
        return (double) getAmount();
    }

    /**
     * Applies the transaction to given bank account.
     * @param ba the bank account to apply the transaction to
     */
    @Override
    public void apply(BankAccount ba) {
        System.out.println("Base transaction applied");
    }
}

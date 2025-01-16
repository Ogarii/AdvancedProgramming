package Lecture4_interfaces_abstract_classes;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

/**
 * Represents a deposit transaction for a bank account.
 */
public class DepositTransaction extends BaseTransaction {

    /**
     * Constructor for a deposit transaction.
     * @param amount The deposit amount. Must be non-negative.
     * @param date The date of the transaction. Must not be null.
     */
    public DepositTransaction(int amount, @NotNull Calendar date) {
        super(amount, date);
        if (!checkDepositAmount(amount)) {
            throw new IllegalArgumentException("Deposit amount must be non-negative.");
        }
    }

    /**
     * Validates the deposit amount.
     * @param amt The deposit amount to check.
     * @return true if the amount is valid (non-negative), false otherwise.
     */
    private boolean checkDepositAmount(double amt) {
        return amt >= 0;
    }

    /**
     * Prints the details of the transaction.
     */
    @Override
    public void printTransactionDetails() {
        System.out.println("Deposit Transaction: " + this.toString());
    }

    /**
     * Retrieves the transaction amount.
     * @return The transaction amount as a Double.
     */
    @Override
    public Double getAmounts() {
        return (double) getAmount();
    }

    /**
     * Applies the deposit transaction to the given bank account.
     * @param ba The bank account to which the deposit transaction is applied.
     * @throws IllegalArgumentException if the bank account is null.
     * @throws IllegalStateException if the transaction amount is invalid.
     */
    @Override
    public void apply(BankAccount ba) {
        if (ba == null) {
            throw new IllegalArgumentException("Bank account cannot be null.");
        }
        if (!checkDepositAmount(getAmount())) {
            throw new IllegalStateException("Invalid deposit amount: " + getAmount());
        }
        try {
            double curr_balance = ba.getBalance();
            double new_balance = curr_balance + getAmount();
            ba.setBalance(new_balance);
            System.out.println("Deposit successful. New balance: " + new_balance);
        } catch (Exception e) {
            System.out.println("Error occurred while applying deposit: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

package Lecture4_interfaces_abstract_classes;

import java.util.Calendar;

public class WithdrawalTransaction extends BaseTransaction {
    private BankAccount appliedAccount; // To track the account for reversal
    private boolean applied; // To ensure the transaction is only applied once

    public WithdrawalTransaction(Calendar date, int amount) {
        super(amount, date);
        this.applied = false; // Initial state: transaction has not been applied
    }

    @Override
    public void printTransactionDetails(){
        System.out.println("Withdrawal Transaction");
        System.out.println("Date: " + getDate().getTime());
        System.out.println("Amount: " + getAmount());
    }

    @Override
    public Double getAmounts(){
        return (double) getAmount();
    }

    @Override
    public void apply(BankAccount ba){
        if (!applied) { // Check if already applied
            double curr_balance = ba.getBalance();
            if (curr_balance >= getAmount()) {
                double new_balance = curr_balance - getAmount();
                ba.setBalance(new_balance);
                appliedAccount = ba; // Track the account for possible reversal
                applied = true; // Mark as applied
                System.out.println("Withdrawal successful. New balance: " + new_balance);
            } else {
                System.out.println("Insufficient funds for withdrawal.");
            }
        } else {
            System.out.println("This transaction has already been applied.");
        }
    }

    public boolean reverse() {
        if (applied && appliedAccount != null) {
            try {
                appliedAccount.setBalance(appliedAccount.getBalance() + getAmount());
                applied = false;
                System.out.println("Withdrawal reversed. Restored balance: " + appliedAccount.getBalance());
                return true;
            } catch (Exception e) {
                System.out.println("Error occurred during reversal: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("Reversal failed: transaction not applied or already reversed.");
            return false;
        }
    }
}
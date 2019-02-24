package ee.taltech.iti0202.bankmanagement.card;
import ee.taltech.iti0202.bankmanagement.exceptions.TransactionException;

import java.math.BigDecimal;

public final class DebitCard extends BankCard {

    DebitCard() {
    }

    @Override
    public BigDecimal withdraw(BigDecimal value) throws TransactionException {
        if (this.getBalance().compareTo(value) < 0) {
            throw new TransactionException("Too large withdraw amount.");
        }
        balance = balance.subtract(value);
        return balance;
    }
}
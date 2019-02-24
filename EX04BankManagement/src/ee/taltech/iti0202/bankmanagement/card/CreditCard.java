package ee.taltech.iti0202.bankmanagement.card;
import ee.taltech.iti0202.bankmanagement.exceptions.TransactionException;

import java.math.BigDecimal;

public final class CreditCard extends BankCard {
    private static final long MAX_DEBT_ALLOWED = 5000L;
    CreditCard() {
    }

    @Override
    public BigDecimal withdraw(BigDecimal value) throws TransactionException {
        if (balance.add(new BigDecimal(MAX_DEBT_ALLOWED)).compareTo(value) < 0) {
            throw new TransactionException("Too large withdraw amount.");
        }
        balance = balance.subtract(value);
        return balance;
    }

    @Override
    public BigDecimal getBalance() {
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            return BigDecimal.ZERO;
        }
        return balance;
    }

    public BigDecimal getDebt() {
        if (balance.compareTo(BigDecimal.ZERO) >= 0) {
            return BigDecimal.ZERO;
        }
        return balance.abs();
    }
}

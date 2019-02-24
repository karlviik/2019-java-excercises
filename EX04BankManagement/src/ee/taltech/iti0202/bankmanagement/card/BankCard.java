package ee.taltech.iti0202.bankmanagement.card;
import ee.taltech.iti0202.bankmanagement.bank.Bank;
import ee.taltech.iti0202.bankmanagement.exceptions.TransactionException;
import ee.taltech.iti0202.bankmanagement.person.Person;

import java.math.BigDecimal;

public abstract class BankCard {
    BigDecimal balance;
    private Bank bank;
    private Person person;
    private CardType cardType;
    private static final long STARTING_CREDIT_CARD_BALANCE = 10000L;

    public enum CardType { CREDIT, DEBIT }

    /**
     * Constructor factory. Return a CreditCard or DebitCard object according to parameter cardType.
     *
     * @param cardType Specifies objected type to be returned.
     * @param bank     Specifies the bank of the created card.
     * @param person   Specifies the card owner.
     * @return
     */
    public static BankCard createCard(CardType cardType, Bank bank, Person person) {
        BankCard card;
        if (cardType == CardType.DEBIT) {
            card = new DebitCard();
            card.balance = BigDecimal.ZERO;
        } else {
            card = new CreditCard();
            card.balance = new BigDecimal(STARTING_CREDIT_CARD_BALANCE);
        }
        card.bank = bank;
        card.person = person;
        card.cardType = cardType;
        person.setBankCard(card);
        return card;
    }

    /**
     * Deposit given amount to the card.
     *
     * @param value Value to be deposited.
     * @throws TransactionException Thrown if given value is zero or less.
     */
    public void deposit(BigDecimal value) throws TransactionException {
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new TransactionException("Invalid deposit value.");
        }
        balance = balance.add(value);
    }

    /**
     * Withdraw the given amount from the card. Abstract function - implemented in subclasses CreditCard and DebitCard.
     *
     * @param value Value to be withdrawn.
     * @return Amount withdrawn.
     * @throws TransactionException Thrown if given value cannot be withdrawn for
     *                              various reasons - specified in subclasses.
     */
    public abstract BigDecimal withdraw(BigDecimal value) throws TransactionException;

    public Bank getBank() {
        return bank;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Person getPerson() {
        return person;
    }

    public boolean isDebit() {
        return cardType == CardType.DEBIT;
    }
}

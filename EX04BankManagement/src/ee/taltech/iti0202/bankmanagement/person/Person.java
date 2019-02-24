package ee.taltech.iti0202.bankmanagement.person;
import ee.taltech.iti0202.bankmanagement.card.BankCard;
import ee.taltech.iti0202.bankmanagement.exceptions.PersonException;

import java.util.Optional;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;
    private double monthlyIncome;
    private BankCard bankCard = null;

    public enum Gender { MALE, FEMALE }

    public Person(String firstName, String lastName, int age, Gender gender, double monthlyIncome)
            throws PersonException {
        if (age < 1) {
            throw new PersonException("Invalid age.");
        }
        if (monthlyIncome < 0) {
            throw new PersonException("Invalid monthly income.");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.monthlyIncome = monthlyIncome;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    /**
     * Return Optional.empty() if person has no bankcard.
     * @return Optional of BankCard
     */
    public Optional<BankCard> getBankCard() {
        return Optional.ofNullable(bankCard);
    }

    public void removeBankCard() {
        bankCard = null;
    }

    public void setBankCard(BankCard bankCard) {
        if (this.bankCard != null && this.bankCard.getBank() == bankCard.getBank()) {
                this.bankCard.getBank().removeCustomer(this);
        }
        if (bankCard != null) {
            bankCard.getBank().addCustomer(this);
        }
        this.bankCard = bankCard;
    }

    @Override
    public String toString() {
        return String.format("%s %s", firstName, lastName);
    }
}

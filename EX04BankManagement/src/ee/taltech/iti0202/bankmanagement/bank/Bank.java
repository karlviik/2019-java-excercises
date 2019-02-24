package ee.taltech.iti0202.bankmanagement.bank;
import ee.taltech.iti0202.bankmanagement.card.BankCard;
import ee.taltech.iti0202.bankmanagement.person.Person;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toList;

public class Bank {
    private String name;
    private Set<Person> customers = new HashSet<>();

    public Bank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<Person> getCustomers() {
        return customers;
    }

    public Boolean addCustomer(Person person) {
        if (customers.contains(person)) {
            return false;
        }
        customers.add(person);
        return true;
    }

    public Boolean removeCustomer(Person person) {
        if (!customers.contains(person)) {
            return false;
        }
        person.setBankCard(null);
        customers.remove(person);
        return true;
    }

    public double getAverageCustomerMonthlyIncome() {
        if (customers.size() == 0) {
            return 0.0;
        }
        double sumOfMonthlyIncomes = 0;
        for (Person customer : customers) {
            sumOfMonthlyIncomes += customer.getMonthlyIncome();
        }
        return sumOfMonthlyIncomes / customers.size();
    }

    public double getAverageCustomerMonthlyIncome(int maxAge) {
        if (customers.size() == 0) {
            return 0.0;
        }
        List<Person> filteredCustomers = customers.stream()
                .filter(person -> person.getAge() <= maxAge)
                .collect(toList());
        return filteredCustomers
                .stream()
                .mapToDouble(Person::getMonthlyIncome)
                .sum() / filteredCustomers.size();
    }

    public double getAverageCustomerMonthlyIncome(int minAge, int maxAge) {
        if (customers.size() == 0) {
            return 0.0;
        }
        List<Person> filteredCustomers = customers.stream()
                .filter(person -> person.getAge() >= minAge && person.getAge() <= maxAge)
                .collect(toList());
        return filteredCustomers
                .stream()
                .mapToDouble(Person::getMonthlyIncome)
                .sum() / filteredCustomers.size();
    }

    public double getAverageCustomerMonthlyIncome(Person.Gender gender) {
        if (customers.size() == 0) {
            return 0.0;
        }
        List<Person> filteredCustomers = customers.stream()
                .filter(person -> person.getGender() == gender)
                .collect(toList());
        return filteredCustomers
                .stream()
                .mapToDouble(Person::getMonthlyIncome)
                .sum() / filteredCustomers.size();
    }

    public Set<Person> getAllCustomersWithCreditCards() {
        return customers.stream()
                .filter(person -> person.getBankCard().filter(not(BankCard::isDebit)).isPresent())
                .collect(Collectors.toSet());
    }

    public Set<Person> getAllCustomersWithDebitCards() {
        return customers.stream()
                .filter(person -> person.getBankCard().filter(BankCard::isDebit).isPresent())
                .collect(Collectors.toSet());
    }

    public Optional<Person> getRichestCustomerByGender(Person.Gender gender) {
        return customers.stream()
                .filter(person -> person.getGender() == gender)
                .max(Comparator.comparingDouble(Person::getMonthlyIncome));
    }

    @Override
    public String toString() {
        return getName();
    }
}

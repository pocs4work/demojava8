package org.me.streams2;

import java.util.Objects;

public class Bill {

    private String reference;
    private double amount;
    private String customer;

    public Bill(String reference, double amount, String customer) {
        this.reference = reference;
        this.amount = amount;
        this.customer = customer;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return Double.compare(bill.amount, amount) == 0 &&
                Objects.equals(reference, bill.reference) &&
                Objects.equals(customer, bill.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference, amount, customer);
    }

    @Override
    public String toString() {
        return "reference " + reference + " amount " + amount + " customer " + customer;
    }

}


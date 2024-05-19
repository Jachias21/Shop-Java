package model;

import java.time.LocalDateTime;

public class Amount {
    private double value;
    private String currency;

    public Amount(double value, String currency) {
        this.value = value;
        this.currency = "€";
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value, LocalDateTime saleTime) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String toString() {
        return value + currency;
    }
}
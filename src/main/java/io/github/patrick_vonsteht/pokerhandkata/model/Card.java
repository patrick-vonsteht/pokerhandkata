package io.github.patrick_vonsteht.pokerhandkata.model;

public record Card(CardSuit suit, CardValue value) {

    public int numericValue() {
        return value.numericValue();
    }

    public int numericSuitValue() {
        return suit.numericValue();
    }
}

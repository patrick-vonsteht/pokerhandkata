package io.github.patrick_vonsteht;

public record Card(CardSuit suit, CardValue value) {

    public int getNumericValue() {
        return value.getNumericValue();
    }
}

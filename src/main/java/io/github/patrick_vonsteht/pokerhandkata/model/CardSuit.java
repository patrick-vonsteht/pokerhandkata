package io.github.patrick_vonsteht.pokerhandkata.model;

public enum CardSuit {
    CLUBS(1),
    DIAMONDS(2),
    HEARTS(3),
    SPADES(4);

    private final int value;

    CardSuit(final int value) {
        this.value = value;
    }

    public int numericValue() {
        return this.value;
    }
}
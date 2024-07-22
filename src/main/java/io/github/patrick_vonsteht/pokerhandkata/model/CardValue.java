package io.github.patrick_vonsteht.pokerhandkata.model;

public enum CardValue {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13),
    ACE(14);

    private final int value;

    CardValue(final int value) {
        this.value = value;
    }

    public int numericValue() {
        return this.value;
    }
}

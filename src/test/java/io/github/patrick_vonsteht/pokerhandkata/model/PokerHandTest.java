package io.github.patrick_vonsteht.pokerhandkata.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokerHandTest {

    @Test
    void ThrowsIllegalArgumentExceptionWhenCreatingHandWithNullCard() {
        assertThrows(IllegalArgumentException.class, () -> PokerHand.fromCards(null, null, null, null, null));
    }
}
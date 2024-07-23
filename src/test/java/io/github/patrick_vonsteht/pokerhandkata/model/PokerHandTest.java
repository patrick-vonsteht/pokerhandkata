package io.github.patrick_vonsteht.pokerhandkata.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokerHandTest {

    @Test
    void ThrowsIllegalArgumentExceptionWhenCreatingHandWithNullCard() {
        Card card = new Card(CardSuit.DIAMONDS, CardValue.FIVE);
        assertThrows(IllegalArgumentException.class, () -> PokerHand.fromCards(card, card, card, card, null));
        assertThrows(IllegalArgumentException.class, () -> PokerHand.fromCards(card, card, card, null, card));
        assertThrows(IllegalArgumentException.class, () -> PokerHand.fromCards(card, card, null, card, card));
        assertThrows(IllegalArgumentException.class, () -> PokerHand.fromCards(card, null, card, card, card));
        assertThrows(IllegalArgumentException.class, () -> PokerHand.fromCards(null, card, card, card, card));
    }
}
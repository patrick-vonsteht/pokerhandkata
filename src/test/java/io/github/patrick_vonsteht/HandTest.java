package io.github.patrick_vonsteht;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Test
    void SortedByValueIteratorIsSorted() {
        PokerHand h = new PokerHand(
                new Card(CardSuit.CLUBS, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.THREE),
                new Card(CardSuit.CLUBS, CardValue.FOUR),
                new Card(CardSuit.CLUBS, CardValue.FIVE),
                new Card(CardSuit.CLUBS, CardValue.SIX)
                );

        int previousValue = Integer.MIN_VALUE;
        for (Iterator<Card> it = h.sortedByValueIterator(); it.hasNext(); ) {
            Card c = it.next();
            assertTrue(previousValue <= c.getNumericValue());
            previousValue = c.getNumericValue();
        }
    }
}
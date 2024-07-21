package io.github.patrick_vonsteht;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FlushMatcherTest {
    @Test
    void FlushMatcherMatchesFlush() {
        PokerHand flushHand = new PokerHand(
                new Card(CardSuit.CLUBS, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.FOUR),
                new Card(CardSuit.CLUBS, CardValue.JACK),
                new Card(CardSuit.CLUBS, CardValue.FIVE),
                new Card(CardSuit.CLUBS, CardValue.SIX)
        );

        FlushMatcher m = new FlushMatcher();
        assertTrue(m.matches(flushHand));
    }

    @Test
    void FlushMatcherDoesNotMatchNonFlush() {
        PokerHand nonFlushHand = new PokerHand(
                new Card(CardSuit.DIAMONDS, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.FOUR),
                new Card(CardSuit.CLUBS, CardValue.JACK),
                new Card(CardSuit.CLUBS, CardValue.FIVE),
                new Card(CardSuit.CLUBS, CardValue.SIX)
        );

        FlushMatcher m = new FlushMatcher();
        assertFalse(m.matches(nonFlushHand));
    }
}

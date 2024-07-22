package io.github.patrick_vonsteht.pokerhandkata.matchers;

import io.github.patrick_vonsteht.pokerhandkata.model.CardSuit;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlushMatcherTest {
    @Test
    void MatchesFlush() {
        assertMatches(List.of(CardSuit.CLUBS, CardSuit.CLUBS, CardSuit.CLUBS, CardSuit.CLUBS, CardSuit.CLUBS));
    }

    @Test
    void DoesNotMatchNonFlush() {
        assertDoesNotMatch(List.of(CardSuit.DIAMONDS, CardSuit.CLUBS, CardSuit.CLUBS, CardSuit.CLUBS, CardSuit.CLUBS));
    }

    private void assertMatches(List<CardSuit> handSuits) {
        PokerHand hand = PokerHandTestHelper.createHandFromSuits(handSuits);
        FlushMatcher m = new FlushMatcher();
        assertTrue(m.matches(hand));
    }

    private void assertDoesNotMatch(List<CardSuit> handSuits) {
        PokerHand hand = PokerHandTestHelper.createHandFromSuits(handSuits);
        FlushMatcher m = new FlushMatcher();
        assertFalse(m.matches(hand));
    }
}

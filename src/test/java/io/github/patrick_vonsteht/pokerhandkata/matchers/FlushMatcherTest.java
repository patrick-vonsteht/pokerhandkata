package io.github.patrick_vonsteht.pokerhandkata.matchers;

import io.github.patrick_vonsteht.pokerhandkata.model.Card;
import io.github.patrick_vonsteht.pokerhandkata.model.CardSuit;
import io.github.patrick_vonsteht.pokerhandkata.model.CardValue;
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
        PokerHand hand = createHand(handSuits);
        FlushMatcher m = new FlushMatcher();
        assertTrue(m.matches(hand));
    }

    private void assertDoesNotMatch(List<CardSuit> handSuits) {
        PokerHand hand = createHand(handSuits);
        FlushMatcher m = new FlushMatcher();
        assertFalse(m.matches(hand));
    }

    private PokerHand createHand(List<CardSuit> handSuits) {
        return new PokerHand(
                new Card(handSuits.get(0), CardValue.TWO),
                new Card(handSuits.get(1), CardValue.FOUR),
                new Card(handSuits.get(2), CardValue.JACK),
                new Card(handSuits.get(3), CardValue.FIVE),
                new Card(handSuits.get(4), CardValue.SIX)
        );
    }
}

package io.github.patrick_vonsteht.pokerhandkata.matchers;

import io.github.patrick_vonsteht.pokerhandkata.PokerHandTestHelper;
import io.github.patrick_vonsteht.pokerhandkata.model.CardSuit;
import io.github.patrick_vonsteht.pokerhandkata.model.CardValue;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StraightFlushMatcherTest {

    @Test
    void MatchesStraightFlush() {
        assertMatches(
                List.of(CardSuit.CLUBS, CardSuit.CLUBS, CardSuit.CLUBS, CardSuit.CLUBS, CardSuit.CLUBS),
                List.of(CardValue.TWO, CardValue.FOUR, CardValue.THREE, CardValue.FIVE, CardValue.SIX));
    }

    @Test
    void DoesNotMatchNonStraightFlush() {
        assertDoesNotMatch(
                List.of(CardSuit.CLUBS, CardSuit.CLUBS, CardSuit.CLUBS, CardSuit.CLUBS, CardSuit.CLUBS),
                List.of(CardValue.TWO, CardValue.QUEEN, CardValue.FOUR, CardValue.FIVE, CardValue.SIX));
    }

    @Test
    void DoesNotMatchNonFlushStraight() {
        assertDoesNotMatch(
                List.of(CardSuit.CLUBS, CardSuit.CLUBS, CardSuit.DIAMONDS, CardSuit.CLUBS, CardSuit.CLUBS),
                List.of(CardValue.TWO, CardValue.THREE, CardValue.FOUR, CardValue.FIVE, CardValue.SIX));
    }

    @Test
    void DoesNotMatchOtherHand() {
        assertDoesNotMatch(
                List.of(CardSuit.CLUBS, CardSuit.DIAMONDS, CardSuit.CLUBS, CardSuit.SPADES, CardSuit.CLUBS),
                List.of(CardValue.TWO, CardValue.QUEEN, CardValue.FOUR, CardValue.FIVE, CardValue.SIX));
    }

    private void assertMatches(List<CardSuit> handSuits, List<CardValue> handValues) {
        assertResult(handSuits, handValues, true);
    }

    private void assertDoesNotMatch(List<CardSuit> handSuits, List<CardValue> handValues) {
        assertResult(handSuits, handValues, false);
    }

    private void assertResult(List<CardSuit> handSuits, List<CardValue> handValues, boolean expectedResult) {
        final PokerHand hand = PokerHandTestHelper.createHandFromSuitsAndValues(handSuits, handValues);
        final PokerHandMatcher matcher = createStraightFlushMatcher();
        assertEquals(expectedResult, matcher.matches(hand));
    }

    private PokerHandMatcher createStraightFlushMatcher() {
        final PokerHandMatcher straightMatcher = new StraightMatcher();
        final PokerHandMatcher flushMatcher = new FlushMatcher();
        return new AndMatcher(straightMatcher, flushMatcher);
    }
}
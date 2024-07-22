package io.github.patrick_vonsteht.pokerhandkata.matchers;

import io.github.patrick_vonsteht.pokerhandkata.model.CardValue;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StraightMatcherTest {

    @Test
    void MatchesLowestStraight() {
        assertMatches(List.of(CardValue.TWO, CardValue.THREE, CardValue.FIVE, CardValue.FOUR, CardValue.SIX));
    }

    @Test
    void MatchesMiddleStraight() {
        assertMatches(List.of(CardValue.FIVE, CardValue.SEVEN, CardValue.SIX, CardValue.EIGHT, CardValue.NINE));
    }

    @Test
    void MatchesHighestStraight() {
        assertMatches(List.of(CardValue.ACE, CardValue.QUEEN, CardValue.KING, CardValue.JACK, CardValue.TEN));
    }

    @Test
    void DoesNotMatchNonStraight() {
        assertDoesNotMatch(List.of(CardValue.TWO, CardValue.QUEEN, CardValue.SIX, CardValue.JACK, CardValue.NINE));
    }

    private void assertMatches(List<CardValue> handValues) {
        PokerHand hand = PokerHandTestHelper.createHandFromValues(handValues);
        StraightMatcher matcher = new StraightMatcher();
        assertTrue(matcher.matches(hand));
    }

    private void assertDoesNotMatch(List<CardValue> handValues) {
        PokerHand hand = PokerHandTestHelper.createHandFromValues(handValues);
        StraightMatcher matcher = new StraightMatcher();
        assertFalse(matcher.matches(hand));
    }
}

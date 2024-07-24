package io.github.patrick_vonsteht.pokerhandkata.matchers;

import io.github.patrick_vonsteht.pokerhandkata.PokerHandTestHelper;
import io.github.patrick_vonsteht.pokerhandkata.TestValues;
import io.github.patrick_vonsteht.pokerhandkata.model.CardValue;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StraightMatcherTest {

    @Test
    void MatchesLowestStraight() {
        assertMatches(TestValues.LOW_STRAIGHT_VALUES);
    }

    @Test
    void MatchesMiddleStraight() {
        assertMatches(TestValues.MIDDLE_STRAIGHT_VALUES);
    }

    @Test
    void MatchesHighestStraight() {
        assertMatches(TestValues.HIGH_STRAIGHT_VALUES);
    }

    @Test
    void DoesNotMatchNonStraight() {
        assertDoesNotMatch(TestValues.ANY_CARD_VALUES);
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

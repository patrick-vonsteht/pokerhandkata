package io.github.patrick_vonsteht.pokerhandkata.matchers;

import io.github.patrick_vonsteht.pokerhandkata.PokerHandTestHelper;
import io.github.patrick_vonsteht.pokerhandkata.model.CardValue;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FullHouseMatcherTest {

    @Test
    void MatchesLowFullHouse() {
        assertMatches(List.of(CardValue.TWO, CardValue.FOUR, CardValue.TWO, CardValue.TWO, CardValue.FOUR));
    }

    @Test
    void MatchesHighFullHouse() {
        assertMatches(List.of(CardValue.TWO, CardValue.FOUR, CardValue.TWO, CardValue.FOUR, CardValue.FOUR));
    }

    @Test
    void DoesNotMatchNonFullHouseThreeOfAKind() {
        assertDoesNotMatch(List.of(CardValue.THREE, CardValue.FOUR, CardValue.FOUR, CardValue.FOUR, CardValue.FIVE));
    }

    @Test
    void DoesNotMatchNonFullHouseTwoOfAKind() {
        assertDoesNotMatch(List.of(CardValue.JACK, CardValue.JACK, CardValue.THREE, CardValue.FOUR, CardValue.FIVE));
    }

    @Test
    void DoesNotMatchNonFullHouse() {
        assertDoesNotMatch(List.of(CardValue.QUEEN, CardValue.JACK, CardValue.THREE, CardValue.NINE, CardValue.FIVE));
    }

    private void assertMatches(List<CardValue> handValues) {
        assertResult(handValues, true);
    }

    private void assertDoesNotMatch(List<CardValue> handValues) {
        assertResult(handValues, false);
    }

    private void assertResult(List<CardValue> handValues, boolean expectedResult) {
        final PokerHand hand = PokerHandTestHelper.createHandFromValues(handValues);
        final PokerHandMatcher matcher = createFullHouseMatcher();
        assertEquals(expectedResult, matcher.matches(hand));
    }

    private PokerHandMatcher createFullHouseMatcher() {
        final PokerHandMatcher threeOfAKindMatcher = new XOfAKindMatcher(3, 1, 1);
        final PokerHandMatcher twoOfAKindMatcher = new XOfAKindMatcher(2, 1, 1);
        return new AndMatcher(threeOfAKindMatcher, twoOfAKindMatcher);
    }
}
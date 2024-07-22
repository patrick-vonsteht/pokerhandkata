package io.github.patrick_vonsteht.pokerhandkata.matchers;

import io.github.patrick_vonsteht.pokerhandkata.PokerHandTestHelper;
import io.github.patrick_vonsteht.pokerhandkata.model.CardValue;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class XOfAKindMatcherTest {

    @Test
    void SingleOneOfAKindMatcherDoesNotMatchAnyHand() {
        PokerHandMatcher matcher = new XOfAKindMatcher(1, 1, 1);
        assertDoesNotMatch(matcher, List.of(CardValue.TWO, CardValue.FOUR, CardValue.QUEEN, CardValue.ACE, CardValue.EIGHT));
    }

    @Test
    void MultipleOneOfAKindMatcherMatchesAnyHand() {
        PokerHandMatcher matcher = new XOfAKindMatcher(1, 1, 5);
        assertMatches(matcher, List.of(CardValue.TWO, CardValue.FOUR, CardValue.QUEEN, CardValue.ACE, CardValue.EIGHT));
    }

    @Test
    void SingleTwoOfAKindMatcherMatchesLowTwoOfAKind() {
        PokerHandMatcher matcher = new XOfAKindMatcher(2, 1, 1);
        assertMatches(matcher, List.of(CardValue.TWO, CardValue.FOUR, CardValue.TWO, CardValue.ACE, CardValue.EIGHT));
    }

    @Test
    void SingleTwoOfAKindMatcherMatchesMiddleTwoOfAKind() {
        PokerHandMatcher matcher = new XOfAKindMatcher(2, 1, 1);
        assertMatches(matcher, List.of(CardValue.TWO, CardValue.FOUR, CardValue.FOUR, CardValue.ACE, CardValue.EIGHT));
    }

    @Test
    void SingleTwoOfAKindMatcherMatchesHighTwoOfAKind() {
        PokerHandMatcher matcher = new XOfAKindMatcher(2, 1, 1);
        assertMatches(matcher, List.of(CardValue.TWO, CardValue.FOUR, CardValue.ACE, CardValue.ACE, CardValue.EIGHT));
    }

    @Test
    void SingleTwoOfAKindMatcherDoesNotMatchNonTwoOfAKind() {
        PokerHandMatcher matcher = new XOfAKindMatcher(2, 1, 1);
        assertDoesNotMatch(matcher, List.of(CardValue.TEN, CardValue.FOUR, CardValue.TWO, CardValue.JACK, CardValue.QUEEN));
    }

    @Test
    void SingleTwoOfAKindMatcherDoesNotMatchTwoTwoOfAKind() {
        PokerHandMatcher matcher = new XOfAKindMatcher(2, 1, 1);
        assertDoesNotMatch(matcher, List.of(CardValue.TEN, CardValue.FOUR, CardValue.TEN, CardValue.JACK, CardValue.JACK));
    }

    @Test
    void TwoTwoOfAKindMatcherMatchesTwoTwoOfAKind() {
        PokerHandMatcher matcher = new XOfAKindMatcher(2, 2, 2);
        assertMatches(matcher, List.of(CardValue.TEN, CardValue.FOUR, CardValue.TEN, CardValue.JACK, CardValue.JACK));
    }

    @Test
    void TwoTwoOfAKindMatcherDoesNotMatchSingleTwoOfAKind() {
        PokerHandMatcher matcher = new XOfAKindMatcher(2, 2, 2);
        assertDoesNotMatch(matcher, List.of(CardValue.TEN, CardValue.FOUR, CardValue.TEN, CardValue.QUEEN, CardValue.JACK));
    }

    @Test
    void ThreeOfAKindMatcherMatchesLowThreeOfAKind() {
        PokerHandMatcher matcher = new XOfAKindMatcher(3, 1, 1);
        assertMatches(matcher, List.of(CardValue.TWO, CardValue.FOUR, CardValue.TWO, CardValue.ACE, CardValue.TWO));
    }

    @Test
    void ThreeOfAKindMatcherMatchesMiddleThreeOfAKind() {
        PokerHandMatcher matcher = new XOfAKindMatcher(3, 1, 1);
        assertMatches(matcher, List.of(CardValue.TWO, CardValue.FOUR, CardValue.FOUR, CardValue.ACE, CardValue.FOUR));
    }

    @Test
    void ThreeOfAKindMatcherMatchesHighThreeOfAKind() {
        PokerHandMatcher matcher = new XOfAKindMatcher(3, 1, 1);
        assertMatches(matcher, List.of(CardValue.ACE, CardValue.FOUR, CardValue.ACE, CardValue.ACE, CardValue.EIGHT));
    }

    @Test
    void ThreeOfAKindMatcherDoesNotMatchTwoOfAKind() {
        PokerHandMatcher matcher = new XOfAKindMatcher(3, 1, 1);
        assertDoesNotMatch(matcher, List.of(CardValue.FOUR, CardValue.FOUR, CardValue.TWO, CardValue.SIX, CardValue.QUEEN));
    }

    @Test
    void FourOfAKindMatcherMatchesLowFourOfAKind() {
        PokerHandMatcher matcher = new XOfAKindMatcher(4, 1, 1);
        assertMatches(matcher, List.of(CardValue.TWO, CardValue.FOUR, CardValue.TWO, CardValue.TWO, CardValue.TWO));
    }

    @Test
    void FourOfAKindMatcherMatchesHighFourOfAKind() {
        PokerHandMatcher matcher = new XOfAKindMatcher(4, 1, 1);
        assertMatches(matcher, List.of(CardValue.FOUR, CardValue.FOUR, CardValue.TWO, CardValue.FOUR, CardValue.FOUR));
    }

    @Test
    void FourOfAKindMatcherDoesNotMatchThreeOfAKind() {
        PokerHandMatcher matcher = new XOfAKindMatcher(4, 1, 1);
        assertDoesNotMatch(matcher, List.of(CardValue.FOUR, CardValue.FOUR, CardValue.TWO, CardValue.FOUR, CardValue.QUEEN));
    }

    @Test
    void FiveOfAKindMatcherMatchesFiveOfAKind() {
        PokerHandMatcher matcher = new XOfAKindMatcher(5, 1, 1);
        assertMatches(matcher, List.of(CardValue.THREE, CardValue.THREE, CardValue.THREE, CardValue.THREE, CardValue.THREE));
    }

    @Test
    void FiveOfAKindMatcherDoesNotMatchFourOfAKind() {
        PokerHandMatcher matcher = new XOfAKindMatcher(5, 1, 1);
        assertDoesNotMatch(matcher, List.of(CardValue.FOUR, CardValue.FOUR, CardValue.FOUR, CardValue.FOUR, CardValue.QUEEN));
    }


    private void assertMatches(PokerHandMatcher matcher, List<CardValue> handValues) {
        PokerHand hand = PokerHandTestHelper.createHandFromValues(handValues);
        assertTrue(matcher.matches(hand));
    }

    private void assertDoesNotMatch(PokerHandMatcher matcher, List<CardValue> handValues) {
        PokerHand hand = PokerHandTestHelper.createHandFromValues(handValues);
        assertFalse(matcher.matches(hand));
    }
}
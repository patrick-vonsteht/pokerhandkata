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
        Matcher matcher = new XOfAKindMatcher(1, 1, 1);
        assertDoesNotMatch(matcher, List.of(CardValue.TWO, CardValue.FOUR, CardValue.QUEEN, CardValue.ACE, CardValue.EIGHT));
    }

    @Test
    void MultipleOneOfAKindMatcherMatchesAnyHand() {
        Matcher matcher = new XOfAKindMatcher(1, 1, 5);
        assertMatches(matcher, List.of(CardValue.TWO, CardValue.FOUR, CardValue.QUEEN, CardValue.ACE, CardValue.EIGHT));
    }

    @Test
    void SingleTwoOfAKindMatcherMatchesLowTwoOfAKind() {
        Matcher matcher = new XOfAKindMatcher(2, 1, 1);
        assertMatches(matcher, List.of(CardValue.TWO, CardValue.FOUR, CardValue.TWO, CardValue.ACE, CardValue.EIGHT));
    }

    @Test
    void SingleTwoOfAKindMatcherMatchesMiddleTwoOfAKind() {
        Matcher matcher = new XOfAKindMatcher(2, 1, 1);
        assertMatches(matcher, List.of(CardValue.TWO, CardValue.FOUR, CardValue.FOUR, CardValue.ACE, CardValue.EIGHT));
    }

    @Test
    void SingleTwoOfAKindMatcherMatchesHighTwoOfAKind() {
        Matcher matcher = new XOfAKindMatcher(2, 1, 1);
        assertMatches(matcher, List.of(CardValue.TWO, CardValue.FOUR, CardValue.ACE, CardValue.ACE, CardValue.EIGHT));
    }

    @Test
    void SingleTwoOfAKindMatcherDoesNotMatchNonTwoOfAKind() {
        Matcher matcher = new XOfAKindMatcher(2, 1, 1);
        assertDoesNotMatch(matcher, List.of(CardValue.TEN, CardValue.FOUR, CardValue.TWO, CardValue.JACK, CardValue.QUEEN));
    }

    @Test
    void SingleTwoOfAKindMatcherDoesNotMatchTwoTwoOfAKind() {
        Matcher matcher = new XOfAKindMatcher(2, 1, 1);
        assertDoesNotMatch(matcher, List.of(CardValue.TEN, CardValue.FOUR, CardValue.TEN, CardValue.JACK, CardValue.JACK));
    }

    @Test
    void TwoTwoOfAKindMatcherMatchesTwoTwoOfAKind() {
        Matcher matcher = new XOfAKindMatcher(2, 2, 2);
        assertMatches(matcher, List.of(CardValue.TEN, CardValue.FOUR, CardValue.TEN, CardValue.JACK, CardValue.JACK));
    }

    @Test
    void TwoTwoOfAKindMatcherDoesNotMatchSingleTwoOfAKind() {
        Matcher matcher = new XOfAKindMatcher(2, 2, 2);
        assertDoesNotMatch(matcher, List.of(CardValue.TEN, CardValue.FOUR, CardValue.TEN, CardValue.QUEEN, CardValue.JACK));
    }

    @Test
    void ThreeOfAKindMatcherMatchesLowThreeOfAKind() {
        Matcher matcher = new XOfAKindMatcher(3, 1, 1);
        assertMatches(matcher, List.of(CardValue.TWO, CardValue.FOUR, CardValue.TWO, CardValue.ACE, CardValue.TWO));
    }

    @Test
    void ThreeOfAKindMatcherMatchesMiddleThreeOfAKind() {
        Matcher matcher = new XOfAKindMatcher(3, 1, 1);
        assertMatches(matcher, List.of(CardValue.TWO, CardValue.FOUR, CardValue.FOUR, CardValue.ACE, CardValue.FOUR));
    }

    @Test
    void ThreeOfAKindMatcherMatchesHighThreeOfAKind() {
        Matcher matcher = new XOfAKindMatcher(3, 1, 1);
        assertMatches(matcher, List.of(CardValue.ACE, CardValue.FOUR, CardValue.ACE, CardValue.ACE, CardValue.EIGHT));
    }

    @Test
    void ThreeOfAKindMatcherDoesNotMatchTwoOfAKind() {
        Matcher matcher = new XOfAKindMatcher(3, 1, 1);
        assertDoesNotMatch(matcher, List.of(CardValue.FOUR, CardValue.FOUR, CardValue.TWO, CardValue.SIX, CardValue.QUEEN));
    }

    @Test
    void FourOfAKindMatcherMatchesLowFourOfAKind() {
        Matcher matcher = new XOfAKindMatcher(4, 1, 1);
        assertMatches(matcher, List.of(CardValue.TWO, CardValue.FOUR, CardValue.TWO, CardValue.TWO, CardValue.TWO));
    }

    @Test
    void FourOfAKindMatcherMatchesHighFourOfAKind() {
        Matcher matcher = new XOfAKindMatcher(4, 1, 1);
        assertMatches(matcher, List.of(CardValue.FOUR, CardValue.FOUR, CardValue.TWO, CardValue.FOUR, CardValue.FOUR));
    }

    @Test
    void FourOfAKindMatcherDoesNotMatchThreeOfAKind() {
        Matcher matcher = new XOfAKindMatcher(4, 1, 1);
        assertDoesNotMatch(matcher, List.of(CardValue.FOUR, CardValue.FOUR, CardValue.TWO, CardValue.FOUR, CardValue.QUEEN));
    }

    @Test
    void FiveOfAKindMatcherMatchesFiveOfAKind() {
        Matcher matcher = new XOfAKindMatcher(5, 1, 1);
        assertMatches(matcher, List.of(CardValue.THREE, CardValue.THREE, CardValue.THREE, CardValue.THREE, CardValue.THREE));
    }

    @Test
    void FiveOfAKindMatcherDoesNotMatchFourOfAKind() {
        Matcher matcher = new XOfAKindMatcher(5, 1, 1);
        assertDoesNotMatch(matcher, List.of(CardValue.FOUR, CardValue.FOUR, CardValue.FOUR, CardValue.FOUR, CardValue.QUEEN));
    }


    private void assertMatches(Matcher matcher, List<CardValue> handValues) {
        PokerHand hand = PokerHandTestHelper.createHandFromValues(handValues);
        assertTrue(matcher.matches(hand));
    }

    private void assertDoesNotMatch(Matcher matcher, List<CardValue> handValues) {
        PokerHand hand = PokerHandTestHelper.createHandFromValues(handValues);
        assertFalse(matcher.matches(hand));
    }
}
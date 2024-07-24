package io.github.patrick_vonsteht.pokerhandkata.matchers;

import io.github.patrick_vonsteht.pokerhandkata.PokerHandTestHelper;
import io.github.patrick_vonsteht.pokerhandkata.TestValues;
import io.github.patrick_vonsteht.pokerhandkata.model.CardValue;
import io.github.patrick_vonsteht.pokerhandkata.model.PokerHand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class XOfAKindMatcherTest {

    Matcher oneThreeOfAKindMatcher = new XOfAKindMatcher(3, 1, 1);
    Matcher oneOneOfAKindMatcher =new XOfAKindMatcher(1,1,1);
    Matcher oneToFiveOneOfAKindMatcher = new XOfAKindMatcher(1, 1, 5);
    Matcher oneFiveOfAKindMatcher = new XOfAKindMatcher(5, 1, 1);
    Matcher twoTwoOfAKindMatcher =  new XOfAKindMatcher(2, 2, 2);

    @Test
    void OneThreeOfAKindMatcherDoesNotMatchTwoOfAKind() {
        assertDoesNotMatch(oneThreeOfAKindMatcher, TestValues.HIGH_PAIR_VALUES);
    }

    @Test
    void OneThreeOfAKindMatcherMatchesThreeOfAKind() {
        assertMatches(oneThreeOfAKindMatcher, TestValues.HIGH_THREE_OF_A_KIND_VALUES);
    }

    @Test
    void OneThreeOfAKindMatcherDoesNotMatchFourOfAKind() {
        assertDoesNotMatch(oneThreeOfAKindMatcher, TestValues.HIGH_FOUR_OF_A_KIND_VALUES);
    }

    @Test
    void OneOneOfAKindMatcherDoesNotMatchAnyHand() {
        assertDoesNotMatch(oneOneOfAKindMatcher, TestValues.ANY_CARD_VALUES);
    }

    @Test
    void OneToFiveOneOfAKindMatcherMatchesAnyHand() {
        assertMatches(oneToFiveOneOfAKindMatcher, TestValues.ANY_CARD_VALUES);
    }

    @Test
    void OneFiveOfAKindMatcherMatchesFiveOfAKind() {
        assertMatches(oneFiveOfAKindMatcher, TestValues.HIGH_FIVE_OF_A_KIND_VALUES);
    }

    @Test
    void TwoTwoOfAKindMatcherMatchesTwoTwoOfAKind() {
        assertMatches(twoTwoOfAKindMatcher, TestValues.TWO_PAIR_WITH_HIGH_FIRST_PAIR_VALUES);
    }

    @Test
    void TwoTwoOfAKindMatcherDoesNotMatchSingleTwoOfAKind() {
        assertDoesNotMatch(twoTwoOfAKindMatcher, TestValues.HIGH_PAIR_VALUES);
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